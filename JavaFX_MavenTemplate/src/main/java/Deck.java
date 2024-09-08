import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card>{



    private void loader() {
        for(int i=2;i<=14;i++) {
            for(int j=0;j<4;j++) {
                if(j==0) {
                    add(new Card(i,'C'));
                }
                else if(j==1) {
                    add(new Card(i,'D'));

                }
                else if(j==2) {
                    add(new Card(i,'S'));

                }
                else {
                    add(new Card(i,'H'));
                }
            }
        }
    }

    public Deck() {
        loader();
        Collections.shuffle(this);
    }


    public void newDeck() {
        clear();
        loader();
        Collections.shuffle(this);


    }


}
