package src.views;

import src.controller.UserController;
import src.model.UserDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationView extends JFrame {
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JCheckBox termsCheckBox;
    private JRadioButton adminRadioButton;
    private JRadioButton sellerRadioButton;
    private JRadioButton buyerRadioButton;
    private JLabel statusLabel;
    private UserController userController;

    public RegistrationView(UserController userController) {
        this.userController = userController;
        initUI();
    }

    private void initUI() {
        setTitle("User Registration");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordField = new JPasswordField();
        
        termsCheckBox = new JCheckBox("Accept Terms and Conditions");
        
        adminRadioButton = new JRadioButton("Admin");
        sellerRadioButton = new JRadioButton("Seller");
        buyerRadioButton = new JRadioButton("Buyer");
        ButtonGroup userTypeGroup = new ButtonGroup();
        userTypeGroup.add(adminRadioButton);
        userTypeGroup.add(sellerRadioButton);
        userTypeGroup.add(buyerRadioButton);
        
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });

        statusLabel = new JLabel();
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(usernameLabel)
                        .addComponent(emailLabel)
                        .addComponent(passwordLabel)
                        .addComponent(confirmPasswordLabel)
                        .addComponent(adminRadioButton)
                        .addComponent(sellerRadioButton)
                        .addComponent(buyerRadioButton)
                        .addComponent(registerButton))
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(usernameField)
                        .addComponent(emailField)
                        .addComponent(passwordField)
                        .addComponent(confirmPasswordField)
                        .addComponent(termsCheckBox)
                        .addComponent(loginButton)))
                .addComponent(statusLabel)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmPasswordLabel)
                    .addComponent(confirmPasswordField))
                .addComponent(termsCheckBox)
                .addComponent(adminRadioButton)
                .addComponent(sellerRadioButton)
                .addComponent(buyerRadioButton)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(registerButton)
                    .addComponent(loginButton))
                .addComponent(statusLabel)
        );

        add(panel);
    }

    private void loginUser() {
        this.setVisible(false); // Hide the registration view
        UserDAO userDAO = new UserDAO();
        UserController userController = new UserController(userDAO);
        LoginView loginView = new LoginView(userController);
        userController.setLoginView(loginView);
        loginView.setVisible(true);
    }

    private void registerUser() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        boolean termsAccepted = termsCheckBox.isSelected();
        String userType = "";

        if (adminRadioButton.isSelected()) {
            userType = "Admin";
        } else if (sellerRadioButton.isSelected()) {
            userType = "Seller";
        } else if (buyerRadioButton.isSelected()) {
            userType = "Buyer";
        } else {
            statusLabel.setText("Please select user type.");
            return;
        }

        String message = userController.registerUser(username, email, password, confirmPassword, termsAccepted, userType);
        statusLabel.setText(message);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
