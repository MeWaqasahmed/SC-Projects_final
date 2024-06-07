package src.views;

import src.controller.ListingController;
import src.classes.Listing;
import src.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class SellerDashboardView extends JFrame {
    private JTextField titleField;
    private JTextField descriptionField;
    private JTextField durationField;
    private JTable listingsTable;
    private DefaultTableModel tableModel;
    private ListingController listingController;
    private User user;

    public SellerDashboardView(User user, ListingController listingController) throws SQLException {
        this.user = user;
        this.listingController = listingController;
        initUI();
    }

    private void initUI() throws SQLException {
        setTitle("Seller Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Welcome message
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.add(new JLabel("Welcome, " + user.getUsername() + "!"));
        welcomePanel.add(new JLabel("You are logged in as Seller."));
        mainPanel.add(welcomePanel, BorderLayout.NORTH);

        // Add Listing Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Add New Listing"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Title:"), gbc);

        gbc.gridx = 1;
        titleField = new JTextField(20);
        formPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Description:"), gbc);

        gbc.gridx = 1;
        descriptionField = new JTextField(20);
        formPanel.add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Duration (days):"), gbc);

        gbc.gridx = 1;
        durationField = new JTextField(20);
        formPanel.add(durationField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addButton = new JButton("Add Listing");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addListing();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        formPanel.add(addButton, gbc);
        mainPanel.add(formPanel, BorderLayout.WEST);

        // Listings Table
        String[] columnNames = {"Listing ID", "Title", "Description", "Duration", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        listingsTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(listingsTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Actions Panel
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton extendButton = new JButton("Extend");
        extendButton.setPreferredSize(new Dimension(100, 40));
        extendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = listingsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int listingId = (int) tableModel.getValueAt(selectedRow, 0);
                    try {
                        extendListing(listingId);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    showMessage("Please select a listing to extend.");
                }
            }
        });

        JButton restartButton = new JButton("Restart");
        restartButton.setPreferredSize(new Dimension(100, 40));
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = listingsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int listingId = (int) tableModel.getValueAt(selectedRow, 0);
                    int currentDuration = (int) tableModel.getValueAt(selectedRow, 3);
                    try {
                        restartListing(listingId, currentDuration);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    showMessage("Please select a listing to restart.");
                }
            }
        });

        actionsPanel.add(extendButton);
        actionsPanel.add(restartButton);
        mainPanel.add(actionsPanel, BorderLayout.SOUTH);

        loadListings();

        add(mainPanel);
    }

    private void addListing() throws SQLException {
        String title = titleField.getText();
        String description = descriptionField.getText();
        int duration;
        try {
            duration = Integer.parseInt(durationField.getText());
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid duration.");
            return;
        }

        Listing listing = new Listing(0, title, description, "Pending", user.getId(), duration); // Create a Listing object
        listingController.addListing(listing); // Call the method to add the listing

        showMessage("Listing added successfully. Waiting for admin approval.");
        refreshTable();
    }

    private void loadListings() throws SQLException {
        List<Listing> listings = listingController.getListingsBySeller(user.getId());

        for (Listing listing : listings) {
            Object[] row = new Object[5];
            row[0] = listing.getId();
            row[1] = listing.getTitle();
            row[2] = listing.getDescription();
            row[3] = listing.getDuration();
            row[4] = listing.getStatus();
            tableModel.addRow(row);
        }
    }

    private void extendListing(int listingId) throws SQLException {
        String input = JOptionPane.showInputDialog(this, "Enter additional duration (days):");
        int additionalDuration;
        try {
            additionalDuration = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid number.");
            return;
        }

        listingController.extendListingDuration(listingId, additionalDuration);
        showMessage("Listing duration extended.");
        refreshTable();
    }

    private void restartListing(int listingId, int currentDuration) throws SQLException {
        String input = JOptionPane.showInputDialog(this, "Enter new duration (days):", currentDuration);
        int newDuration;
        try {
            newDuration = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showMessage("Please enter a valid number.");
            return;
        }

        listingController.restartListing(listingId, newDuration);
        showMessage("Listing restarted with new duration.");
        refreshTable();
    }

    private void refreshTable() throws SQLException {
        tableModel.setRowCount(0); // Clear the table
        loadListings(); // Reload the listings
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
