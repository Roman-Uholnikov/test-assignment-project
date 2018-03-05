package org.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.test.TestConfig;
import org.test.domain.DocumentWrapper;
import org.test.domain.SearchRequest;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class DocumentServiceImplTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DocumentService service;

    @Test
    public void getDocument() {
        DocumentWrapper documentWrapper = new DocumentWrapper("document", "key");
        when(restTemplate.getForObject(anyString(), eq(DocumentWrapper.class)))
                .thenReturn(documentWrapper);
        DocumentWrapper key = service.getDocument("key");
        assertEquals(documentWrapper, key);
    }

    @Test
    public void saveDocument() {
        DocumentWrapper documentWrapper = new DocumentWrapper("document", "key");
        when(restTemplate.postForObject(anyString(), eq(documentWrapper), eq(String.class)))
                .thenReturn("error");
        String s = service.saveDocument(documentWrapper);
        assertEquals("error", s);
    }

    @Test
    public void getAllDocumentsKeys() {
        List<String> in = Arrays.asList("one", "two");
        when(restTemplate
                .getForObject(anyString(), eq(List.class))).thenReturn(in);
        List<String> allDocumentsKeys = service.getAllDocumentsKeys();
        assertEquals(in, allDocumentsKeys);
    }

    @Test
    public void searchDocuments() {
        List<String> in = Arrays.asList("one", "two");
        SearchRequest searchRequest = new SearchRequest("phrase", true);
        when(restTemplate
                .exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(List.class)))
                .thenReturn(new ResponseEntity<>(in, HttpStatus.OK));
        List<String> allDocumentsKeys = service.searchDocuments(searchRequest);
        assertEquals(in, allDocumentsKeys);
    }
}