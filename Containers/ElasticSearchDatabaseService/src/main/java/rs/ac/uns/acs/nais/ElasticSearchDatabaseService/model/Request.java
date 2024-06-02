package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "request")
public class Request {
    @Id
    private String request_id;
    private String user_id;
    private String text;
    private Boolean approved;

    public Request(String request_id, String user_id, String text, Boolean approved) {
        this.request_id = request_id;
        this.user_id = user_id;
        this.text = text;
        this.approved = approved;
    }

    public Request() {
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String description) {
        this.text = text;
    }

    public Boolean isApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

}
