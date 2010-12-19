import java.util.* ;
import java.io.* ;

public class Player {
	
	String name ;
	
	public List<Card> faceDown = new ArrayList<Card>() ;
	public List<Card> faceUp = new ArrayList<Card>() ;
	public List<Card> hand = new ArrayList<Card>() ;

	public Player(String name) {
		this.name = name ;
	}
	
	public String showHand() {
		StringBuffer output = new StringBuffer() ;
		output.append("Hand = ") ;
		
		Iterator<Card> cardIterator = hand.iterator() ;
		while (cardIterator.hasNext()) {
			output.append(cardIterator.next() + ", ") ;
		}
		return output.toString() ;
	}

	public String showFaceUp() {
		StringBuffer output = new StringBuffer() ;
		output.append("Face up = ") ;
		
		Iterator<Card> cardIterator = faceUp.iterator() ;
		while (cardIterator.hasNext()) {
			output.append(cardIterator.next() + ", ") ;
		}
		return output.toString() ;
	}
	
	public String showFaceDown() {
		StringBuffer output = new StringBuffer() ;
		output.append("Face down = ") ;
		
		Iterator<Card> cardIterator = faceDown.iterator() ;
		while (cardIterator.hasNext()) {
			output.append(cardIterator.next() + ", ") ;
		}
		return output.toString() ;
	}
	
}
