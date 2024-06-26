package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Transactions;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl.TransactionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions.json")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    Iterable<Transactions> getAll(){
        return transactionService.getAllTransactions();
    }

    @PostMapping
    public Transactions addTransaction(@RequestBody Transactions transactions) {
        return transactionService.save(transactions);
    }

    @GetMapping("findByUserId")
    public List<Transactions> findByUserId(@RequestParam(value = "userId") String userId) {
        return transactionService.findByUserId(userId);
    }

    //CRUD

    @GetMapping("findById")
    public Transactions findById(@RequestParam(value = "Id") String Id){
        return transactionService.getTransactionById(Id);
    }

    @DeleteMapping
    public void deleteById(@RequestParam(value = "Id") String Id){
        transactionService.deleteById(Id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transactions> updateById(@PathVariable String id, @RequestBody Transactions transactions){
        Optional<Transactions> transaction = transactionService.findById(id);
        if(transaction.isPresent()){
            Transactions existingTransaction = transaction.get();
            existingTransaction.setAmount(transactions.getAmount());
            existingTransaction.setComment(transactions.getComment());
            existingTransaction.setSent(transactions.getSent());
            existingTransaction.setCompany_name(transactions.getCompany_name());
            existingTransaction.setUser_id(transactions.getUser_id());
            return ResponseEntity.ok(transactionService.save(existingTransaction));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("searchByTerm")
    public List<Transactions> searchByCommentOrCompanyNameFuzzy(@RequestParam(value = "search") String searchTerm){
        return transactionService.searchByCommentOrCompanyNameFuzzy(searchTerm);
    }

}