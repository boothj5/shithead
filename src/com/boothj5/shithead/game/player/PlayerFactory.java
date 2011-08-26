package com.boothj5.shithead.game.player;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.player.computer.*;

public class PlayerFactory {

	private static final Map<String, Class<? extends ComputerPlayer>> players = 
	            new HashMap<String, Class<? extends ComputerPlayer>>() ;
	static {
        players.put("s", SimplePlayer.class) ;
        players.put("a", Aggressive.class) ;
        players.put("r", RandomPlayer.class) ;
        players.put("p", Pyromaniac.class) ;
        players.put("d", DeviousPyro.class) ;
        players.put("f", FaceDownChecker.class) ;
        players.put("l", LikesRankOrder.class) ;
        players.put("o", RankOrderSwapper.class) ;
	}
	
	public static final Map<String, Class<? extends ComputerPlayer>> getPlayers() {
	    return players ;
	}
	
    public static Map<String, String> computerPlayerDescriptions() throws ShitheadException {
        Map<String, String> result = new HashMap<String, String>() ;
        
        try {
        
            for(String key : players.keySet()) {
                Class<? extends ComputerPlayer> player = players.get(key) ;
                Field descField = player.getField("description") ;
                String description = (String) descField.get(null) ;
                result.put(player.getName().substring(player.getPackage().getName().length() + 1), description) ; 
            }
        } catch (IllegalAccessException iae) {
            throw new ShitheadException("Error getting computer player description") ;
        } catch (NoSuchFieldException fsfe) {
            throw new ShitheadException("Error getting computer player description") ;
        }
        return result ;
    }
    
    public static Player createPlayer(String playerType, String name, int cardsPerHand)
			throws ShitheadException {
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
		else if (playerType.equals("ros")) 
			return new RankOrderSwapper(name, cardsPerHand) ;
		else 
			throw new ShitheadException("Cannot find player type to create") ;
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
	    players.put("RankOrderSwapper", "ros") ;
	    
	    return players ;
	}
	
//    public static Map<String, String> computerPlayerDescriptions() {
//        Map<String, String> players = new HashMap<String, String>() ;
//
//        players.put("SimplePlayer", SimplePlayer.description) ;
//        players.put("Aggressive", Aggressive.description) ;
//        players.put("RandomPlayer", RandomPlayer.description) ;
//        players.put("Pyromaniac", Pyromaniac.description) ;
//        players.put("DeviousPyro", DeviousPyro.description) ;
//        players.put("FaceDownChecker", FaceDownChecker.description) ;
//        players.put("LikesRankOrder", LikesRankOrder.description) ;
//        players.put("RankOrderSwapper", RankOrderSwapper.description) ;
//
//        return players ;
//    }
}
