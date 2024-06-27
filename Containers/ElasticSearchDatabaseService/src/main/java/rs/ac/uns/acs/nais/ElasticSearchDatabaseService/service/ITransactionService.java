package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;


import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.dto.AmountStatDTO;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Transactions;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ITransactionService {

    List<Transactions> findByUserId(String user_id);
    Iterable<Transactions> getAllTransactions();
    Optional<Transactions> findById(String Id);
    Transactions getTransactionById(String transaction_id);
    void deleteById(String transaction_id);

    // Queries
    List<Transactions> searchByCommentOrCompanyNameFuzzy(String searchTerm);
    List<Transactions> findByCompanyNameAndCommentNotAndOptional(String companyName, String mustNotTerms, String shouldTerms);
    List<Transactions> findLesserThan(double Amount);
    List<Transactions> findGreaterThan(double Amount);


}
