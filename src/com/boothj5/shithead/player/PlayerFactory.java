package com.boothj5.shithead.player;

public class PlayerFactory {

	public static Player createPlayer(String playerType, String name, int cardsPerHand)
			throws Exception {
		if (playerType.equals("h"))
			return new HumanPlayer(name, cardsPerHand) ;
		else if (playerType.equals("s")) 
			return new SimpleComputerPlayer(name, cardsPerHand) ;
		else if (playerType.equals("a")) 
			return new AggressiveComputerPlayer(name, cardsPerHand) ;
		else if (playerType.equals("r")) 
			return new RandomComputerPlayer(name, cardsPerHand) ;
		else if (playerType.equals("p")) 
			return new PyromaniacComputerPlayer(name, cardsPerHand) ;
		else if (playerType.equals("d")) 
			return new DeviousPyroComputerPlayer(name, cardsPerHand) ;
		else 
			throw new Exception("Cannot find player type to create") ;
	}
}
