package org.test.service;

import org.springframework.stereotype.Service;
import org.test.domain.DocumentWrapper;
import org.test.domain.SearchRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//todo roman: implement it
@Service
public class DocumentServiceImpl implements DocumentService {

    private Map<String, String> documentKeys = new HashMap<>();

    @Override
    public String getDocument(String documentKey) {
        return documentKeys.entrySet().stream()
                .filter(s -> s.getKey().contains(documentKey))
                .map(g -> g.getValue())
                .findFirst().get();
    }

    @Override
    public String saveDocument(DocumentWrapper wrapper) {
        String put = documentKeys.put(wrapper.getKey(), wrapper.getDocument());
        return null;
    }

    @Override
    public List<String> getAllDocumentsKeys() {
        return documentKeys.keySet().stream().collect(Collectors.toList());
    }

    @Override
    public List<String> searchDocuments(SearchRequest searchRequest) {
        return documentKeys.keySet().stream()
                .filter(s -> s.contains(searchRequest.getPhrase()))
                .collect(Collectors.toList());
    }
}
