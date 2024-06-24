package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.impl;

import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Transactions;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.repository.TransactionRepository;
import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service.ITransactionService;

import java.util.List;


@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void save(Transactions transactions) {
        transactionRepository.save(transactions);
    }

    public List<Transactions> findByUserId(String user_id) {
        return transactionRepository.findByUserId(user_id);
    }

}