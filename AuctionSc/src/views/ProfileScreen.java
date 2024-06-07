package src.views;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileScreen extends JFrame {
    private JTextField idField, nameField, passwordField, emailField;
    private UpdateAccountInfoScreen updateAccountInfoScreen;

    public ProfileScreen() {
        setTitle("Profile Screen");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel headingLabel = new JLabel("User Information");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and size
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns
        panel.add(headingLabel, gbc);

        JLabel idLabel = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset grid width
        panel.add(idLabel, gbc);

        idField = new JTextField();
        gbc.gridx = 1;
        panel.add(idField, gbc);

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(nameLabel, gbc);

        nameField = new JTextField();
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(passwordLabel, gbc);

        passwordField = new JTextField();
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(emailLabel, gbc);

        emailField = new JTextField();
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        JButton updateButton = new JButton("Update Info");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(updateButton, gbc);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (updateAccountInfoScreen == null) {
                    // Create an instance of your existing UpdateAccountInfoScreen if not already instantiated
                    updateAccountInfoScreen = new UpdateAccountInfoScreen();
                }
                // Make the UpdateAccountInfoScreen visible
                updateAccountInfoScreen.setVisible(true);
                // Dispose the ProfileScreen window
                dispose();
            }
        });

        add(panel);
    }

    // Example UpdateAccountInfoScreen class (you need to implement this)
    // Replace this with your implementation of UpdateAccountInfoScreen
    

    public static void main(String[] args) {
        // Create an instance of ProfileScreen
        ProfileScreen profileScreen = new ProfileScreen();
        // Make the ProfileScreen visible
        profileScreen.setVisible(true);
    }
}
