package tdm.tools.featureextractor.jstor;

import org.hathitrust.htrc.textprocessing.runningheaders.java.PageStructureParser.StructuredPage;

public class StructuredDocumentPage extends DocumentPage implements StructuredPage {
    protected final int numHeaderLines;
    protected final int numFooterLines;

    public StructuredDocumentPage(DocumentPage documentPage, int numHeaderLines, int numFooterLines) {
        super(documentPage.seq, documentPage.lines);
        this.numHeaderLines = numHeaderLines;
        this.numFooterLines = numFooterLines;
    }

    @Override
    public int numHeaderLines() {
        return numHeaderLines;
    }

    @Override
    public int numFooterLines() {
        return numFooterLines;
    }
}