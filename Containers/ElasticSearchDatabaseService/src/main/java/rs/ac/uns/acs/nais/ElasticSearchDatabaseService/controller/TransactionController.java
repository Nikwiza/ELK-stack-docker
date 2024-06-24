package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.controller;

import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Transactions;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl.ProductService;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions.json")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public void addTransaction(@RequestBody Transactions transactions) {
        transactionService.save(transactions);
    }

    @GetMapping("findByUserId")
    public List<Transactions> findByUserId(@RequestParam(value = "userId") String userId) {
        return transactionService.findByUserId(userId);
    }

}