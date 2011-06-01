package com.boothj5.shithead.player;

import java.util.HashMap;
import java.util.Map;

import com.boothj5.shithead.player.computer.*;

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
	
	public static Map<String, String> computerPlayerList() {
	    Map<String, String> players = new HashMap<String, String>() ;
	    
	    players.put("SimplePlayer", "s") ;
	    players.put("Aggressive", "a") ;
	    players.put("RandomPlayer", "r") ;
	    players.put("Pyromaniac", "p") ;
	    players.put("DeviousPyro", "d") ;
	    players.put("FaceDownChecker", "f") ;
	    players.put("LikesRankOrder", "l") ;
	    
	    return players ;
	}
	
	   public static Map<String, String> computerPlayerDescriptions() {
	        Map<String, String> players = new HashMap<String, String>() ;
	        
	        players.put("SimplePlayer", SimplePlayer.description) ;
	        players.put("Aggressive", Aggressive.description) ;
	        players.put("RandomPlayer", RandomPlayer.description) ;
	        players.put("Pyromaniac", Pyromaniac.description) ;
	        players.put("DeviousPyro", DeviousPyro.description) ;
	        players.put("FaceDownChecker", FaceDownChecker.description) ;
	        players.put("LikesRankOrder", LikesRankOrder.description) ;
	        
	        return players ;
	    }
}
