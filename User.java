import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class User {
    private final String name;
    private double price;
    private String message; 
    DecimalFormat df = new DecimalFormat("#.00"); 

    public User(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getStringPrice() {
        return "$" + df.format(price);
    }

    public void increasePrice(double extra) {
        this.price += extra;
    }

    public String funnyMessage() {
        return message;
    }

    public void setFunnyMessage(String message) {
        this.message = message;
    }
    public abstract ArrayList<String> getQuotes();
    public abstract void addFunnyMessage(String message);
    public abstract String getType();
    @Override
    public abstract String toString();
}
