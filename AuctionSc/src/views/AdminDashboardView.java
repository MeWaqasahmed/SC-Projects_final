package src.views;

import src.controller.ListingController;
import src.controller.UserController;
import src.model.ListingDAO;
import src.model.User;
import src.model.UserDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboardView extends JFrame {
    private JTable listingsTable;
    private DefaultTableModel tableModel;
    private ListingController listingController;

    public AdminDashboardView(User user, UserController userController) {
        ListingDAO listingDAO = new ListingDAO();
        this.listingController = new ListingController(listingDAO);
        initUI(user);
    }

    private void initUI(User user) {
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add welcome message at the top
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.add(new JLabel("Welcome, " + user.getUsername() + "!"));
        welcomePanel.add(new JLabel("You are logged in as Admin."));
        mainPanel.add(welcomePanel, BorderLayout.NORTH);

        // Create table for listings
        String[] columnNames = {"Listing ID", "Title", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0);
        listingsTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(listingsTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        loadListings();

        // Create action buttons panel
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        
        JButton approveButton = new JButton("Approve");
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = listingsTable.getSelectedRow();
                if (selectedRow != -1) {
                    int listingId = (int) tableModel.getValueAt(selectedRow, 0);
                    approveListing(listingId);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a listing to approve.");
                }
            }
        });

        JButton rejectButton = new JButton("Reject");
        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = listingsTable.getSelectedRow();
                if (selectedRow != -1) {
                    int listingId = (int) tableModel.getValueAt(selectedRow, 0);
                    rejectListing(listingId);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a listing to reject.");
                }
            }
        });

        actionsPanel.add(approveButton);
        actionsPanel.add(rejectButton);

        mainPanel.add(actionsPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        add(mainPanel);
    }

    private void loadListings() {
        Object[][] listings = listingController.getListingsForReview();

        for (Object[] listing : listings) {
            tableModel.addRow(listing);
        }
    }

    private void approveListing(int listingId) {
        String message = listingController.approveListing(listingId);
        JOptionPane.showMessageDialog(this, message);
        refreshTable();
    }

    private void rejectListing(int listingId) {
        String message = listingController.rejectListing(listingId);
        JOptionPane.showMessageDialog(this, message);
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Clear the table
        loadListings(); // Reload the listings
    }

    // public static void main(String[] args) {
    //     // For testing purposes, create a sample user and show the AdminDashboardView
    //     UserDAO userDAO = new UserDAO();
    //     UserController userController = new UserController(userDAO);
    //     User sampleUser = new User("Admin", "admin@gmail.com", "Admin");
    //     AdminDashboardView adminDashboard = new AdminDashboardView(sampleUser, userController);
    //     adminDashboard.setVisible(true);
    // }
}
