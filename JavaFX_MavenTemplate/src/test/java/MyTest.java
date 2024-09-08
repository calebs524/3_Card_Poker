import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
class MyTest {
	 static Dealer theDealer;
//	static ArrayList<Card> test ;
	static Deck daDeck;
	@BeforeAll
	 static void setup() {
		theDealer=new Dealer();
//		test=theDealer.dealHand();
		daDeck = new Deck();
	}

	@Test
	void arrayTest() {
		assertEquals(52, theDealer.theDeck.size(),"didnt work");
	}

	@Test
	void arrayTest2() {
//		assertEquals(52,daDeck.size(),"didnt work");
	}
// tests 3-8 makes sure the eval hand work
	@Test
	void arrayTest3() {
		ArrayList<Card> tester = new ArrayList<Card>();
		tester.add(new Card (2,'D'));
		tester.add(new Card (2,'H'));
		tester.add(new Card (2,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(2, holder.evalHand(tester),"didnt work");
	}

	@Test
	void arrayTest4() {
		ArrayList<Card> tester = new ArrayList<Card>();
		tester.add(new Card (2,'D'));
		tester.add(new Card (2,'H'));
		tester.add(new Card (5,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(5, holder.evalHand(tester),"didnt work");
	}

	@Test
	void arrayTest5() {
		ArrayList<Card> tester = new ArrayList<Card>();
		tester.add(new Card (2,'D'));
		tester.add(new Card (3,'D'));
		tester.add(new Card (4,'D'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(1, holder.evalHand(tester),"didnt work");
	}

	@Test
	void arrayTest6() {
		ArrayList<Card> tester = new ArrayList<Card>();
		tester.add(new Card (2,'D'));
		tester.add(new Card (3,'H'));
		tester.add(new Card (4,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(3, holder.evalHand(tester),"didnt work");
	}

	@Test
	void arrayTest7() {
		ArrayList<Card> tester = new ArrayList<Card>();
		tester.add(new Card (2,'D'));
		tester.add(new Card (9,'D'));
		tester.add(new Card (12,'D'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(4, holder.evalHand(tester),"didnt work");
	}

	@Test
	void arrayTest8() {
		ArrayList<Card> tester = new ArrayList<Card>();
		tester.add(new Card (2,'D'));
		tester.add(new Card (9,'H'));
		tester.add(new Card (12,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(0, holder.evalHand(tester),"didnt work");
	}

	// tests evalpphand for each of the 6 cases
	@Test
	void arrayTest9() {
		ArrayList<Card> tester = new ArrayList<Card>();
		tester.add(new Card (2,'D'));
		tester.add(new Card (3,'D'));
		tester.add(new Card (4,'D'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(1230, holder.evalPPWinnings(tester, 30),"didnt work");
	}

	@Test
	void arrayTest10() {
		ArrayList<Card> tester = new ArrayList<Card>();
		tester.add(new Card (2,'D'));
		tester.add(new Card (2,'H'));
		tester.add(new Card (2,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(930, holder.evalPPWinnings(tester, 30),"didnt work");
	}

	@Test
	void arrayTest11() {
		ArrayList<Card> tester = new ArrayList<Card>();
		tester.add(new Card (2,'D'));
		tester.add(new Card (2,'H'));
		tester.add(new Card (5,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(60, holder.evalPPWinnings(tester, 30),"didnt work");
	}

	@Test
	void arrayTest12() {
		ArrayList<Card> tester = new ArrayList<Card>();
		tester.add(new Card (2,'D'));
		tester.add(new Card (9,'H'));
		tester.add(new Card (12,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(100, holder.evalPPWinnings(tester,100),"didnt work");
	}
	@Test
	void arrayTest13() {
		ArrayList<Card> tester = new ArrayList<Card>();
		tester.add(new Card (2,'D'));
		tester.add(new Card (9,'D'));
		tester.add(new Card (12,'D'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(320, holder.evalPPWinnings(tester,80),"didnt work");
	}
	@Test
	void arrayTest14() {
		ArrayList<Card> tester = new ArrayList<Card>();
		tester.add(new Card (2,'D'));
		tester.add(new Card (3,'H'));
		tester.add(new Card (4,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(525, holder.evalPPWinnings(tester,75),"didnt work");
	}
// Tests for compare hand
	@Test
	void arrayTest15(){
		ArrayList<Card> dealer = new ArrayList<Card>();
		dealer.add(new Card (2,'D'));
		dealer.add(new Card (3,'H'));
		dealer.add(new Card (4,'S'));

		ArrayList<Card> player = new ArrayList<Card>();
		player.add(new Card (2,'D'));
		player.add(new Card (9,'H'));
		player.add(new Card (12,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(1, holder.compareHands(dealer, player), "didn't work");

	}
	@Test
	void arrayTest16(){
		ArrayList<Card> player = new ArrayList<Card>();
		player.add(new Card (2,'D'));
		player.add(new Card (3,'H'));
		player.add(new Card (4,'S'));

		ArrayList<Card> dealer = new ArrayList<Card>();
		dealer.add(new Card (2,'D'));
		dealer.add(new Card (9,'H'));
		dealer.add(new Card (12,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(2, holder.compareHands(dealer, player), "didn't work");

	}
	@Test
	void arrayTest17(){
		ArrayList<Card> dealer = new ArrayList<Card>();
		dealer.add(new Card (2,'D'));
		dealer.add(new Card (3,'H'));
		dealer.add(new Card (4,'S'));

		ArrayList<Card> player = new ArrayList<Card>();
		player.add(new Card (2,'D'));
		player.add(new Card (9,'D'));
		player.add(new Card (12,'D'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(1, holder.compareHands(dealer, player), "didn't work");
	}
	@Test
	void arrayTest18(){
		ArrayList<Card> player = new ArrayList<Card>();
		player.add(new Card (2,'D'));
		player.add(new Card (3,'H'));
		player.add(new Card (4,'S'));

		ArrayList<Card> dealer = new ArrayList<Card>();
		dealer.add(new Card (2,'D'));
		dealer.add(new Card (9,'D'));
		dealer.add(new Card (12,'D'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(2, holder.compareHands(dealer, player), "didn't work");
	}


	@Test
	void arrayTest19(){
		ArrayList<Card> dealer = new ArrayList<Card>();
		dealer.add(new Card (2,'D'));
		dealer.add(new Card (6,'H'));
		dealer.add(new Card (4,'S'));

		ArrayList<Card> player = new ArrayList<Card>();
		player.add(new Card (2,'D'));
		player.add(new Card (9,'H'));
		player.add(new Card (12,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(0, holder.compareHands(dealer, player), "didn't work");

	}
	@Test
	void arrayTest20(){
		ArrayList<Card> dealer = new ArrayList<Card>();
		dealer.add(new Card (7,'D'));
		dealer.add(new Card (13,'H'));
		dealer.add(new Card (10,'S'));

		ArrayList<Card> player = new ArrayList<Card>();
		player.add(new Card (2,'D'));
		player.add(new Card (9,'H'));
		player.add(new Card (12,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(1, holder.compareHands(dealer, player), "didn't work");

	}
	@Test
	void arrayTest21(){
		ArrayList<Card> dealer = new ArrayList<Card>();
		dealer.add(new Card (2,'D'));
		dealer.add(new Card (12,'H'));
		dealer.add(new Card (4,'S'));

		ArrayList<Card> player = new ArrayList<Card>();
		player.add(new Card (2,'D'));
		player.add(new Card (9,'H'));
		player.add(new Card (13,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(2, holder.compareHands(dealer, player), "didn't work");

	}
	@Test
	void arrayTest22(){
		ArrayList<Card> dealer = new ArrayList<Card>();
		dealer.add(new Card (2,'D'));
		dealer.add(new Card (2,'H'));
		dealer.add(new Card (4,'S'));

		ArrayList<Card> player = new ArrayList<Card>();
		player.add(new Card (9,'D'));
		player.add(new Card (9,'H'));
		player.add(new Card (2,'S'));
		ThreeCardLogic holder = new ThreeCardLogic();
		assertEquals(2, holder.compareHands(dealer, player), "didn't work");

	}

	@Test
	void arrayTest23(){
		for(int i = 0; i < daDeck.size(); i ++){

			System.out.print((int) i);
			System.out.print((char)daDeck.get(i).getC() );
			System.out.println(daDeck.get(i).getV() );
		}
	}
@Test
void arrayTest23(){
	Deck daDeck=new Deck();
	int temp=0;

	for(int i = 0; i < daDeck.size(); i ++){
		if(daDeck.get(i).getC()=='H') {
			temp +=1;
		}
	}
	assertEquals(13,temp,"Didnt work");
}

	@Test
	void arrayTest24(){
		Deck daDeck=new Deck();
		int temp=0;

		for(int i = 0; i < daDeck.size(); i ++){
			if(daDeck.get(i).getC()=='S') {
				temp +=1;
			}
		}
		assertEquals(13,temp,"Didnt work");
	}
	@Test
	void arrayTest25(){
		Deck daDeck=new Deck();
		int temp=0;

		for(int i = 0; i < daDeck.size(); i ++){
			if(daDeck.get(i).getC()=='C') {
				temp +=1;
			}
		}
		assertEquals(13,temp,"Didnt work");
	}
	@Test
	void arrayTest26(){
		Deck daDeck=new Deck();
		int temp=0;

		for(int i = 0; i < daDeck.size(); i ++){
			if(daDeck.get(i).getV()==4) {
				temp +=1;
			}
		}
		assertEquals(4,temp,"Didnt work");
	}
	@Test
	void arrayTest27(){
		Deck daDeck=new Deck();
		int temp=0;

		for(int i = 0; i < daDeck.size(); i ++){
			if(daDeck.get(i).getC()=='D') {
				temp +=1;
			}
		}
		assertEquals(13,temp,"Didnt work");
	}
	@Test
	void arrayTest28(){
		Deck daDeck=new Deck();
		int temp=0;

		for(int i = 0; i < daDeck.size(); i ++){
			if(daDeck.get(i).getV()==8) {
				temp +=1;
			}
		}
		assertEquals(4,temp,"Didnt work");
	}
	@Test
	void arrayTest29(){
		Deck daDeck=new Deck();
		int temp=0;

		for(int i = 0; i < daDeck.size(); i ++){
			if(daDeck.get(i).getV()==11) {
				temp +=1;
			}
		}
		assertEquals(4,temp,"Didnt work");
	}
	@Test
	void arrayTest30(){
		Deck daDeck=new Deck();
		int temp=0;

		for(int i = 0; i < daDeck.size(); i ++){
			if(daDeck.get(i).getV()==12) {
				temp +=1;
			}
		}
		assertEquals(4,temp,"Didnt work");

	}
	@Test
	void arrayTest31(){
		Deck daDeck=new Deck();
		int temp=0;

		for(int i = 0; i < daDeck.size(); i ++){
			if(daDeck.get(i).getV()==6) {
				temp +=1;
			}
		}
		assertEquals(4,temp,"Didnt work");
	}

	@Test
	void arrayTest32(){
		Deck daDeck=new Deck();
		int temp=0;

		for(int i = 0; i < daDeck.size(); i ++){
			if(daDeck.get(i).getV()==14) {
				temp +=1;
			}
		}
		assertEquals(4,temp,"Didnt work");
	}



//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

}
