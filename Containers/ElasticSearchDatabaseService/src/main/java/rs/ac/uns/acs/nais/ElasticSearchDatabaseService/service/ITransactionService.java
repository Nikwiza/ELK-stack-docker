package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.service;


import rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model.Transactions;

import java.util.List;

public interface ITransactionService {

    List<Transactions> findByUserId(String user_id);

    Iterable<Transactions> getAllTransactions();
    Transactions getTransactionById(String transaction_id);
    Transactions updateTransaction(Transactions transactions);
    void deleteById(String transaction_id);

}
