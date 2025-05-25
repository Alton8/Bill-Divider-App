import java.util.ArrayList;

public class LightSpender extends User {
    private ArrayList<String> quotes;

    public LightSpender(String name, double price) {
        super(name, price);

        quotes = new ArrayList<>();
        quotes.add("Thrifty legend. You spent less than a large coffee.");
        quotes.add("You treated yourself... very lightly.");
        quotes.add("Your wallet didn’t even notice this purchase.");
        quotes.add("You spent " + this.getStringPrice() + " — you’re practically a financial guru.");
        quotes.add("You’re the kind of person coupon sites dream about.");

        int randomIndex = (int)(Math.random() * quotes.size());
        setFunnyMessage(quotes.get(randomIndex)); // Store once
    }
    public LightSpender(String name, double price, String funnyMessage) {
        super(name, price);
        setFunnyMessage(funnyMessage); // Use existing message
    }
    @Override
    public String funnyMessage() {
        return super.funnyMessage();
    }

    @Override
    public String toString() {
        return getName() + " only spent " + getStringPrice() + ". Nice!";
    }
}
