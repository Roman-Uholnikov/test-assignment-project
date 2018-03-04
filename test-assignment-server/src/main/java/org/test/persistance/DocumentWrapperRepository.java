package org.test.persistance;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.test.domain.DocumentWrapper;

import java.util.List;

/**
 * Rest repository is enough for current task.
 *
 * @author Roman Uholnikov
 */
@RepositoryRestResource(collectionResourceRel = "document", path = "document")
public interface DocumentWrapperRepository extends MongoRepository<DocumentWrapper, String> {

        @Query(fields="{ 'key': 1 }")
        List<DocumentWrapper> findByDocumentRegex(@Param("rexExp") String rexExp);

        @Query(fields="{ 'key': 1 }")
        List<DocumentWrapper> findByDocumentLike(@Param("phrase") String phrase);
    }

