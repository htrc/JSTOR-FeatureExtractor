package tdm.tools.featureextractor.jstor;

import java.util.List;

public class DocumentFeatures {
    protected final int pageCount;
    protected final List<TdmPageFeatures> pages;

    public DocumentFeatures(List<TdmPageFeatures> pages) {
        this.pageCount = pages.size();
        this.pages = pages;
    }

    public int getPageCount() {
        return pageCount;
    }

    public List<TdmPageFeatures> getPages() {
        return pages;
    }
}
