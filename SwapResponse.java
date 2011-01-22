
public class SwapResponse {
	int handCard ;
	int faceUpCard ;

	public SwapResponse(int handCard, int faceUpCard) {
		this.handCard = handCard ;
		this.faceUpCard = faceUpCard ;
	}
	
	public int getHandCard() {
		return handCard;
	}
	public void setHandCard(int handCard) {
		this.handCard = handCard;
	}
	public int getFaceUpCard() {
		return faceUpCard;
	}
	public void setFaceUpCard(int faceUpCard) {
		this.faceUpCard = faceUpCard;
	}
}
