package src.views;

import src.controller.UserController;
import src.model.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel statusLabel;
    private UserController userController;

    public LoginView(UserController userController) {
        this.userController = userController;
        initUI();
    }

    private void initUI() {
        setTitle("User Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });
        panel.add(loginButton);

        statusLabel = new JLabel();
        panel.add(statusLabel);

        add(panel);
    }

    private void authenticateUser() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        String message = userController.authenticateUser(email, password);
        statusLabel.setText(message);
    }

    // public static void main(String[] args) {
    //     UserDAO userDAO = new UserDAO();
    //     UserController userController = new UserController(userDAO);
    //     LoginView view = new LoginView(userController);
    //     userController.setLoginView(view);
    //     view.setVisible(true);
    // }
}
