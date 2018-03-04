package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.domain.DocumentWrapper;
import org.test.persistance.DocumentWrapperRepository;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private  DocumentWrapperRepository repository;

    public List<DocumentWrapper> findAllByKeyNotNull() {
        return repository.findAllByKeyNotNull();
    }

    public List<DocumentWrapper> findByDocumentRegex(String phrase) {
        String rexExp= buildRegExp(phrase);
        return repository.findByDocumentRegex(rexExp);
    }

    public List<DocumentWrapper> findByDocumentLike(String phrase) {
        return repository.findByDocumentLike(phrase);
    }


    /**
     * In order to fetch document that "contains all tokens in the set"
     * we build regexp.
     * @param phrase string with words (tokens)
     * @return reg exp that mandatory includes
     *         all tokens (not necessary going together in one sentence).
     */
    private String buildRegExp(String phrase) {
        StringBuilder builder = new StringBuilder("");
        String[] split = phrase.split("[^a-zA-Z0-9-_]");
        builder.append("(([^a-zA-Z0-9-_]+)|(^))");
        for (String token :
                split) {
            builder.append("(" + token + ")");
            builder.append("([^a-zA-Z0-9-_]{1}.*[^a-zA-Z0-9-_]{1}|(\\ )|($))");
        }

        return builder.toString();
    }

    // TODO: 05.03.18 test it on
    /*
    test what?
    test assignment for Roman
    a test assignment
    test and assignment
    super test and assignment
    test and assignment here
     */
}
