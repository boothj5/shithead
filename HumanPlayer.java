public class HumanPlayer extends Player {

	public HumanPlayer(String name, int handSize) {
		super(name, handSize) ;
	}
	
	public void swapCards(int handCard, int faceUpCard) {
		if ((handCard < 0) || !(handCard < handSize) ||
				(faceUpCard < 0) || !(faceUpCard < handSize)) {
			return ;
		}
		else {
			Card savedFromHand = hand.get(handCard) ;
			Card savedFromFaceUp = faceUp.get(faceUpCard) ;
			faceUp.set(faceUpCard, savedFromHand) ;
			hand.set(handCard, savedFromFaceUp) ;		
		}
	}
}
