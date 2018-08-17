package tdm.tools.featureextractor.jstor;

import com.beust.jcommander.Parameter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

public class Conf {
    @Parameter(
        names = {"-i", "--input"},
        description = "Input JSONL (jsonlines.org) data to process (omit to read from stdin)"
    )
    private String inputJsonFile;

    @Parameter(
        names = {"-o", "--output"},
        description = "Output JSON document containing the extracted features (omit to output to stdout)"
    )
    private String outputJsonFile;

    @Parameter(
        names = {"-h", "--help"},
        help = true
    )
    private boolean showHelp = false;

    public InputStream getInputJsonLinesStream() {
        if (inputJsonFile != null) {
            try {
                return new FileInputStream(inputJsonFile);
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else { return System.in; }
    }

    public PrintStream getOutputJsonLinesStream() {
        if (outputJsonFile != null) {
            try {
                return new PrintStream(outputJsonFile);
            }
            catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else { return System.out; }
    }

    public boolean showHelp() {
        return showHelp;
    }
}
