import java.util.ArrayList;


public class LightSpender extends User {

    private ArrayList<String> quotes;

    public LightSpender(String inputName, double inputPrice) {

        super(inputName, inputPrice);
        quotes = new ArrayList<>();

        quotes.add("Cheep, cheep cheep cheep! Oh is that a bird? Oh no sorry that's just you");
        quotes.add("Frugal and fabulous 💅");
        quotes.add("Contributing spiritually, and not financially");
        quotes.add("Big spender alert! Just kidding, that was someone else.");
        quotes.add("Your fork worked harder on my plate than your card did on the bill.");

    }

    public String toString() {

        return name + " owes a mere " + price + " dollars.";        

    }

    public String funnyMessage() {
        int randomIndex = (int)(Math.random() * 6);

        return quotes.get(randomIndex);

    }
}
