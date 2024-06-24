package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Requests;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.RequestRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.IRequestService;

import java.util.List;


@Service
public class RequestService implements IRequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository productRepository) {
        this.requestRepository = productRepository;
    }

    public void save(Requests requests) {
        requestRepository.save(requests);
    }

    public List<Requests> findByUserId(String user_id) {
        return requestRepository.findByUserId(user_id);
    }


}