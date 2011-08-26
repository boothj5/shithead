package com.boothj5.shithead.game.player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
	
    public static Player createPlayer(String playerType, String name, int cardsPerHand)
			throws ShitheadException {
		if (playerType.equals("h"))
			return new HumanPlayer(name, cardsPerHand) ;
		else {
		    Class<? extends ComputerPlayer> playerClass = players.get(playerType) ;
		    if (playerClass == null)
		        throw new ShitheadException("Trying to create no existent player type : " + playerType) ;
		    try {
		        Constructor<? extends ComputerPlayer> cons = playerClass.getConstructor(String.class, Integer.TYPE) ;
		        return (cons.newInstance(name, cardsPerHand)) ;
		    } catch (NoSuchMethodException nsme) {
		        throw new ShitheadException("Error creating new computer player") ;
		    } catch (InvocationTargetException ite) {
		        throw new ShitheadException("Error creating new computer player") ;
            } catch (IllegalAccessException ite) {
                throw new ShitheadException("Error creating new computer player") ;
            } catch (InstantiationException ite) {
                throw new ShitheadException("Error creating new computer player") ;
            }
		}
	}
	
	public static Map<String, String> computerPlayerList() {
	    Map<String, String> result = new HashMap<String, String>() ;

	    for (String key : players.keySet()) {
	        Class<? extends ComputerPlayer> player = players.get(key) ;
	        result.put(getShortClassName(player), key) ;
	    }
	    
	    return result ;
	}

    public static Map<String, String> computerPlayerDescriptions() throws ShitheadException {
        Map<String, String> result = new HashMap<String, String>() ;
        
        try {
        
            for(String key : players.keySet()) {
                Class<? extends ComputerPlayer> player = players.get(key) ;
                Field descField = player.getField("description") ;
                String description = (String) descField.get(null) ;
                result.put(getShortClassName(player), description) ; 
            }
        } catch (IllegalAccessException iae) {
            throw new ShitheadException("Error getting computer player description") ;
        } catch (NoSuchFieldException fsfe) {
            throw new ShitheadException("Error getting computer player description") ;
        }
        return result ;
    }

    private static String getShortClassName(Class<? extends ComputerPlayer> player) {
        return player.getName().substring(player.getPackage().getName().length() + 1) ;
    }
}
