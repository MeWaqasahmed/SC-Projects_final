package src.model;

import src.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public void registerUser(String username, String email, String password, String userType) throws SQLException {
        String INSERT_USERS_SQL = "INSERT INTO users (username, email, password, user_type) VALUES (?, ?, ?, ?);";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, userType);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
            throw e;
        }
    }

    public User authenticateUser(String email, String password) throws SQLException {
        String SELECT_USER_SQL = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
    
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String userType = rs.getString("user_type");
                return new User(id, username, email, userType);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error during authentication: " + e.getMessage());
            throw e;
        }
    }
    


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
