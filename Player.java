import java.util.* ;

public abstract class Player {
	
	public static enum Hand {HAND, FACEUP, FACEDOWN } ;
	
	private String name ;
	protected int handSize ;
	
	protected List<Card> faceDown = new ArrayList<Card>() ;
	protected List<Card> faceUp = new ArrayList<Card>() ;
	protected List<Card> hand = new ArrayList<Card>() ;
	
	public Player(String name, int handSize) {
		this.name = name ;
		this.handSize = handSize ;
	}
	
	// Abstract methods
	public abstract String askSwapMore() ;
		
	public abstract SwapResponse askSwapChoice() ;
	
	// Concrete methods
	public String getName() {
		return name ;
	}
	
	public List<Card> getHand() {
		return hand ;
	}
	
	public List<Card> getFaceUp() {
		return faceUp ;
	}

	public List<Card> getFaceDown() {
		return faceDown ;
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
		hand.addAll(cards) ;
	}
	
	public void deal(Hand hand, Card card) {
		if (Hand.FACEDOWN.equals(hand)) {
			faceDown.add(card) ;
		}
		else if (Hand.FACEUP.equals(hand)) {
			faceUp.add(card) ;
		}
		else {
			this.hand.add(card) ;
		}
	}
	
	public void swapCards(SwapResponse swapResponse) {
		if ((swapResponse.getHandCard() < 0) || !(swapResponse.getHandCard() < handSize) ||
				(swapResponse.getFaceUpCard() < 0) || !(swapResponse.getFaceUpCard() < handSize)) {
			return ;
		}
		else {
			Card savedFromHand = hand.get(swapResponse.getHandCard()) ;
			Card savedFromFaceUp = faceUp.get(swapResponse.getFaceUpCard()) ;
			faceUp.set(swapResponse.getFaceUpCard(), savedFromHand) ;
			hand.set(swapResponse.getHandCard(), savedFromFaceUp) ;		
		}
	}
}
