

//Card class:

public class Card{
    private int value;
    private char suit;

    public Card(int v,char c) {
        value=v;
        suit=c;

    }
    public int getV() {
        return value;
    }
    public char getC() {
        return suit;
    }
    public void setV(int v) {
        value=v;
    }
    public void setC(char c) {
        suit=c;
    }
}
