package org.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
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
    public DocumentWrapper getDocument(String documentKey) {
        return  restTemplate.getForObject(serverBaseUrl + "/document/" + documentKey, DocumentWrapper.class);
    }

    @Override
    public String saveDocument(DocumentWrapper wrapper) {
        String documentResource = restTemplate
                .postForObject(serverBaseUrl + "/document",
                        wrapper, String.class);

        return documentResource;

    }

    @Override
    public List<String> getAllDocumentsKeys() {
        return restTemplate
                .getForObject(serverBaseUrl + "/document-keys", List.class);
    }

    @Override
    public List<String> searchDocuments(SearchRequest searchRequest) {
        String url;
        String phrase = searchRequest.getPhrase();
        if(searchRequest.isExactMatch()){
            url = serverBaseUrl + "/document/search/like";
        } else {
            url = serverBaseUrl + "/document/search/phrase";

        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("phrase", phrase);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<List> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                List.class);

        return response.getBody();
    }

}
