package org.oncoblocks.magpie.rest.repositories;

import com.mongodb.MongoClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.oncoblocks.magpie.rest.models.CopyNumberGeneCentric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        return mongoTemplate.find( query( where("entrezGeneId").is(geneId) ), CopyNumberGeneCentric.class, COLLECTION_NAME );
    }

    public List<CopyNumberGeneCentric> find(HashMap<String, String> param) throws Exception {
        List<Criteria> criteriaList = new ArrayList<>();

        if ( param.get("geneId") != null) {
            Integer geneId = Integer.parseInt(param.get("geneId"));

            if (geneId != null) {
                criteriaList.add(Criteria.where("entrezGeneId").is(geneId));
            }
        }

        String sampleId = param.get("sampleId");
        if ( sampleId != null ) {
            criteriaList.add( Criteria.where("sampleId").regex(sampleId, "i") );
        }

        log.info("Number of criteria in this query is:" + criteriaList.size());

        Criteria criteria = new Criteria();
        criteria = criteria.andOperator( criteriaList.toArray( new Criteria[ criteriaList.size() ] ) );

        Query query = new Query(criteria);

        String fieldsStr = param.get("fields");
        if ( fieldsStr != null ) {
            List<String> fields = Arrays.asList(fieldsStr.split(","));
            for ( String field : fields) {
                query.fields().include(field);
            }
        }
        return mongoTemplate.find(query, CopyNumberGeneCentric.class);
    }

}