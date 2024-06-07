package src.views;


import javax.swing.*;

import src.controller.UserController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductDetailView extends JFrame {
    private JLabel productNameLabel;
    private JLabel descriptionLabel;
    private JLabel sellerRatingLabel;
    private JLabel highestBidLabel;
    private JButton placeBidButton;
    private JTextField bidAmountField;
    private UserController userController;

    public ProductDetailView(UserController userController, String productName, String description, String sellerName) {
        this.userController = userController;
        setTitle("Product Detail");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        productNameLabel = new JLabel("Product: " + productName);
        descriptionLabel = new JLabel("<html>Description: " + description + "</html>");
        Seller seller = Seller.fetchSellerDetails(sellerName);
        sellerRatingLabel = new JLabel("Seller: " + seller.getName() + " (Rating: " + seller.getRating() + ")");
        Bid highestBid = Bid.fetchHighestBid(productName);
        highestBidLabel = new JLabel("Highest Bid: $" + highestBid.getAmount());

        placeBidButton = new JButton("Place Bid");
        bidAmountField = new JTextField(10);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        add(productNameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        add(descriptionLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        add(sellerRatingLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        add(highestBidLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        add(new JLabel("Your Bid:"), constraints);

        constraints.gridx = 1;
        add(bidAmountField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        add(placeBidButton, constraints);

        placeBidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeBid(productName);
            }
        });
    }

    private void placeBid(String productName) {
        String bidAmountText = bidAmountField.getText();
        try {
            double bidAmount = Double.parseDouble(bidAmountText);
            //String message = userController.placeBid(productName, bidAmount);
            //JOptionPane.showMessageDialog(this, message);
            //  if (message.contains("successful")) {
            //     Bid highestBid = Bid.fetchHighestBid(productName);
            //     highestBidLabel.setText("Highest Bid: $" + highestBid.getAmount());
            // }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid bid amount.");
        }
    }

    // public static void main(String[] args) {
    //     UserController userController = new UserController(new UserModel());
    //     ProductDetailView productDetailView = new ProductDetailView(userController, "Sample Product", "This is a sample product description.", "SampleSeller");
    //     SwingUtilities.invokeLater(() -> productDetailView.setVisible(true));
    // }
}
