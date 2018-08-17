package tdm.tools.featureextractor.jstor;

public class Document {
    protected final String id;
    protected final DocumentFeatures features;

    public Document(String id, DocumentFeatures features) {
        this.id = id;
        this.features = features;
    }

    public String getId() {
        return id;
    }

    public DocumentFeatures getFeatures() {
        return features;
    }
}
