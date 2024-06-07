package src.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuctionDesktopApp extends JFrame {
    private JTextField searchField;
    private JTextArea searchResultsArea;

    public AuctionDesktopApp() {
        setTitle("Auction Desktop Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        JLabel searchLabel = new JLabel("Enter search query:");
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(80, 60)); // Set button size
        searchResultsArea = new JTextArea(10, 30);
        searchResultsArea.setEditable(false);

        // Add action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                // Call method to perform search with the query
                searchItems(query);
            }
        });

        // Layout components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel searchPanel = new JPanel();
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(searchResultsArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }

    // Method to simulate search functionality
    private void searchItems(String query) {
        // Here, you would implement the actual search logic, such as querying a database
        // For simplicity, let's just display the search query in the search results area
        searchResultsArea.setText("Searching for: " + query);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AuctionDesktopApp();
            }
        });
    }
}
