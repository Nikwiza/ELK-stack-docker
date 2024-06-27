package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Requests;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Transactions;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl.RequestService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/requests.json")
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }



    //CRUD

    public Optional<Requests> findById(String id) {
        return requestService.findById(id);
    }


    @DeleteMapping
    public void deleteById(@RequestParam(value = "Id") String Id){
        requestService.deleteById(Id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Requests> updateById(@PathVariable String id, @RequestBody Requests requests){
        Optional<Requests> requests1 = requestService.findById(id);
        if(requests1.isPresent()){
            Requests existingRequest = requests1.get();
            existingRequest.setApproved(requests.getApproved());
            existingRequest.setLikes(requests.getLikes());
            existingRequest.setText(requests.getText());
            existingRequest.setUser_id(requests.getUser_id());

            return ResponseEntity.ok(requestService.save(existingRequest));
        }

        return ResponseEntity.notFound().build();
    }


    @GetMapping("findByUserId")
    public List<Requests> findByUserId(@RequestParam(value = "userId") String userId) {
        return requestService.findByUserId(userId);
    }

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