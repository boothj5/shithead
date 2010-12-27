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
		
		for (Card card : hand) 
			output.append(card + ", ") ;
		
		return output.toString() ;
	}

	public String showFaceUp() {
		StringBuffer output = new StringBuffer() ;
		output.append("Face up = ") ;
		
		for (Card card : faceUp) 
			output.append(card + ", ") ;

		return output.toString() ;
	}
	
	public String showFaceDown(boolean reallyShow) {
		StringBuffer output = new StringBuffer() ;
		output.append("Face down = ") ;
		
		for (Card card : faceDown) {
			if (reallyShow) 
				output.append(card + ", ") ;
			else 
				output.append("****, ") ;
		}
		return output.toString() ;
	}
	
}
