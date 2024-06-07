package src.views;


public class Seller {
    private String name;
    private double rating;

    public Seller(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    // Simulate fetching seller details from a database
    public static Seller fetchSellerDetails(String sellerName) {
        // Dummy data for demonstration purposes
        return new Seller(sellerName, 4.5);
    }
}
