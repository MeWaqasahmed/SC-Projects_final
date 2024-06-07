package src.views;


import java.util.Date;

public class Bid {
    private String bidder;
    private double amount;
    private Date bidTime;

    public Bid(String bidder, double amount, Date bidTime) {
        this.bidder = bidder;
        this.amount = amount;
        this.bidTime = bidTime;
    }

    public String getBidder() {
        return bidder;
    }

    public double getAmount() {
        return amount;
    }

    public Date getBidTime() {
        return bidTime;
    }

    // Simulate fetching highest bid from a database
    public static Bid fetchHighestBid(String productId) {
        // Dummy data for demonstration purposes
        return new Bid("user123", 150.0, new Date());
    }
}
