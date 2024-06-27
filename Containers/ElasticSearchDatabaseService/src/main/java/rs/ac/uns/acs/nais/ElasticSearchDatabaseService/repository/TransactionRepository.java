package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository;


import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.dto.AmountStatDTO;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Transactions;

import java.util.List;

@Repository
public interface TransactionRepository extends ElasticsearchRepository<Transactions, String> {
    List<Transactions> findByUserId(String user_id);



    // A search for general keywords in the database
    @Query("{\"multi_match\":{\"query\":\"?0\",\"fields\":[\"comment\",\"company_name^2\"],\"fuzziness\":\"AUTO\"}}, \"sort\": [{\"amount\": {\"order\": \"asc\"}}]")
    List<Transactions> searchByCommentOrCompanyNameFuzzy(String searchTerm);

    // A classic search for company transactions with must and must not contain keywords
    @Query("{\"bool\":{\"must\":[{\"match\":{\"company_name\":\"?0\"}}],\"must_not\":[{\"match\":{\"comment\":\"?1\"}}],\"should\":[{\"match\":{\"comment\":\"?2\"}}]}}")
    List<Transactions> findByCompanyNameAndCommentNotAndOptional(String companyName, String mustNotTerms, String shouldTerms);

    // Finds greater amounts than the specified amount that are sent and sorts them
    @Query("{\"bool\":{\"must\":[{\"range\":{\"amount\":{\"gt\":\"?0\"}}}, {\"match\":{\"sent\":\"True\"}}]}}, \"sort\": [{\"amount\": {\"order\": \"asc\"}}]")
    List<Transactions> findGreaterThan(double amount);

    // Finds smaller values than specified and sorts them
    @Query("{\"bool\":{\"must\":[{\"range\":{\"amount\":{\"lt\":\"?0\"}}}, {\"match\":{\"sent\":\"True\"}}]}}, \"sort\": [{\"amount\": {\"order\": \"desc\"}}]")
    List<Transactions> findLesserThan(double amount);


}
