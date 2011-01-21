import java.util.* ;

public class ShitheadGame {
	public boolean debug = false ;
	public List<Player> players = new ArrayList<Player>() ;

	public Deck deck = new Deck() ;
	public int numPlayers ;
	public int numCards ;

	public int currentPlayer ;

	public Stack<Card> pile = new Stack<Card>() ;
	public List<Card> burnt = new ArrayList<Card>() ;

	public static final EnumSet<Card.Rank> layOnAnythingRanks = 
		EnumSet.<Card.Rank>of(Card.Rank.TWO, Card.Rank.SEVEN, Card.Rank.TEN) ;
	public static final EnumSet<Card.Rank> normalRanks = 
		EnumSet.complementOf(layOnAnythingRanks) ;

	public static final Card.Rank invisibleRank = Card.Rank.SEVEN ;
	public static final Card.Rank missTurnRank = Card.Rank.EIGHT ;
	public static final Card.Rank burnRank = Card.Rank.TEN ;	
}
