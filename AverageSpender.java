import java.util.ArrayList;

public class AverageSpender extends User {
    private ArrayList<String> quotes;

    public AverageSpender(String name, double price) {
        super(name, price);

        quotes = new ArrayList<>();

    }
    public AverageSpender(String name, double price, String funnyMessage) {
        super(name, price);
        setFunnyMessage(funnyMessage); // Use existing message
    }
    @Override
    public String funnyMessage() {
        quotes.add("You’re walking the line between budgeting and ballin’.");
        quotes.add("Not frugal, not flashy — just fashionably average.");
        quotes.add("That was a decent treat. Your wallet isn’t mad. Yet.");
        quotes.add("Just enough to feel something. Not enough to feel guilty.");

        int randomIndex = (int)(Math.random() * quotes.size());
        setFunnyMessage(quotes.get(randomIndex)); 
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
        return "AverageSpender";
    }
    @Override
    public String toString() {
        return getName() + " spent a reasonable " + getStringPrice() + ".";
    }
}
