//Dealer class:
import java.util.ArrayList;
public class Dealer{
    public Deck theDeck;
    public ArrayList<Card> dealerHand;
    public Dealer() {
        theDeck=new Deck();
        dealerHand = new ArrayList<Card>();

    }

    public Deck getDeck(){

        return theDeck;
    }
    private ArrayList<Card> helper() {
        int temp=0;
        ArrayList<Card> pass = new ArrayList<Card>();
        for(int i=0;i<3;i++) {
            pass.add(theDeck.get(0));
            theDeck.remove(0);
        }
        return pass;
    }

    public ArrayList<Card> dealHand(){

        return helper();
    }

    public void setH(){
        dealerHand.clear();
        for(int i=0;i<3;i++) {
            dealerHand.add(theDeck.get(0));
            theDeck.remove(0);
        }


    }

    public ArrayList<Card> getH(){
        return dealerHand;
    }

    public void startGame(){
        if(theDeck.size() <= 34) {
            theDeck.newDeck();
        }
    }

}
