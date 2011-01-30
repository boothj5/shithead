package com.boothj5.shithead.player;

import com.boothj5.shithead.player.computer.Aggressive;
import com.boothj5.shithead.player.computer.DeviousPyro;
import com.boothj5.shithead.player.computer.FaceDownChecker;
import com.boothj5.shithead.player.computer.LikesRankOrder;
import com.boothj5.shithead.player.computer.Pyromaniac;
import com.boothj5.shithead.player.computer.RandomPlayer;
import com.boothj5.shithead.player.computer.SimplePlayer;

public class PlayerFactory {

	public static Player createPlayer(String playerType, String name, int cardsPerHand)
			throws Exception {
		if (playerType.equals("h"))
			return new HumanPlayer(name, cardsPerHand) ;
		else if (playerType.equals("s")) 
			return new SimplePlayer(name, cardsPerHand) ;
		else if (playerType.equals("a")) 
			return new Aggressive(name, cardsPerHand) ;
		else if (playerType.equals("r")) 
			return new RandomPlayer(name, cardsPerHand) ;
		else if (playerType.equals("p")) 
			return new Pyromaniac(name, cardsPerHand) ;
		else if (playerType.equals("d")) 
			return new DeviousPyro(name, cardsPerHand) ;
		else if (playerType.equals("f")) 
			return new FaceDownChecker(name, cardsPerHand) ;
		else if (playerType.equals("l")) 
			return new LikesRankOrder(name, cardsPerHand) ;
		else 
			throw new Exception("Cannot find player type to create") ;
	}
}
