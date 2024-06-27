package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Requests;

import java.util.List;

public interface IRequestService {

    List<Requests> findByUserId(String user_id);

    Iterable<Requests> getAllRequests();
    Requests getRequestById(String request_id);
    Requests updateRequest(Requests requests);
    void deleteById(String request_id);


    List<Requests> notApprovedRequests();
    List<Requests> findByCustomQueryAndUserId(String query, String userId);
    List<Requests> findUserAndUserTags(String searchTerm);
    List<Requests> findByUserIdAndTextNotAndOptional(String userId, String mustNotTerms, String shouldTerms);

}
