package tdm.tools.featureextractor.jstor;

import tdm.featureextractor.java.features.PageFeatures;
import tdm.featureextractor.java.features.SectionFeatures;

/**
 * Class that extends the "standard" set of features with an "seq" attribute for each page
 */
public class TdmPageFeatures extends PageFeatures {
    protected final String seq;

    /**
     * Object recording aggregate features at the page level
     *
     * @param version        The MD5 hash of the page content
     * @param language       The identified page language (if any)
     * @param seq            The page sequence label
     * @param tokenCount     The total token count for the page
     * @param lineCount      The total line count for the page
     * @param emptyLineCount The empty line count for the page
     * @param sentenceCount  The sentence count for the page
     * @param header         The page header features
     * @param body           The page body features
     * @param footer         The page footer features
     */
    public TdmPageFeatures(String version, String language, String seq,
                           int tokenCount, int lineCount, int emptyLineCount, Integer sentenceCount,
                           SectionFeatures header, SectionFeatures body, SectionFeatures footer) {
        super(version, language,
              tokenCount, lineCount, emptyLineCount, sentenceCount,
              header, body, footer);
        this.seq = seq;
    }

    public static TdmPageFeatures withSeq(String seq, PageFeatures features) {
        return new TdmPageFeatures(
            features.getVersion(), features.getLanguage(), seq,
            features.getTokenCount(), features.getLineCount(), features.getEmptyLineCount(),
            features.getSentenceCount(), features.getHeader(), features.getBody(), features.getFooter()
        );
    }

    public String getSeq() {
        return seq;
    }
}
