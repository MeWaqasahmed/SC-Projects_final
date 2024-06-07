package src.controller;

import src.database.DatabaseConnection;
import src.model.User;
import src.classes.Item;
import src.views.BuyerDashboardUI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuyerDashboardController {
    private BuyerDashboardUI ui;

    public BuyerDashboardController(BuyerDashboardUI buyerDashboardUI) {
        this.ui = buyerDashboardUI;
        createItemsTable();
        // addSampleData();
    }

    public List<Item> fetchItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                double highestBid = rs.getDouble("highest_bid");
                String endDate = rs.getString("end_date");
                String description = rs.getString("description");
                items.add(new Item(name, highestBid, endDate, description));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public void updateHighestBid(Item item, double newBid) {
        String sql = "UPDATE items SET highest_bid = ? WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, newBid);
            pstmt.setString(2, item.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createItemsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS items (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                     "name VARCHAR(255) NOT NULL," +
                     "highest_bid DOUBLE NOT NULL," +
                     "end_date DATE NOT NULL," +
                     "description TEXT NOT NULL)";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getHighestBid(Item item) {
        String sql = "SELECT highest_bid FROM items WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("highest_bid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0; // Default value if not found
    }
}
