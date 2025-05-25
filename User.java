import java.text.DecimalFormat;

public abstract class User {
    private String name;
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

    public abstract String toString();
}
