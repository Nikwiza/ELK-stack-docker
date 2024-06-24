package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository;


import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Requests;

import java.util.List;

@Repository
public interface RequestRepository extends ElasticsearchRepository<Requests, String> {
    List<Requests> findByUserId(String user_id);

}
