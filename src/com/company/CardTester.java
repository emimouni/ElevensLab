
package com.company;

/**
 * Created by em343 on 3/15/17.
 */
public class CardTester {

    /**
     * The main method in this class checks the Card operations for consistency.
     *	@param args is not used.
     */
    public static void main(String[] args) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 1 *** */
	    	Card c1 = new Card("Queen", "Hearts" , 12);
		Card c2 = new Card("Ace", "Spades" , 1);
		Card c3 = new Card("Jack","Dimonds",11 );


		System.out.println(card.rank());
		System.out.println(card.suit());
		System.out.println(card.pointValue());
		System.out.println(card.matches(c1));
		System.out.println(card.toString());

		System.out.println(card1.rank());
		System.out.println(card1.suit());
		System.out.println(card1.pointValue());
		System.out.println(card1.matches(c2));
		System.out.println(card1.toString());

		System.out.println(card2.rank());
		System.out.println(card2.suit());
		System.out.println(card2.pointValue());
		System.out.println(card2.matches(c3));
		System.out.println(card2.toString());
    }
}
