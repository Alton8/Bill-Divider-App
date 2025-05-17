
public abstract class User {

    String name;
    double price;

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
        return "$" + price + "";
    }
    public void increasePrice(double amount) {
        price+=amount;
    }

    public abstract String toString();
    public abstract String funnyMessage();
}
