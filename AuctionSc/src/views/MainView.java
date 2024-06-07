package src.views;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    public MainView() {
        setTitle("Main View");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create app bar panel
        JPanel appBarPanel = new JPanel();
        appBarPanel.setBackground(Color.LIGHT_GRAY);
        appBarPanel.setLayout(new BorderLayout());

        // Create drawer button
        JButton drawerButton = new JButton("MENU");
        appBarPanel.add(drawerButton, BorderLayout.WEST);

        // Create search bar
        JTextField searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(200, 30));
        JButton searchButton = new JButton("Search");
        JPanel searchBarPanel = new JPanel();
        searchBarPanel.add(searchBar);
        searchBarPanel.add(searchButton);
        appBarPanel.add(searchBarPanel, BorderLayout.CENTER);

        // Create profile button
        JButton profileButton = new JButton("Profile");
        appBarPanel.add(profileButton, BorderLayout.EAST);

        // Add app bar to main panel
        mainPanel.add(appBarPanel, BorderLayout.NORTH);

        // Create panel for square panels
        JPanel squarePanelPanel = new JPanel();
        squarePanelPanel.setLayout(new GridLayout(10, 5, 5, 5)); // 10 rows and 5 columns with gaps
        squarePanelPanel.setBackground(Color.BLUE);

        // Add square panels
        for (int i = 0; i < 50; i++) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(250, 400));
            panel.setBackground(Color.ORANGE);
            panel.setLayout(new BorderLayout());

            // Upper section for picture
            JLabel pictureLabel = new JLabel("Picture " + (i + 1), SwingConstants.CENTER);
            pictureLabel.setPreferredSize(new Dimension(80, 250));
            pictureLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(pictureLabel, BorderLayout.NORTH);

            // Lower section for text details
            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
            textPanel.setPreferredSize(new Dimension(80, 80));
            textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel highestBidLabel = new JLabel("Highest bid: ");
            highestBidLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel endDateLabel = new JLabel("End date: ");
            endDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton placeBidButton = new JButton("Place Bid");
            placeBidButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            JLabel descriptionLabel = new JLabel("Description:");
            descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            textPanel.add(highestBidLabel);
            textPanel.add(endDateLabel);
            textPanel.add(placeBidButton);
            textPanel.add(descriptionLabel);

            panel.add(textPanel, BorderLayout.CENTER);

            // Add action listener for place bid button
            placeBidButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Open the BidScreen window
                    BidScreen bidScreen = new BidScreen();
                    bidScreen.setVisible(true);
                }
            });

            squarePanelPanel.add(panel);
        }

        // Add square panel panel to main panel with scrollbar
        JScrollPane scrollPane = new JScrollPane(squarePanelPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add main panel to frame
        add(mainPanel);

        // Example action listeners
        drawerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MainView.this, "MENU Button Clicked");
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchBar.getText();
                JOptionPane.showMessageDialog(MainView.this, "Searching for: " + searchText);
            }
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProfileScreen profileScreen = new ProfileScreen();
                profileScreen.setVisible(true);
            }
        });
    }
}
