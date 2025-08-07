import java.util.ArrayList;

public class BigSpender extends User {
    private ArrayList<String> quotes;

    public BigSpender(String name, double price) {
        super(name, price);

        quotes = new ArrayList<>();

    }
    public BigSpender(String name, double price, String funnyMessage) {
        super(name, price);
        setFunnyMessage(funnyMessage); // Use existing message
    }

    @Override
    public String funnyMessage() {
        quotes.add("You treat your bank account like it wronged you in a past life.");
        quotes.add("Good news: You’re stimulating the economy. Bad news: It’s just your economy.");
        quotes.add("We ran the numbers and... yikes.");
        quotes.add("Were you feeding yourself or opening a small bistro?");
        quotes.add("You didn’t split the bill — you became the bill.");

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
        return "BigSpender";
    }
    @Override
    public String toString() {
        return getName() + " owes a whole " + getStringPrice() + " dollars.";
    }
}
