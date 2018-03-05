package org.test.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.test.ServerWebApplication;
import org.test.domain.DocumentWrapper;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ServerWebApplication.class)
@AutoConfigureMockMvc
public class ControllerIT {

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        insertDocument("key1", "test what?");
        insertDocument("key2", "test assignment for Roman");
        insertDocument("key3", "a test assignment");
        insertDocument("key4", "test and assignment");
        insertDocument("key5", "super test and assignment");
        insertDocument("key6", "test and assignment here");
        insertDocument("key7", "sssstestassignmentsss");
        insertDocument("key8", "a test % assignment");
        insertDocument("key9", "?a test !@#$%^&*()_+ assignment?");
    }

    @Test
    public void allDocuments() throws Exception {
        mvc.perform(get("/document-keys"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(9)));
    }

    @Test
    public void allDocumentsLike() throws Exception {
        mvc.perform(get("/document/search/like").param("phrase", "test assignment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0]", is("key2")))
                .andExpect(jsonPath("$.[1]", is("key3")));
    }

    @Test
    public void allDocumentsLikeSpecialSymbol() throws Exception {
        mvc.perform(get("/document/search/like").param("phrase", "test %"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0]", is("key8")));
    }

    @Test
    public void allDocumentsRgexp() throws Exception {
        //only sssstestassignmentsss and test what? are not matching
        mvc.perform(get("/document/search/phrase").param("phrase", "test assignment"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(7)));
    }

    private void insertDocument(String key, String document) throws Exception {
        mvc.perform(post("/document")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new DocumentWrapper(key, document))))
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}