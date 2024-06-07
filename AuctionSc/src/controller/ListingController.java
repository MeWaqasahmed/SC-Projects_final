package src.controller;

import src.classes.*;
import src.model.ListingDAO;

import java.sql.SQLException;
import java.util.List;

public class ListingController {
    private ListingDAO listingDAO;

    public ListingController(ListingDAO listingDAO) {
        this.listingDAO = listingDAO;
    }

    public String rejectListing(int listingId) {
        try {
            listingDAO.rejectListing(listingId);
            return "Listing rejected successfully.";
        } catch (SQLException e) {
            return "Error rejecting listing: " + e.getMessage();
        }
    }

    public String approveListing(int listingId) {
        try {
            listingDAO.approveListing(listingId);
            return "Listing approved successfully.";
        } catch (SQLException e) {
            return "Error approving listing: " + e.getMessage();
        }
    }

    public Object[][] getListingsForReview() {
        try {
            List<Listing> listings = listingDAO.getListingsForReview();
            Object[][] data = new Object[listings.size()][4];
            for (int i = 0; i < listings.size(); i++) {
                Listing listing = listings.get(i);
                data[i][0] = listing.getId();
                data[i][1] = listing.getTitle();
                data[i][2] = listing.getDescription();
                data[i][3] = listing.getStatus();
            }
            return data;
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Error fetching listings for review: " + e.getMessage());
        }
    }

    public List<Listing> getListingsBySeller(int sellerId) throws SQLException {
        return listingDAO.getListingsBySeller(sellerId);
    }

    public void extendListingDuration(int listingId, int additionalDuration) {
        try {
            listingDAO.extendListingDuration(listingId, additionalDuration);
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Error extending listing duration: " + e.getMessage());
        }
    }

    public void restartListing(int listingId, int newDuration) {
        try {
            listingDAO.restartListing(listingId, newDuration);
        } catch (SQLException e) {
            throw new UnsupportedOperationException("Error restarting listing: " + e.getMessage());
        }
    }

    public void addListing(Listing listing) throws SQLException {
        listingDAO.addListing(listing);
    }
}
