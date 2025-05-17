import java.util.ArrayList;

public class BigSpender extends User {

    private ArrayList<String> quotes;

    public BigSpender(String inputName, double inputPrice) {
        super(inputName, inputPrice);
        quotes = new ArrayList<>();
        quotes.add("You treat your bank account like it wronged you in a past life.");
        quotes.add("Good news: You’re stimulating the economy. Bad news: It’s just your economy.");
        quotes.add("You spent $" + price + " on... honestly, we don’t even know. Neither do you.");
        quotes.add("We ran the numbers and... yikes.");
    }

    public String toString() {
        return name + " owes a whole " + price + " dollars.";
    }
    
    public String funnyMessage() {
        return quotes.get((int)(Math.random() * 5));
    }
}
