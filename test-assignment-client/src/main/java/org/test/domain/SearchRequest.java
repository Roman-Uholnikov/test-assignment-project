package org.test.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Simple search request
 *
 * @author Roman Uholnikov
 */
@Getter
@Setter
@NoArgsConstructor
public class SearchRequest {
    private String phrase;
    /**
     * if false, then tries to match all words from phrase with
     * possible intermediate words.
     */
    private boolean exactMatch;

}
