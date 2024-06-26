package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "transactions")
public class Transactions {
    @Id
    private String transactionId;
    private String userId;
    private Float amount;
    private String comment;
    private String company_name;
    private Boolean sent;

    public Transactions() {
    }

    public Transactions(String transaction_id, String user_id, Float amount, String company_name, String comment, Boolean sent) {
        this.transactionId = transaction_id;
        this.userId = user_id;
        this.amount = amount;
        this.company_name = company_name;
        this.comment = comment;
        this.sent = sent;
    }

    public String getTransaction_id() {
        return transactionId;
    }

    public void setTransaction_id(String transaction_id) {
        this.transactionId = transaction_id;
    }

    public String getUser_id() {
        return userId;
    }

    public void setUser_id(String user_id) {
        this.userId = user_id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
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

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }
}
