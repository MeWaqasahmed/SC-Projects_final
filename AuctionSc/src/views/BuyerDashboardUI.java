package src.views;

import src.classes.Item;
import src.controller.BuyerDashboardController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BuyerDashboardUI extends JPanel {
    private JFrame frame;
    private BuyerDashboardController controller;

    public BuyerDashboardUI() {
        controller = new BuyerDashboardController(this);
    }

    public void createAndShowGUI() {
        frame = new JFrame("Buyer Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(this);

        setLayout(new BorderLayout());

        // Create and add app bar panel
        BuyerAppBarPanel appBarPanel = new BuyerAppBarPanel();
        add(appBarPanel, BorderLayout.NORTH);

        // Create and add square panel with scroll pane
        List<Item> items = controller.fetchItems();
        SquarePanel squarePanelPanel = new SquarePanel(items);
        JScrollPane scrollPane = new JScrollPane(squarePanelPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        // Show frame
        frame.setVisible(true);
    }

    class BuyerAppBarPanel extends JPanel {
        public BuyerAppBarPanel() {
            setBackground(Color.LIGHT_GRAY);
            setLayout(new BorderLayout());

            // Create drawer button
            JButton drawerButton = new JButton("MENU");
            add(drawerButton, BorderLayout.WEST);

            // Create search bar
            JTextField searchBar = new JTextField();
            searchBar.setPreferredSize(new Dimension(200, 30));
            JButton searchButton = new JButton("Search");
            JPanel searchBarPanel = new JPanel();
            searchBarPanel.add(searchBar);
            searchBarPanel.add(searchButton);
            add(searchBarPanel, BorderLayout.CENTER);

            // Create profile button
            JButton profileButton = new JButton("Profile");
            add(profileButton, BorderLayout.EAST);

            // Add action listeners
            drawerButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "MENU Button Clicked"));

            searchButton.addActionListener(e -> {
                String searchText = searchBar.getText();
                JOptionPane.showMessageDialog(this, "Searching for: " + searchText);
            });

            profileButton.addActionListener(e -> {
                ProfileScreen profileScreen = new ProfileScreen();
                profileScreen.setVisible(true);
            });
        }
    }

    class SquarePanel extends JPanel {
        public SquarePanel(List<Item> items) {
            int rows = (items.size() + 4) / 5; // calculate number of rows needed
            setLayout(new GridLayout(rows, 5, 5, 5)); // flexible rows and 5 columns with gaps
            setBackground(Color.BLUE);

            // Add square panels
            for (Item item : items) {
                JPanel panel = createSquarePanel(item);
                add(panel);
            }
        }

        private JPanel createSquarePanel(Item item) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(250, 400));
            panel.setBackground(Color.ORANGE);
            panel.setLayout(new BorderLayout());

            // Upper section for picture
            JLabel pictureLabel = new JLabel(item.getName(), SwingConstants.CENTER);
            pictureLabel.setPreferredSize(new Dimension(80, 250));
            pictureLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(pictureLabel, BorderLayout.NORTH);

            // Lower section for text details
            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
            textPanel.setPreferredSize(new Dimension(80, 80));
            textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel highestBidLabel = new JLabel("Highest bid: " + item.getHighestBid());
            highestBidLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel endDateLabel = new JLabel("End date: " + item.getEndDate());
            endDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton placeBidButton = new JButton("Place Bid");
            placeBidButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel descriptionLabel = new JLabel("Description: " + item.getDescription());
            descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            textPanel.add(highestBidLabel);
            textPanel.add(endDateLabel);
            textPanel.add(placeBidButton);
            textPanel.add(descriptionLabel);

            panel.add(textPanel, BorderLayout.CENTER);

            // Add action listener for place bid button
            placeBidButton.addActionListener(e -> {
                String newBidString = JOptionPane.showInputDialog(frame, "Enter your bid:");
                if (newBidString != null && !newBidString.isEmpty()) {
                    try {
                        double newBid = Double.parseDouble(newBidString);
                        double currentHighestBid = controller.getHighestBid(item);
            
                        if (newBid > currentHighestBid) {
                            controller.updateHighestBid(item, newBid);
                            highestBidLabel.setText("Highest bid: " + newBid);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Your bid must be higher than the current highest bid.", "Invalid Bid", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Please enter a valid number for your bid.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            

            return panel;
        }
        // public static void main(String[] args) {
        //     SwingUtilities.invokeLater(() -> {
        //         BuyerDashboardUI buyerDashboardUI = new BuyerDashboardUI();
        //         buyerDashboardUI.createAndShowGUI();
        //     });
        // }
    }
}
