package org.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.test.domain.DocumentWrapper;
import org.test.persistance.DocumentWrapperRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {
    //in order to avoid sending whole document via RepositoryRestResource we define some
    // thin endpoints.

    @Autowired
    private DocumentWrapperRepository repository;


    @GetMapping("/document-keys")
    public ResponseEntity<List> allDocuments(){
        List<DocumentWrapper> all = repository.findAllByKeyNotNull();
        return ResponseEntity.ok(all.stream().map(DocumentWrapper::getKey)
                .collect(Collectors.toList()));
    }

    @GetMapping("/document/search/like")
    public ResponseEntity<List> allDocumentsLike(@RequestParam("phrase") String phrase){
        List<DocumentWrapper> all = repository.findByDocumentLike(phrase);
        return ResponseEntity.ok(all.stream().map(DocumentWrapper::getKey)
                .collect(Collectors.toList()));
    }

    @GetMapping("/document/search/regexp")
    public ResponseEntity<List> allDocumentsRgexp(@RequestParam("phrase") String phrase){
        List<DocumentWrapper> all = repository.findByDocumentRegex(phrase);
        return ResponseEntity.ok(all.stream().map(DocumentWrapper::getKey)
                .collect(Collectors.toList()));
    }
}
