package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "requests")
public class Requests {
    @Id
    private String requestId;
    private String userId;
    private String text;
    private Boolean approved;

    public Requests(String request_id, String user_id, String text, Boolean approved) {
        this.requestId = request_id;
        this.userId = user_id;
        this.text = text;
        this.approved = approved;
    }

    public Requests() {
    }

    public String getRequest_id() {
        return requestId;
    }

    public void setRequest_id(String request_id) {
        this.requestId = request_id;
    }

    public String getUser_id() {
        return userId;
    }

    public void setUser_id(String user_id) {
        this.userId = user_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
