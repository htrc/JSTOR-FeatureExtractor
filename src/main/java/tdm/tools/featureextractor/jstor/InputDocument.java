package tdm.tools.featureextractor.jstor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class InputDocument {
    private final String id;
    private final List<DocumentPage> pages;

    @JsonCreator
    public InputDocument(@JsonProperty("id") String id,
                         @JsonProperty("pages") List<DocumentPage> pages) {
        this.id = id;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public List<DocumentPage> getPages() {
        return pages;
    }
}
