package src.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentScreen extends JFrame {
    public PaymentScreen() {
        // Set up the frame
        setTitle("Payment");
        setSize(400, 400);
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
        JLabel headingLabel = new JLabel("Payment", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headingLabel, gbc);

        // Add the payment method label and combo box
        JLabel paymentMethodLabel = new JLabel("Payment Method:");
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(paymentMethodLabel, gbc);

        String[] paymentMethods = {"Credit Card", "Debit Card", "PayPal"};
        JComboBox<String> paymentMethodComboBox = new JComboBox<>(paymentMethods);
        gbc.gridx = 1;
        panel.add(paymentMethodComboBox, gbc);

        // Add the account holder name label and text field
        JLabel accountHolderLabel = new JLabel("Account Holder Name:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(accountHolderLabel, gbc);

        JTextField accountHolderTextField = new JTextField();
        gbc.gridx = 1;
        panel.add(accountHolderTextField, gbc);

        // Add the account number label and text field
        JLabel accountNumberLabel = new JLabel("Account Number:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(accountNumberLabel, gbc);

        JTextField accountNumberTextField = new JTextField();
        gbc.gridx = 1;
        panel.add(accountNumberTextField, gbc);

        // Add the card number label and text field
        JLabel cardNumberLabel = new JLabel("Card Number:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(cardNumberLabel, gbc);

        JTextField cardNumberTextField = new JTextField();
        gbc.gridx = 1;
        panel.add(cardNumberTextField, gbc);

        // Add the card expiry date label and text field
        JLabel cardExpiryDateLabel = new JLabel("Card Expiry Date:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(cardExpiryDateLabel, gbc);

        JTextField cardExpiryDateTextField = new JTextField();
        gbc.gridx = 1;
        panel.add(cardExpiryDateTextField, gbc);

        // Add the payment button
        JButton paymentButton = new JButton("Confirm");
        paymentButton.setBackground(Color.GREEN);
        paymentButton.setForeground(Color.black);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(paymentButton, gbc);

        // Add action listener for the payment button
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle payment button click event
                JOptionPane.showMessageDialog(PaymentScreen.this, "Payment processed successfully!");
            }
        });

        // Add the panel to the frame
        add(panel);
    }

    public static void main(String[] args) {
        // Run the payment screen
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaymentScreen().setVisible(true);
            }
        });
    }
}
