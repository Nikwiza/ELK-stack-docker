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

    public List<Transactions> findByUserId(String user_id) {
        return transactionRepository.findByUserId(user_id);
    }

    //CRUD services

    public void save(Transactions transactions) {
        transactionRepository.save(transactions);
    }

    @Override
    public Iterable<Transactions> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transactions getTransactionById(String transaction_id) {
        return transactionRepository.findById(transaction_id).orElseThrow();
    }

    @Override
    public Transactions updateTransaction(Transactions transactions) {
        return transactionRepository.save(transactions);
    }

    @Override
    public void deleteById(String transaction_id) {
        transactionRepository.deleteById(transaction_id);
    }

}