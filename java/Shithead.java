import java.util.* ;

public class Shithead {
	
	public static void main(String[] args) {

		Deck deck = new Deck() ;
		deck.shuffle() ;

		Player james = new Player("James") ;
		Player computer = new Player("Computer") ;
		
		Iterator<Card> deckIterator = deck.cards.iterator() ;
		for (int i = 0 ; i <4 ; i++) {
			james.faceDown.add(deckIterator.next()) ;
			computer.faceDown.add(deckIterator.next()) ;
		}
		
		for (int i = 0 ; i <4 ; i++) {
			james.faceUp.add(deckIterator.next()) ;
			computer.faceUp.add(deckIterator.next()) ;
		}

		for (int i = 0 ; i <4 ; i++) {
			james.hand.add(deckIterator.next()) ;
			computer.hand.add(deckIterator.next()) ;
		}


		System.out.println(james.name + " faceDown = " + 
								james.faceDown.get(0) + ", " + 
								james.faceDown.get(1) + ", " + 
								james.faceDown.get(2) + ", " + 
								james.faceDown.get(3) + ", ") ;

		System.out.println(james.name + " faceUp = " + 
								james.faceUp.get(0) + ", " + 
								james.faceUp.get(1) + ", " + 
								james.faceUp.get(2) + ", " + 
								james.faceUp.get(3) + ", ") ;

		System.out.println(james.name + " hand = " + 
								james.hand.get(0) + ", " + 
								james.hand.get(1) + ", " + 
								james.hand.get(2) + ", " + 
								james.hand.get(3) + ", ") ;
		
		System.out.println() ;
		System.out.println(computer.name + " faceDown = " + 
								computer.faceDown.get(0) + ", " + 
								computer.faceDown.get(1) + ", " + 
								computer.faceDown.get(2) + ", " + 
								computer.faceDown.get(3) + ", ") ;

		System.out.println(computer.name + " faceUp = " + 
								computer.faceUp.get(0) + ", " + 
								computer.faceUp.get(1) + ", " + 
								computer.faceUp.get(2) + ", " + 
								computer.faceUp.get(3) + ", ") ;

		System.out.println(computer.name + " hand = " + 
								computer.hand.get(0) + ", " + 
								computer.hand.get(1) + ", " + 
								computer.hand.get(2) + ", " + 
								computer.hand.get(3) + ", ") ;

		
	}

	

}
