package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Request;

import java.util.List;

public interface IRequestService {

    List<Request> findByNameOrDescription(String name, String description);

//    List<Request> findByNameContainingOrDescriptionContaining(String name, String description);
//
//    List<Request> findByCustomQuery(String query);
//
//    List<Request> searchByDescriptionPhrase(String phrase);
//
//    List<Request> searchByNameOrDescriptionFuzzy(String searchTerm);
//
//    List<Request> findByNameAndDescriptionNotAndOptional(String name, String mustNotTerms, String shouldTerms);
//
//    List<Request> findByFunctionScore(String searchTerm, String boostTerms);
}
