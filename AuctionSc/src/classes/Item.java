package src.classes;

public class Item {
    private String name;
    private double highestBid; // Changed to double for proper numerical operations
    private String endDate;
    private String description;

    public Item(String name, double highestBid, String endDate, String description) {
        this.name = name;
        this.highestBid = highestBid;
        this.endDate = endDate;
        this.description = description;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for highestBid
    public double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    // Getter and Setter for endDate
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
