package tdm.tools.featureextractor.jstor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;
import org.hathitrust.htrc.textprocessing.runningheaders.Page;
import scala.collection.IndexedSeq;
import scala.collection.JavaConverters;

public class DocumentPage implements Page {
    protected final String seq;
    protected final List<String> lines;

    @JsonCreator
    public DocumentPage(@JsonProperty("seq") String seq,
                        @JsonProperty("content") String text) {
        BufferedReader br = new BufferedReader(new StringReader(text));
        this.seq = seq;
        this.lines = br.lines().collect(Collectors.toList());
    }

    public DocumentPage(String seq, List<String> lines) {
        this.seq = seq;
        this.lines = lines;
    }

    @Override
    public IndexedSeq<String> textLines() {
        return JavaConverters.asScalaIterator(lines.iterator()).toIndexedSeq();
    }

    public String getSeq() {
        return seq;
    }
}