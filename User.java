import java.text.DecimalFormat;

public abstract class User {

    String name;
    double price;
    DecimalFormat df = new DecimalFormat("#.00"); 

    public User(String inputName, double inputPrice) {
        name = inputName;
        price = inputPrice;
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
    public void increasePrice(double amount) {
        price+=amount;
    }

    public abstract String toString();
    public abstract String funnyMessage();
}
