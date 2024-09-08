//Player class:
import java.util.ArrayList;

public class Player{
    private ArrayList<Card> hand;
    private int anteBet;
    private int playBet;
    private int pairPlusBet;
    private int totalWinnings;

    public Player(){
        hand= new ArrayList<Card> ();
        anteBet=0;
        playBet=0;
        pairPlusBet=0;
        totalWinnings=0;

    }

    public ArrayList<Card> getH() {
        return hand;

    }
    public void setH(Dealer theDealer) {

        hand=theDealer.dealHand();
    }
    public int getA() {
        return anteBet;

    }
    public void setA(int temp) {
        anteBet=temp;

    }
    public int getPB() {
        return playBet;

    }
    public void setPB(int v) {
        playBet=v;

    }
    public int getPPB() {
        return pairPlusBet;

    }
    public void setPPB(int temp) {
        pairPlusBet=temp;

    }
    public int getT() {
        return totalWinnings;

    }
    public void setT(int temp) {
        totalWinnings+=temp;

    }
    public void freshStart(){
        hand.clear();
        anteBet=0;
        playBet=0;
        pairPlusBet=0;
        totalWinnings=0;

    }


    public void fold(){
        hand.clear();
        totalWinnings -= anteBet;
        anteBet=0;
        playBet=0;
        pairPlusBet=0;
    }

}
