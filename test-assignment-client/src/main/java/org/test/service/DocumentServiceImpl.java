package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.test.domain.DocumentResources;
import org.test.domain.DocumentWrapper;
import org.test.domain.SearchRequest;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Value("${test.assignment.server.url}")
    private String serverBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

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
        String documentResource = restTemplate
                .postForObject(serverBaseUrl,
                        wrapper, String.class);

        return documentResource.toString();

    }

    @Override
    public List<String> getAllDocumentsKeys() {
        Resources resoponse = restTemplate
                .getForObject(serverBaseUrl, Resources.class);
        return Collections.emptyList();

//        return Arrays.stream(forEntity.getBody())
//                .map(Resource::getContent)
//                .map(DocumentWrapper::getKey)
//                .collect(Collectors.toList());
    }

    @Override
    public List<String> searchDocuments(SearchRequest searchRequest) {
        return documentKeys.keySet().stream()
                .filter(s -> s.contains(searchRequest.getPhrase()))
                .collect(Collectors.toList());
    }
}
