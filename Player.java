import java.util.* ;

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
		output.append("HAND:\t\t") ;
		
		for (Card card : hand) 
			output.append(card + ", ") ;
		
		return output.toString() ;
	}

	public String showFaceUp() {
		StringBuffer output = new StringBuffer() ;
		output.append("FACE UP:\t") ;
		
		for (Card card : faceUp) 
			output.append(card + ", ") ;

		return output.toString() ;
	}
	
	public String showFaceDown(boolean reallyShow) {
		StringBuffer output = new StringBuffer() ;
		output.append("FACE DOWN:\t") ;
		
		for (Card card : faceDown) {
			if (reallyShow) 
				output.append(card + ", ") ;
			else 
				output.append("****, ") ;
		}
		return output.toString() ;
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
}
