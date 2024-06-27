package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;

import org.bouncycastle.cert.ocsp.Req;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Requests;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Transactions;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl.RequestService;

import java.util.List;

@RestController
@RequestMapping("/requests.json")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("findByUserId")
    public List<Requests> findByUserId(@RequestParam(value = "userId") String userId) {
        return requestService.findByUserId(userId);
    }

    // CRUD endpoints
    @PostMapping
    public void addRequest(@RequestBody Requests requests) {
        requestService.save(requests);
    }

    @GetMapping
    public Iterable<Requests> findAllRequests(){return requestService.getAllRequests();}


    // Queries
    @GetMapping("/notApprovedRequests")
    public List<Requests> approvedRequests(){
        return requestService.notApprovedRequests();
    }

    @GetMapping("/findUserQuery")
    public List<Requests> findByCustomQueryAndUserId(@RequestParam(value = "userId") String userId,
                                                     @RequestParam(value = "query") String query){
        return requestService.findByCustomQueryAndUserId(query, userId);
    }

    @GetMapping("/findUserAndTags")
    List<Requests> findUserAndUserTags(@RequestParam(value = "userTag") String userTag){
        return requestService.findUserAndUserTags(userTag);
    }

    @GetMapping("/findRequestByUserAndTerms")
    List<Requests> findByUserIdAndTextNotAndOptional(@RequestParam(value = "userId") String userId,
                                                     @RequestParam(value = "mustNotTerms") String mustNotTerms,
                                                     @RequestParam(value = "shouldTerms") String shouldTerms
                                                                                                    ){
        return requestService.findByUserIdAndTextNotAndOptional(userId, mustNotTerms, shouldTerms);
    }

}