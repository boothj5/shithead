public class PlayerFactory {

	public static Player createPlayer(String playerType, String name, int cardsPerHand)
			throws Exception {
		if (playerType.equals("h"))
			return new HumanPlayer(name, cardsPerHand) ;
		else if (playerType.equals("s")) 
			return new SimpleComputerPlayer(name, cardsPerHand) ;
		else 
			throw new Exception("Cannot find player type to create") ;
	}
}
