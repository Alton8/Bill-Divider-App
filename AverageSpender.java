import java.util.ArrayList;

public class BigSpender extends User {

    private ArrayList<String> quotes;

    public BigSpender(String inputName, double inputPrice) {

        super(inputName, inputPrice);
        quotes = new ArrayList<>();
        quotes.add("You treat your bank account like it wronged you in a past life.");
        quotes.add("Good news: You’re stimulating the economy. Bad news: It’s just your economy.");
        quotes.add("You spent " + this.getStringPrice() + " on... honestly, we don’t even know. Neither do you.");
        quotes.add("We ran the numbers and... yikes.");
        quotes.add("Were you feeding yourself or opening a small bistro?");
        quotes.add("You didn’t split the bill — you became the bill.");
    }

    public String toString() {
        return name + " owes a whole " + price + " dollars.";
    }
    
    public String funnyMessage() {
        int randomIndex = (int)(Math.random()*6);
        return quotes.get(randomIndex);
    }
}
