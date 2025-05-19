import java.util.ArrayList;

public class AverageSpender extends User {

    private ArrayList<String> quotes = new ArrayList<String>();
    public AverageSpender(String inputName, double inputPrice) {

        super(inputName, inputPrice);
        quotes = new ArrayList<>();

        quotes.add("Reasonable… for now 👀");
        quotes.add("You didn’t break the bank. You just nudged it a little.");
        quotes.add("Balanced budget, unbalanced life.");
        quotes.add("You’re the reason banks invented the ‘average spender’ category.");
        quotes.add("Congrats! You spent a totally reasonable amount — according to you.");
        quotes.add("Respectable spend. Boring, but respectable.");
    

    }

    public String toString() {
        return name + " owes about " + price + " dollars.";
    }

    public String funnyMessage() {
        int randomIndex = (int)(Math.random()*6);
        return quotes.get(randomIndex);
    }
    

}
