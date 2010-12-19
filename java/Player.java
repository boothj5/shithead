public class Player {
	
	String name ;
	
	public List<Card> faceDown = new ArrayList<Card> ;
	public List<Card> faceup = new ArrayList<Card> ;
	public List<Card> hand = new ArrayList<Card> ;

	public Player(String name) {
		this.name = name ;
	}
	
}
