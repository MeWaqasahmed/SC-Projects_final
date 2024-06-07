package src.classes;

public class Listing {
    private int id;
    private String title;
    private String description;
    private String status;
    private int sellerId;
    private int duration;

    // Full constructor
    public Listing(int id, String title, String description, String status, int sellerId, int duration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.sellerId = sellerId;
        this.duration = duration;
    }

    // Constructor for partial initialization
    public Listing(int id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.sellerId = 0; // default value or you can set it to a meaningful default
        this.duration = 0; // default value or you can set it to a meaningful default
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
