package org.oncoblocks.magpie.rest.repositories;

import org.oncoblocks.magpie.rest.models.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository extends MongoRepository<Subject, String> {
}
