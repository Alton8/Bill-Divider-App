import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.util.ArrayList;
// import java.awt.event.ActionListener;
import java.awt.event.ActionListener;
// import java.util.concurrent.Flow;

public class GUI extends JFrame implements ActionListener {
    
    private ArrayList<User> usersList; // ArrayList to hold all of the people

    private JButton finishButton;
    private JButton addButton;
    private JTextField nameInput;
    private JTextField priceInput;
    private JTextArea resultText;
    // private JList <User> users;
    private DefaultListModel <User> listModel; // idk?? but Mr.Crow used it

    public GUI() {

        super("Simple GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(500, 400); // Increased window size
        setLocationRelativeTo(null); // Center the window

        //Initialize the ArrayList
        listModel = new DefaultListModel<>();
        // users = new JList<>(listModel);
        usersList = new ArrayList<>();
  
        //Create the button
        addButton = new JButton("Enter"); 
        addButton.setPreferredSize(new Dimension(100, 50));
        
        finishButton = new JButton("Finish");
        finishButton.setPreferredSize(new Dimension(100, 50));

        addButton.addActionListener(this);
        finishButton.addActionListener(this);

        resultText = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(resultText); // Add JList to a scroll pane
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
        add(scrollPane, BorderLayout.CENTER);
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

        String username = nameInput.getText().trim();
        Double price;   
        boolean userExists = false;
        if (!priceInput.getText().isEmpty()) { // Borrowed Mr Crow's code
            User newUser = null;
            try {
                price = Double.parseDouble(priceInput.getText().trim());
                for (User user : usersList) {
                    if (user.getName().equals(username)) {
                        user.increasePrice(price);
                        userExists = true;
                    }
                }
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
        listModel.clear();
        String totalText = "";
        for (User user : usersList) {
            totalText += user + "   " + user.funnyMessage() + "\n";
        }
        resultText.setText(totalText);
    }
    private void updateScreen() {
        listModel.clear();
        
        for (User user : usersList) {
            resultText.setText(user + "\n"); 
        }
    }
     
        
    
    public static void main(String[] args) { 
        new GUI();
    }
}
