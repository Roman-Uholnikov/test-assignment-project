package org.test.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.test.domain.SearchRequest;
import org.test.service.DocumentService;

import java.util.Collections;

@Controller
public class RootController {

    @Value("${welcome.message:test}")
    private String message;

    @Autowired
    private DocumentService documentService;

    @RequestMapping("/")
    public String welcome(Model model) {
        model.addAttribute("resultList", documentService.getAllDocumentsKeys());
        return "welcome";
    }


    @GetMapping("/search")
    public String searchForm(Model model) {
        model.addAttribute("searchRequest", new SearchRequest());
        model.addAttribute("resultList", Collections.emptySet());
        return "search";
    }

    @PostMapping("/search")
    public String searchSubmit(Model model,
                               @ModelAttribute SearchRequest searchRequest) {
        model.addAttribute("resultList", documentService.searchDocuments(searchRequest));
        return "search";
    }

    @GetMapping("/document/{key}")
    public String greetingSubmit(Model model,
                                 @PathVariable("key") String key) {
        model.addAttribute("document", documentService.getDocument(key));
        return "document";
    }


}