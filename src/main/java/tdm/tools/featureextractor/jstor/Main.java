package tdm.tools.featureextractor.jstor;

import com.beust.jcommander.JCommander;
import com.codepoetics.protonpack.Indexed;
import com.codepoetics.protonpack.StreamUtils;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.hathitrust.htrc.textprocessing.runningheaders.java.PageStructureParser;
import org.hathitrust.htrc.textprocessing.runningheaders.java.PageStructureParser.StructureParserConfig;
import tdm.featureextractor.java.PageFeatureExtractor;
import tdm.featureextractor.java.features.PageFeatures;

public class Main {
    private static final String appName = System.getProperty("app.name", "jstor-feature-extractor");
    private static final StructureParserConfig parserConfig = PageStructureParser.defaultConfig();
    private static final ObjectMapper mapper;

    static {
        JsonFactory jsonFactory = new JsonFactory();
        jsonFactory.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        mapper = new ObjectMapper(jsonFactory);
    }

    public static void main(String[] args) throws Exception {
        Conf conf = new Conf();
        JCommander commander = JCommander.newBuilder().addObject(conf).build();
        commander.setProgramName(Main.appName);
        commander.parse(args);

        if (conf.showHelp()) {
            commander.usage();
            System.exit(1);
        }

        try (
            BufferedReader lineReader =
                 new BufferedReader(new InputStreamReader(
                     conf.getInputJsonLinesStream(),
                     StandardCharsets.UTF_8
                 ));
            PrintStream outputStream = conf.getOutputJsonLinesStream()
        ) {
            Stream<Indexed<String>> indexedJsons = StreamUtils.zipWithIndex(lineReader.lines());
            Stream<Indexed<String>> nonEmptyJsonLines = indexedJsons.filter(line -> !line.getValue().trim().isEmpty());
            Stream<InputDocument> goodDocuments = nonEmptyJsonLines.map(Main::parseJsonInput)
                                                                   .filter(Objects::nonNull);
            Stream<Document> documentFeatures = goodDocuments.map(Main::getDocumentFeatures);

            // output the extracted features as JSON
            documentFeatures.forEach(docFeatures -> writeFeaturesAsJson(docFeatures, outputStream));
        }
    }

    private static void writeFeaturesAsJson(Document features, PrintStream outputStream) {
        try {
            mapper.writeValue(outputStream, features);
            outputStream.println();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputDocument parseJsonInput(Indexed<String> jsonLine) {
        try {
            return mapper.readValue(jsonLine.getValue(), InputDocument.class);
        }
        catch (IOException e) {
            System.err.println(String.format(
                "Error on line %d: %s",
                jsonLine.getIndex() + 1,
                e.getMessage()
            ));
            return null;
        }
    }

    private static Document getDocumentFeatures(InputDocument inputDocument) {
        // do the running header/footer detection (aka page structure parsing)
        List<StructuredDocumentPage> structuredPages =
            PageStructureParser.parsePageStructure(inputDocument.getPages(), parserConfig,
                                                   StructuredDocumentPage::new);

        // perform feature extraction on these pages
        List<TdmPageFeatures> features =
            structuredPages.stream()
                           .map(page -> {
                               PageFeatures standardFeatures = PageFeatureExtractor.extractPageFeatures(page);
                               return TdmPageFeatures.withSeq(page.seq, standardFeatures);
                           })
                           .collect(Collectors.toList());

        return new Document(inputDocument.getId(), new DocumentFeatures(features));
    }
}
