package src.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BidScreen extends JFrame {
    private JTextField bidAmountField;

    public BidScreen() {
        // Set up the frame
        setTitle("Item Bidding");
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
        JLabel headingLabel = new JLabel("Item Bidding", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headingLabel, gbc);

        // Add the bid amount label and text field
        JLabel bidAmountLabel = new JLabel("Bid Amount:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(bidAmountLabel, gbc);

        bidAmountField = new JTextField();
        gbc.gridx = 1;
        panel.add(bidAmountField, gbc);

        // Add the place bid button
        JButton placeBidButton = new JButton("Place Bid");
        placeBidButton.setBackground(Color.GREEN);
        placeBidButton.setForeground(Color.black);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(placeBidButton, gbc);

        // Add action listener for the place bid button
        placeBidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle place bid button click event
                String bidAmount = bidAmountField.getText();

                // Display a success message (or handle bid placement logic)
                JOptionPane.showMessageDialog(BidScreen.this, "Bid placed successfully!\n"
                        + "Bid Amount: " + bidAmount);
            }
        });

        // Add the panel to the frame
        add(panel);
    }

    // public static void main(String[] args) {
    //     // Run the bid screen
    //     SwingUtilities.invokeLater(new Runnable() {
    //         @Override
    //         public void run() {
    //             new BidScreen().setVisible(true);
    //         }
    //     });
    // }
}
