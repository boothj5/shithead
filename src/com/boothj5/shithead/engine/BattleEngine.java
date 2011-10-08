package com.boothj5.shithead.engine;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerFactory;
import com.boothj5.shithead.ui.cli.ShitheadCli;
import com.boothj5.util.MapUtil;

public class BattleEngine extends ShitheadEngine {
    final ShitheadCli cli ;
    List<String> playerNames = new ArrayList<String>() ; 
	List<String> playerTypes = new ArrayList<String>() ;
    Map<String, Integer> shitheadMap = new HashMap<String, Integer>() ;
    int numPlayers ;
    int numCards, turns ;
	int stalemates = 0 ;
	long startTime, stopTime, duration ; 
	float avg ;
	boolean stalemate ;

	public BattleEngine(ShitheadCli cli) {
	    this.cli = cli ;
	}
	
	@Override
	public void globalInit(String[] args) throws ShitheadException {
		cli.line() ;
		cli.welcome() ;
		numCards = 3 ;
		numGames = Integer.parseInt(args[1]) ;
		Map<String, String> compPlayerList = PlayerFactory.computerPlayerList();
	    
		for (String shortName : compPlayerList.keySet()) {
		    playerTypes.add(compPlayerList.get(shortName)) ;
		}
		numPlayers = playerTypes.size() ;
		playerNames = getPlayerNamesFromTypes(numCards, playerTypes) ;
        shitheadMap = createShitheadMap(playerNames) ;
        startTime = System.currentTimeMillis() ;
	}
	
	@Override
	public void init() throws ShitheadException {
        Collections.shuffle(playerTypes) ;      
        playerNames = getPlayerNamesFromTypes(numCards, playerTypes) ;
        game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCards) ;
        turns = 0 ;
        stalemate = false ;
	}
	
	@Override
	public void deal() {
		game.deal() ;
	}

	@Override
	public void swap() throws ShitheadException {
		ShitheadGameDetails details = game.getGameDetails() ;
		
		for (Player player : details.getPlayers()) {
			computerPlayerSwap(player) ;
		}
	}
	
	@Override
	public void firstMove() {
		game.firstMove() ;
	}
	
	@Override
	public void play() throws ShitheadException {	
		while (game.canContinueGame()) {
			if (turns == 10000) {
				stalemate = true ;
				stalemates++ ;
				return ;
			}
			else {
                Player currentPlayer = game.getCurrentPlayer() ;
                
                if (game.currentPlayerCanPlay()) {
                   if (currentPlayer.isComputer()) {
                        if (game.playingFromFaceDown())
                        	computerPlayerFaceDownMove() ;
                        else
                        	computerPlayerMove() ;
                    }
                    else { // human player
                        ShitheadGameDetails details = game.getGameDetails() ;
                    	cli.bail(new ShitheadException("Cannot have human player in computer battle!!"), details) ;
                    }
                }
                else {
                    game.playerPickUpPile() ;
                    game.moveToNextPlayer() ;
                }
                turns++ ;
			}
		}
	}

	@Override
	public void end() throws ShitheadException {
		if (!stalemate) {
			String shithead = game.getShithead() ;
			int total = shitheadMap.get(shithead) ;
			total++ ;
			shitheadMap.put(shithead, total) ;
		}
	}
	
    @Override
	public void globalEnd() {
        stopTime = System.currentTimeMillis() ;
        duration = stopTime - startTime ;
    	DecimalFormat twoPlaces = new DecimalFormat("#.##");
        avg = (new Float(duration) / new Float(numGames)) ;
        float avgRounded = Float.valueOf(twoPlaces.format(avg)) ;
        
	    cli.line() ;
		Map<String, Integer> sortedShitheads = MapUtil.sortHashMapByValues(shitheadMap) ;
		cli.showBattleSummary(sortedShitheads, stalemates, duration, avgRounded) ;		
	}

    @Override
    public void error(ShitheadException e) {
        ShitheadGameDetails details = game.getGameDetails() ;
        cli.bail(e, details) ;
    }
    
    private static Map<String, Integer> createShitheadMap(List<String> names) {
        Map<String, Integer> result = new HashMap<String, Integer>() ;
        
        for (String playerName : names) {
            result.put(playerName, 0) ;
        }
        return result ;
    }

    private static List<String> getPlayerNamesFromTypes(int numCards, List<String> types) throws ShitheadException {
        String name = null;
        String namePrefix = "Computer-";
        List<String> result = new ArrayList<String>() ;
        
        for (int i = 0 ; i < types.size() ; i++) { 
            String className = (PlayerFactory.createPlayer(types.get(i), namePrefix, numCards)).getClass().getName() ;
            StringTokenizer st = new StringTokenizer(className, ".") ;
            while (st.hasMoreTokens())
                name = st.nextToken();
            result.add(name) ;
        }
        
        return result ;
    }
}