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

    private double totalPrice;
    private double tip;
    private int tip_percent;
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

        super("Who Owes What?");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(1000, 400); // Increased window size
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
        addButton.setPreferredSize(new Dimension(100, 40));
        retryButton = new JButton("Retry");
        retryButton.setPreferredSize(new Dimension(100,50));
        finishButton = new JButton("Finish");
        finishButton.setPreferredSize(new Dimension(100, 50));
        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(100,40));

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
                optionalTip();
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
    //Resets the screen to its default state
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
    //Sorts the entries in the list by either name or amount owed
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

        }
    }
    private void addUser() {
        String username = nameInput.getText().trim();
        String stringPrice = priceInput.getText().trim();   

        if (username.isEmpty() && stringPrice.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a name and price.", "User Error", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a name", "User Error", JOptionPane.WARNING_MESSAGE);
            return;           
        } else if (stringPrice.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a price", "User Error", JOptionPane.WARNING_MESSAGE);
            return;  
        }
// Borrowed Mr Crow's code
            User newUser = null;
            try {
                double price = Double.parseDouble(stringPrice);
                if (price > 0 && price < 15) {               
                    newUser = new LightSpender(username, price);
                    usersList.add(newUser);
                } else if (price >= 15 && price < 40) {
                    newUser = new AverageSpender(username, price);
                    usersList.add(newUser);

                } else if (price >= 40) {
                    newUser = new BigSpender(username, price);
                    usersList.add(newUser);
                    
                }
               
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid value. Please enter a number.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return; // Stop adding task if priority is invalid
            }
        

        updateScreen();
        nameInput.setText(""); // Clear input fields
        priceInput.setText(""); // Clear input fields
    }
    private void optionalTip() {
        int yes_no = JOptionPane.showConfirmDialog(this, "Would you look to add a tip? ", "Tip option", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (yes_no == JOptionPane.YES_OPTION) {
            String input = JOptionPane.showInputDialog("Enter the percentage that you would look to tip");
            try {
                int percentage = Integer.parseInt(input);
                tip = totalPrice * percentage * 0.001;
                totalPrice += tip;
            } catch (Exception e) {                
                JOptionPane.showMessageDialog(this, "Invalid percentage. Please enter a number.", "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        }
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
        String finalResult = "";
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
                    j--; 
                }
            }
        }
       for (int i = 0; i < usersList.size(); i++) {
            User oldUser = usersList.get(i);
            String name = oldUser.getName();
            double price = oldUser.getPrice();
            String oldMsg = oldUser.funnyMessage(); // preserve old message

            if (price > 0 && price < 15) {
                usersList.set(i, new LightSpender(name, price, oldMsg));
            } else if (price >= 15 && price < 40) {
                usersList.set(i, new AverageSpender(name, price, oldMsg));
            } else {
                usersList.set(i, new BigSpender(name, price, oldMsg));
            }
        }

        newDtm = new DefaultTableModel(afterColumnNames, 0);
        for (User user : usersList) {
            finalResult += user.toString() + "\n";
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
        JOptionPane.showMessageDialog(this, finalResult, "Results", JOptionPane.PLAIN_MESSAGE);

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
