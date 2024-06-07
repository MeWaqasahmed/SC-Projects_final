package src.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/deskauctiondb"; // Replace with your actual database URL
    private static final String USER = "root"; // Replace with your actual database username
    private static final String PASSWORD = ""; // Replace with your actual database password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // public static void main(String[] args) {
    //     try (Connection connection = DatabaseConnection.getConnection()) {
    //         if (connection != null) {
    //             System.out.println("Connection to the database established successfully!");
    //         } else {
    //             System.out.println("Failed to establish connection to the database.");
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
}

