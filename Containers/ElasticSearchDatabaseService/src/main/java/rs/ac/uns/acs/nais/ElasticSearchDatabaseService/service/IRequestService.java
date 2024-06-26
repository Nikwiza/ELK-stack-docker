package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import org.apache.coyote.Request;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Product;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Requests;

import java.util.List;

public interface IRequestService {

    List<Requests> findByUserId(String user_id);

    Iterable<Requests> getAllRequests();
    Requests getRequestById(String request_id);
    Requests updateRequest(Requests requests);
    void deleteById(String request_id);
}
