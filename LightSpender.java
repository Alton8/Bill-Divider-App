import java.util.ArrayList;

public class LightSpender extends User {
    private ArrayList<String> quotes;

    public LightSpender(String name, double price) {
        super(name, price);

        quotes = new ArrayList<>();

    }
    public LightSpender(String name, double price, String funnyMessage) {
        super(name, price);
        setFunnyMessage(funnyMessage); // Use existing message
    }
    @Override
    public String funnyMessage() {
        quotes.add("Thrifty legend. You spent less than a large coffee.");
        quotes.add("You treated yourself... very lightly.");
        quotes.add("Your wallet didn’t even notice this purchase.");
        quotes.add("You’re the kind of person coupon sites dream about.");

        int randomIndex = (int)(Math.random() * quotes.size());
        setFunnyMessage(quotes.get(randomIndex)); // Store once
        return super.funnyMessage();
    }
    @Override
    public ArrayList<String> getQuotes() {
        return quotes;
    }
    @Override
    public void addFunnyMessage(String message) {
        quotes.add(message);
    }
    @Override 
    public String getType() {
        return "LightSpender";
    }
    @Override
    public String toString() {
        return getName() + " only spent " + getStringPrice() + ". Nice!";
    }
}
