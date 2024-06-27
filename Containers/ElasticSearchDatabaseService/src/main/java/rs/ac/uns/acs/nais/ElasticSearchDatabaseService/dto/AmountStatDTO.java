package rs.ac.uns.acs.nais.ElasticSearchDatabaseService.dto;

public class AmountStatDTO {
    private String userId;
    private double avgAmount;

    // getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAvgAmount() {
        return avgAmount;
    }

    public void setAvgAmount(double avgAmount) {
        this.avgAmount = avgAmount;
    }
}
