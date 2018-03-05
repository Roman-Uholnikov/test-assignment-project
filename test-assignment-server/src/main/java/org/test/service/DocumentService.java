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
        String[] split = phrase.split("[^\\w]");
        for (String token :
                split) {
            builder.append("([\\W\\s\\w]*)");
            builder.append("(\\b)");
            builder.append("(" + token + ")");
            builder.append("(\\b)");
        }
        builder.append("(([\\W\\s\\w]*)|($))");

        return builder.toString();
    }

}
