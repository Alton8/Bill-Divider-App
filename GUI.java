import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
// import java.awt.event.ActionListener;
import java.util.ArrayList;
// import java.awt.event.ActionListener;
import java.awt.event.ActionListener;
// import java.util.concurrent.Flow;

public class GUI extends JFrame implements ActionListener {
    
    private ArrayList<User> usersList; // ArrayList to hold all of the people

    String[] beforeColumnNames = new String[]{"Name", "Item Price"};
    String[] afterColumnNames = new String[]{"Name", "Item Price", "Analysis"};

    private DefaultTableModel dtm;
    private JTable table;
    private JButton finishButton;
    private JButton addButton;
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
  
        //Create the button
        addButton = new JButton("Enter"); 
        addButton.setPreferredSize(new Dimension(100, 50));
        
        finishButton = new JButton("Finish");
        finishButton.setPreferredSize(new Dimension(100, 50));

        addButton.addActionListener(this);
        finishButton.addActionListener(this);

        //Extra stuff
        JPanel inputPanel = new JPanel(new FlowLayout()); // Use FlowLayout for simplicity
        nameInput = new JTextField(15);
        nameInput.setToolTipText("Enter a name"); // Tooltip
        priceInput = new JTextField(8);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameInput);
        inputPanel.add(new JLabel("Price: "));
        inputPanel.add(priceInput);
        inputPanel.add(addButton);
        
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(finishButton);
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
            displayResults();
            finishButton.setVisible(false);

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
                if (price >= 1 && price < 15) {
                    
                    if (!userExists) {
                        newUser = new LightSpender(username, price);
                        usersList.add(newUser);
                    }

                    System.out.println(usersList.toString());
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
    
    private void displayResults() {

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

        System.out.println(usersList);

        DefaultTableModel newDtm = new DefaultTableModel(afterColumnNames, 0);
        for (User user : usersList) {
            String[] finalAllUsers = {user.getName(), user.getStringPrice(), user.funnyMessage()};
            newDtm.addRow(finalAllUsers);
        }
        table.setModel(newDtm);


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
