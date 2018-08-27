package tdm.tools.featureextractor.jstor;

import org.joda.time.DateTime;

public class Document {
    protected final String id;
    protected final String dateCreated;
    protected final String schemaVersion;
    protected final DocumentFeatures features;

    public Document(String id, DocumentFeatures features) {
        this.id = id;
        this.dateCreated = DateTime.now().toString(Constants.DATETIME_FORMAT);
        this.schemaVersion = Constants.SCHEMA_VERSION;
        this.features = features;
    }

    public String getId() {
        return id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public DocumentFeatures getFeatures() {
        return features;
    }
}
