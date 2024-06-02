package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Transaction;

import java.util.List;

public interface ITransactionService {

    List<Transaction> findByNameOrDescription(String name, String description);

//    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);
//
//    List<Product> findByCustomQuery(String query);
//
//    List<Product> searchByDescriptionPhrase(String phrase);
//
//    List<Product> searchByNameOrDescriptionFuzzy(String searchTerm);
//
//    List<Product> findByNameAndDescriptionNotAndOptional(String name, String mustNotTerms, String shouldTerms);
//
//    List<Product> findByFunctionScore(String searchTerm, String boostTerms);
}
