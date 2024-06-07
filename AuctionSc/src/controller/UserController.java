package src.controller;

import java.sql.SQLException;
import src.model.UserDAO;
import src.model.ListingDAO;
import src.model.User;
import src.views.AdminDashboardView;
import src.views.BuyerDashboardUI;
import src.views.LoginView;
import src.views.RegistrationView;
import src.views.SellerDashboardView;

public class UserController {
    private UserDAO userDAO;
    private RegistrationView registrationView;
    private LoginView loginView;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setRegistrationView(RegistrationView view) {
        this.registrationView = view;
    }

    public void setLoginView(LoginView view) {
        this.loginView = view;
    }

    public String registerUser(String username, String email, String password, String confirmPassword, boolean termsAccepted, String userType) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || userType.isEmpty()) {
            return "All fields are required.";
        }
        if (!password.equals(confirmPassword)) {
            return "Passwords do not match.";
        }
        if (!termsAccepted) {
            return "You must accept the terms and conditions.";
        }

        try {
            userDAO.registerUser(username, email, password, userType);
            return "Registration successful.";
        } catch (SQLException e) {
            return "Error during registration: " + e.getMessage();
        }
    }

    public String authenticateUser(String email, String password) {
        try {
            User user = userDAO.authenticateUser(email, password);
            if (user != null) {
                switch (user.getUserType()) {
                    case "Admin":
                        new AdminDashboardView(user, this).setVisible(true);
                        break;
                    case "Seller":
                        new SellerDashboardView(user, new ListingController(new ListingDAO())).setVisible(true);
                        break;
                    case "Buyer":
                        BuyerDashboardUI buyerDashboard = new BuyerDashboardUI();
                        buyerDashboard.createAndShowGUI();
                        // System.out.println("Buyer dashboard should be visible now.");
                        break;
                    default:
                        return "Invalid user type.";
                }
                return "Login successful.";
            } else {
                return "Invalid email or password.";
            }
        } catch (SQLException e) {
            return "Error during authentication: " + e.getMessage();
        }
    }
    
}
