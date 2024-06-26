package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository;


import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Transactions;

import java.util.List;

@Repository
public interface TransactionRepository extends ElasticsearchRepository<Transactions, String> {
    List<Transactions> findByUserId(String user_id);

    @Query("{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"comment^2\",\"company_name\"],\"fuzziness\":\"AUTO\"}}, \"sort\": [{\"amount\": {\"order\": \"asc\"}}]")
    List<Transactions> searchByCommentOrCompanyNameFuzzy(String searchTerm);


}
