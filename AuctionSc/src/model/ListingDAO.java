package src.model;

import src.classes.Listing;
import src.database.DatabaseConnection;

import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListingDAO {

    public void approveListing(int listingId) throws SQLException {
        String UPDATE_LISTING_STATUS_SQL = "UPDATE listings SET status = 'Approved' WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LISTING_STATUS_SQL)) {
            preparedStatement.setInt(1, listingId);
            preparedStatement.executeUpdate();
        }
    }

    public void rejectListing(int listingId) throws SQLException {
        String UPDATE_LISTING_STATUS_SQL = "UPDATE listings SET status = 'Rejected' WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LISTING_STATUS_SQL)) {
            preparedStatement.setInt(1, listingId);
            preparedStatement.executeUpdate();
        }
    }

    public List<Listing> getListingsForReview() throws SQLException {
        String SELECT_LISTINGS_FOR_REVIEW_SQL = "SELECT id, title, description, status FROM listings WHERE status = 'Pending'";
        List<Listing> listings = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LISTINGS_FOR_REVIEW_SQL);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String status = rs.getString("status");
                listings.add(new Listing(id, title, description, status));
            }
        }

        return listings;
    }

    public List<Listing> getListingsBySeller(int sellerId) throws SQLException {
        String SELECT_LISTINGS_BY_SELLER_SQL = "SELECT * FROM listings WHERE seller_id = ?";
        List<Listing> listings = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LISTINGS_BY_SELLER_SQL)) {
            preparedStatement.setInt(1, sellerId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String status = rs.getString("status");
                int duration = rs.getInt("duration");
                listings.add(new Listing(id, title, description, status, sellerId, duration));
            }
        }

        return listings;
    }

    public void extendListingDuration(int listingId, int additionalDuration) throws SQLException {
        String UPDATE_LISTING_DURATION_SQL = "UPDATE listings SET duration = duration + ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LISTING_DURATION_SQL)) {
            preparedStatement.setInt(1, additionalDuration);
            preparedStatement.setInt(2, listingId);
            preparedStatement.executeUpdate();
        }
    }

    public void restartListing(int listingId, int newDuration) throws SQLException {
        String UPDATE_LISTING_RESTART_SQL = "UPDATE listings SET duration = ?, status = 'Pending' WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LISTING_RESTART_SQL)) {
            preparedStatement.setInt(1, newDuration);
            preparedStatement.setInt(2, listingId);
            preparedStatement.executeUpdate();
        }
    }

    public void addListing(Listing listing) throws SQLException {
        String INSERT_LISTING_SQL = "INSERT INTO listings (title, description, status, seller_id, duration) VALUES (?, ?, 'Pending', ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LISTING_SQL)) {
            preparedStatement.setString(1, listing.getTitle());
            preparedStatement.setString(2, listing.getDescription());
            preparedStatement.setInt(3, listing.getSellerId());
            preparedStatement.setInt(4, listing.getDuration());
            preparedStatement.executeUpdate();
        }
    }
}
