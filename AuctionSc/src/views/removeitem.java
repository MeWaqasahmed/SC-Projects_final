package src.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class removeitem extends JFrame {
    private DefaultListModel<String> itemListModel;
    private JList<String> itemList;

    public removeitem() {
        setTitle("Auction Desktop Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create list model and populate with dummy data
        itemListModel = new DefaultListModel<>();
        itemListModel.addElement("Item 1");
        itemListModel.addElement("Item 2");
        itemListModel.addElement("Item 3");

        // Create JList with the model
        itemList = new JList<>(itemListModel);
        JScrollPane scrollPane = new JScrollPane(itemList);

        // Create remove button
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected index
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Remove selected item from the list model
                    itemListModel.remove(selectedIndex);
                }
            }
        });

        // Layout components
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(removeButton, BorderLayout.SOUTH);

        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new removeitem();
            }
        });
    }
}
