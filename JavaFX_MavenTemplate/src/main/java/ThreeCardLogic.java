import java.util.ArrayList;
import java.util.Comparator;

public class ThreeCardLogic {
    public static int evalHand(ArrayList<Card> hand) {
        hand.sort(Comparator.comparing(Card::getV));
        Card temp = hand.get(0);
        Card temp2 = hand.get(1);
        Card temp3 = hand.get(2);
        //checks if all suits are the same
        if (temp.getC() == temp2.getC() && temp.getC() == temp3.getC()) {
            //if all suits the same and the values line up, then we have a straight flush
            if (temp.getV() + 1 == temp2.getV() && temp.getV() + 2 == temp3.getV()) {
                return 1;
            }
            //if just all the suits are the same, then we have a flush
            else {
                return 4;
            }
        }
        //if all the values line up, then we have a straight
        if (temp.getV() + 1 == temp2.getV() && temp.getV() + 2 == temp3.getV()) {
            return 3;
        }
        //if all the values the same, then we have a 3 of a kind
        if (temp.getV() == temp2.getV() && temp.getV() == temp3.getV()) {
            return 2;
        }
        //if 2 of the values are the same, then we have a pair
        if (temp.getV() == temp2.getV() || temp.getV() == temp3.getV() || temp2.getV() == temp3.getV()) {
            return 5;
        }
        //nothing, then high
        return 0;
    }




    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {


        int value = evalHand(hand);

        if (value == 5) {
            return bet + bet;
        } else if (value == 4)
            return (3 * bet) + bet;
        else if (value == 3) {
            return (6 * bet) + bet;
        } else if (value == 2) {
            return (30 * bet) + bet;
        } else if (value == 1) {
            return (bet * 40) + bet;
        } else if (value == 0) {
            return 0;
        }
        return 0;
    }

    // 1 = dealer
    // 2 = player
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        int deal = evalHand(dealer);
        int play = evalHand(player);
        player.sort(Comparator.comparing(Card::getV));
        dealer.sort(Comparator.comparing(Card::getV));
        if(deal == 0){
            if (dealer.get(2).getV() < 12) {
                return 0;
            }
        }
        if (play == 0 && deal != 0) {
            return 1;
        } else if (deal == 0 && play != 0) {
            return 2;
        } else if (deal < play) {
            return 1;
        } else if (play < deal) {
            return 2;
        } else if (deal == 0 && play == 0) {
            if (dealer.get(2).getV() < 12) {
                return 0;

            }
            for (int i = 2; i >= 0; i--) {
                if (dealer.get(i).getV() > player.get(i).getV()) {
                    return 1;
                } else if (dealer.get(i).getV() < player.get(i).getV()) {
                    return 2;

                }

            }
        } else if (deal == play) {

            if (dealer.get(1).getV() > player.get(1).getV()) {
                return 1;
            } else if (dealer.get(1).getV() < player.get(1).getV()) {
                return 2;
            } else if (dealer.get(1).getV() == player.get(1).getV()) {
                if (dealer.get(2).getV() > player.get(2).getV()) {
                    return 1;
                } else if (dealer.get(2).getV() < player.get(2).getV()) {
                    return 2;
                }
            }

        }
            return 0;
    }
}












