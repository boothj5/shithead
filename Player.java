import java.util.* ;

public abstract class Player {
	
	public static enum Hand {HAND, FACEUP, FACEDOWN } ;
	
	private String name ;
	
	private List<Card> faceDown = new ArrayList<Card>() ;
	private List<Card> faceUp = new ArrayList<Card>() ;
	private List<Card> hand = new ArrayList<Card>() ;
	
	public Player(String name) {
		this.name = name ;
	}
	
	public String getName() {
		return name ;
	}
	
	public boolean hasCards() {
		if (!faceUp.isEmpty()) 
			return true ;
		else if (!faceDown.isEmpty())
			return true ;
		else if (!hand.isEmpty())
			return true ;
		else 
			return false ;
	}
	
	public void pickUp(List<Card> cards) {
		// pick up these cards to hand
	}
	
	public void deal(Hand hand, Card card) {
		// accept card to defined hand
	}
}
