import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.Comparator;

import java.util.ArrayList;
import java.awt.event.ActionListener;
// import java.util.concurrent.Flow;

public class GUI extends JFrame implements ActionListener {
    
    private ArrayList<User> usersList; // ArrayList to hold all of the people

    String[] beforeColumnNames = new String[]{"Name", "Item Price"};
    String[] afterColumnNames = new String[]{"Name", "Total Owed", "Analysis"};

    private JComboBox<String> sortBox;
    private DefaultTableModel dtm;
    private DefaultTableModel newDtm;
    private JTable table;
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel sortLabel;
    private JButton finishButton;
    private JButton addButton;
    private JButton deleteButton;
    private JButton retryButton;
    private JTextField nameInput;
    private JTextField priceInput;

    public GUI() {

        super("Simple GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(500, 400); // Increased window size
        setLocationRelativeTo(null); // Center the window
        
        //Youtube Tutorial
        dtm = new DefaultTableModel(beforeColumnNames, 0);
        table = new JTable();
        table.setModel(dtm);
        
        //Initialize the ArrayList
        usersList = new ArrayList<>();

        //Set something up
        sortBox = new JComboBox<>(new String[]{"Name", "Amount owed"});
        sortBox.setVisible(true);

        //Create the button
        addButton = new JButton("Enter"); 
        addButton.setPreferredSize(new Dimension(100, 50));
        retryButton = new JButton("Retry");
        retryButton.setPreferredSize(new Dimension(100,50));
        finishButton = new JButton("Finish");
        finishButton.setPreferredSize(new Dimension(100, 50));
        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100,50));

        addButton.addActionListener(this);
        retryButton.addActionListener(this);
        finishButton.addActionListener(this);
        deleteButton.addActionListener(this);
        sortBox.addActionListener(this);
        //Labels
        nameLabel = new JLabel("Name: ");
        priceLabel = new JLabel("Price: ");
        sortLabel = new JLabel("Sort by: ");

        //Extra stuff
        JPanel inputPanel = new JPanel(new FlowLayout()); // Use FlowLayout for simplicity
        nameInput = new JTextField(15);
        nameInput.setToolTipText("Enter a name"); // Tooltip
        priceInput = new JTextField(8);
        priceInput.setToolTipText("Enter the price of the item");
        inputPanel.add(nameLabel);
        inputPanel.add(nameInput);
        inputPanel.add(priceLabel);
        inputPanel.add(priceInput);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(sortLabel);
        inputPanel.add(sortBox);
        sortLabel.setVisible(false);
        sortBox.setVisible(false);
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(finishButton);
        bottomPanel.add(retryButton);
        retryButton.setVisible(false);

        setLayout(new BorderLayout());

