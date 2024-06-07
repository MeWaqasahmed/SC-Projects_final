package src.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateAccountInfoScreen extends JFrame {
    private JTextField nameField, idField, emailField,passwordField;

    public UpdateAccountInfoScreen() {
        // Set up the frame
        setTitle("Update Account Information");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // Add the heading
        JLabel headingLabel = new JLabel("Update Account Information", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headingLabel, gbc);

        // Add the name label and text field
        JLabel nameLabel = new JLabel("Name:");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(nameLabel, gbc);

        nameField = new JTextField();
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        
        // Add the phone label and text field
        JLabel passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);

         passwordField = new JTextField();
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Add the email label and text field
        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(emailLabel, gbc);

        emailField = new JTextField();
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        JLabel idLabel = new JLabel("ID:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(idLabel, gbc);

        idField = new JTextField();
        gbc.gridx = 1;
        panel.add(idField, gbc);

        // Add the update button
        JButton updateButton = new JButton("Update");
        updateButton.setBackground(Color.green);
        updateButton.setForeground(Color.black);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(updateButton, gbc);

        // Add action listener for the update button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle update button click event
                String name = nameField.getText();
                String password = passwordField.getText();
                String email = emailField.getText();
                String id = idField.getText();
                // Display a success message (or handle update logic)
                JOptionPane.showMessageDialog(UpdateAccountInfoScreen.this, "Account information updated successfully!\n"
                        + "Name: " + name + "\n"
                        +"ID: " + id + "\n"
                        + "Phone: " + password + "\n"
                        + "Email: " + email);
            }
        });

        // Add the panel to the frame
        add(panel);
    }

    public static void main(String[] args) {
        // Run the update account information screen
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UpdateAccountInfoScreen().setVisible(true);
            }
        });
    }
}

