package org.test.service;

import org.test.domain.DocumentWrapper;
import org.test.domain.SearchRequest;

import java.util.List;

/**
 * Service to work with documents. We assuming that document is simple String.
 *
 * @author Roman Uholnikov
 */
public interface DocumentService {

    /**
     * Fetch document by key
     *
     * @param documentKey key of the document
     * @return String document or null if not found
     */
    String getDocument(String documentKey);

    /**
     * Save document
     *
     * @return null if saved successfully, Error message otherwise
     * (bad parameters, document already exist...)
     */
    String saveDocument(DocumentWrapper documentWrapper);

    /**
     * Get all documents
     *
     * @return all available documents
     */
    List<String> getAllDocumentsKeys();


    /**
     * Search for the documents
     *
     * @return list of documents keys founded
     */
    List<String> searchDocuments(SearchRequest searchRequest);

}