        add(inputPanel, BorderLayout.NORTH); // Input at the top
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH); // Finish button on bottom
        setVisible(true); 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Category 1: Code Correctness & Implementation (Event handling logic)
        // Category 3: Algorithms & Data Structures (Using ArrayList methods)

        if (e.getSource() == addButton) {
            addUser();
        } else if (e.getSource() == finishButton) {
            if (dtm.getRowCount() < 1) {
                JOptionPane.showMessageDialog(this, "Unable to calculate. Please enter at least one entry.", "Input Error", JOptionPane.WARNING_MESSAGE);
            } else {
                displayResults();
                sortBox.setVisible(true);
            }
        } else if (e.getSource() == retryButton) {
            resetScreen();
        
        } else if (e.getSource() == deleteButton) {
            deleteEntry();
        } else if (e.getSource() == sortBox) {
            sortEntries();
        }
    }

    private void resetScreen() {
        dtm.setRowCount(0);
        usersList.clear();
        table.setModel(dtm);
        addButton.setVisible(true);
        finishButton.setVisible(true);
        retryButton.setVisible(false);
        nameInput.setVisible(true);
        deleteButton.setVisible(true);
        priceInput.setVisible(true);
        nameLabel.setVisible(true);
        priceLabel.setVisible(true);
        sortLabel.setVisible(false);
        sortBox.setVisible(false);
    }
    //Finish this later
    private void sortEntries() {
        String selectedType = (String)sortBox.getSelectedItem();
        if (selectedType.equals("Name")) {
            Collections.sort(usersList, new Comparator<User>() {
                public int compare(User u1, User u2) {
                    return u1.getName().compareTo(u2.getName());
                }
            });
            newDtm = new DefaultTableModel(afterColumnNames, 0);
            for (User user : usersList) {
                String[] finalAllUsers = {user.getName(), user.getStringPrice(), user.funnyMessage()};
                newDtm.addRow(finalAllUsers);
            }            
            table.setModel(newDtm);

            System.out.println(usersList);
            
        } else if (selectedType.equals("Amount owed")) {
            Collections.sort(usersList, new Comparator<User>() {
                public int compare(User u1, User u2) {
                    return Double.compare(u1.getPrice(), u2.getPrice());
                }
            });
            newDtm = new DefaultTableModel(afterColumnNames, 0);
            for (User user : usersList) {
                String[] finalAllUsers = {user.getName(), user.getStringPrice(), user.funnyMessage()};
                newDtm.addRow(finalAllUsers);
            }
            table.setModel(newDtm);
            System.out.println(usersList);

        }
    }
    private void addUser() {
        String username = "";
        Double price = 0.0;   
        boolean userExists = false;

        if (!priceInput.getText().isEmpty()) { // Borrowed Mr Crow's code
            User newUser = null;
            try {
                username = nameInput.getText().trim();
                price = Double.parseDouble(priceInput.getText().trim());
                if (price > 0 && price < 15) {
                    
                    if (!userExists) {
                        newUser = new LightSpender(username, price);
                        usersList.add(newUser);
                    }

                } else if (price >= 15 && price < 40) {

                    if (!userExists) {
                        newUser = new AverageSpender(username, price);
                        usersList.add(newUser);
                    }

                } else if (price >= 40) {
                    if (!userExists) {
                        newUser = new BigSpender(username, price);
                        usersList.add(newUser);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid value. Please enter a number.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return; // Stop adding task if priority is invalid
            }
        }

        updateScreen();
        nameInput.setText(""); // Clear input fields
        priceInput.setText(""); // Clear input fields
    }
    private void deleteEntry() {
        int selectedIndex = table.getSelectedRow();

        if (selectedIndex >= 0) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Delete this entry", getTitle(), dialogButton);
            if (dialogResult == 0) {
                ((DefaultTableModel)table.getModel()).removeRow(selectedIndex);
                usersList.remove(selectedIndex);
                updateScreen();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an entry to delete.", "Selection Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void displayResults() {
        nameLabel.setVisible(false);
        priceLabel.setVisible(false);
        sortLabel.setVisible(true);
        sortBox.setVisible(true);
        for (int i = 0; i < usersList.size(); i++) {
            User user = usersList.get(i);
            for (int j = i + 1; j < usersList.size(); j++) {
                User currentUser = usersList.get(j);
                // Check for duplicates by comparing names
                if (user.getName().equalsIgnoreCase(currentUser.getName())) {
                    // Increase the price of the first user
                    user.increasePrice(currentUser.getPrice());
                    // Remove the duplicate user
                    usersList.remove(j);
                    // Adjust the loop counter since we've removed an element
                    j--; 
                }
            }
        }
        newDtm = new DefaultTableModel(afterColumnNames, 0);
        for (User user : usersList) {
            String[] finalAllUsers = {user.getName(), user.getStringPrice(), user.funnyMessage()};
            newDtm.addRow(finalAllUsers);
        }
        table.setModel(newDtm);
        finishButton.setVisible(false);
        addButton.setVisible(false);
        retryButton.setVisible(true);  
        deleteButton.setVisible(false);
        nameInput.setVisible(false);
        priceInput.setVisible(false);
    }

    private void updateScreen() {

        dtm.setRowCount(0);
        for (User user : usersList) {
            String[] allUsers = {user.getName(), user.getStringPrice()};
            dtm.addRow(allUsers);
        }  

    }
    public static void main(String[] args) { 
        new GUI();
    }
}
