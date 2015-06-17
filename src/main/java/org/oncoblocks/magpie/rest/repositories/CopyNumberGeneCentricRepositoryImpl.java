package org.oncoblocks.magpie.rest.repositories;

import com.mongodb.MongoClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oncoblocks.magpie.rest.models.CopyNumberGeneCentric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class CopyNumberGeneCentricRepositoryImpl implements CopyNumberGeneCentricRepositoryCustom {
    private static final Log log = LogFactory.getLog(CopyNumberGeneCentricRepositoryImpl.class);
    private static final String DB_NAME = "magpie_test_1";
    private static final String COLLECTION_NAME = "cnv_gene_centric";

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<CopyNumberGeneCentric> findByGeneId(Integer geneId) throws Exception {
        mongoTemplate = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), DB_NAME));
        return mongoTemplate.find(query(where("entrezGeneId").is(geneId)), CopyNumberGeneCentric.class, COLLECTION_NAME);
    }
}