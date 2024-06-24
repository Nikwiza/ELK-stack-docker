package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;

import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Requests;

import java.util.List;

public interface IRequestService {

    List<Requests> findByUserId(String user_id);
}
