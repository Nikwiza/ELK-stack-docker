package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "transaction")
public class Transaction {
    @Id
    private String transactionId;
    private Float amount;
    private String userId;
    private String comment;
    private String company_name;
    private Boolean sent;

    public Transaction(String transactionId, String userId, String comment, String company_name, Boolean sent, Float amount) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.comment = comment;
        this.company_name = company_name;
        this.sent = sent;
        this.amount = amount;
    }

    public Transaction() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String name) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Boolean isSent() {
        return sent;
    }

    public void setIsSent(Boolean sent) {
        this.sent = sent;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

}
