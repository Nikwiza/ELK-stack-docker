package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository;


import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Requests;

import java.util.List;

@Repository
public interface RequestRepository extends ElasticsearchRepository<Requests, String> {
    List<Requests> findByUserId(String user_id);

    @Query("{\"bool\":{\"must\":[{\"match\":{\"approved\":\"False\"}}, {\"range\":{\"likes\":{\"gt\":\"900\"}}} ] } }, \"sort\": [{\"likes\": {\"order\": \"asc\"}}]")
    List<Requests> notApprovedRequests();

    @Query("{\"bool\":{\"should\":[{\"match\":{\"text\":\"?0\"}}], \"must\":[{\"match\":{\"userId\":\"?1\"}}] }}, \"sort\": [{\"likes\": {\"order\": \"asc\"}}]")
    List<Requests> findByCustomQueryAndUserId(String query, String userId);

    @Query("{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"userId^3\",\"text\"],\"fuzziness\":\"AUTO\"}}")
    List<Requests> findUserAndUserTags(String searchTerm);

    @Query("{\"bool\":{\"must\":[{\"match\":{\"userId\":\"?0\"}}],\"must_not\":[{\"match\":{\"text\":\"?1\"}}],\"should\":[{\"match\":{\"text\":\"?2\"}}]}}")
    List<Requests> findByUserIdAndTextNotAndOptional(String userId, String mustNotTerms, String shouldTerms);


}
