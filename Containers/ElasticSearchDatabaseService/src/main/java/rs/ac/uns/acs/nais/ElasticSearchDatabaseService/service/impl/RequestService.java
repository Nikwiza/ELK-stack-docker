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

    public List<Requests> findByUserId(String user_id) {
        return requestRepository.findByUserId(user_id);
    }

    //CRUD services

    public void save(Requests requests) {
        requestRepository.save(requests);
    }

    @Override
    public Iterable<Requests> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public Requests getRequestById(String request_id) {
        return requestRepository.findById(request_id).orElseThrow();
    }

    @Override
    public Requests updateRequest(Requests requests) {
        return requestRepository.save(requests);
    }

    @Override
    public void deleteById(String request_id) {
        requestRepository.deleteById(request_id);
    }



    @Override
    public List<Requests> notApprovedRequests() {
        return requestRepository.notApprovedRequests();
    }

    @Override
    public List<Requests> findByCustomQueryAndUserId(String query, String userId) {
        return requestRepository.findByCustomQueryAndUserId(query, userId);
    }

    @Override
    public List<Requests> findUserAndUserTags(String searchTerm) {
        return requestRepository.findUserAndUserTags(searchTerm);
    }

    @Override
    public List<Requests> findByUserIdAndTextNotAndOptional(String userId, String mustNotTerms, String shouldTerms) {
        return requestRepository.findByUserIdAndTextNotAndOptional(userId, mustNotTerms, shouldTerms);
    }

    //Queries



}