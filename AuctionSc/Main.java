

import javax.swing.SwingUtilities;

import src.controller.UserController;
import src.model.UserDAO;
import src.views.BuyerDashboardUI;
import src.views.LoginView;
import src.views.RegistrationView;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        UserController userController = new UserController(userDAO);

        RegistrationView registrationView = new RegistrationView(userController);
        userController.setRegistrationView(registrationView);
        registrationView.setVisible(true);
        

        // LoginView loginView = new LoginView(userController);
        // userController.setLoginView(loginView);
        // loginView.setVisible(true);
    }
    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         BuyerDashboardUI buyerDashboardUI = new BuyerDashboardUI();
    //         buyerDashboardUI.createAndShowGUI();
    //     });
    // }
}
