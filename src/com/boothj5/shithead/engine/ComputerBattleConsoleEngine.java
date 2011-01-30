package com.boothj5.shithead.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.player.Player;
import com.boothj5.shithead.player.PlayerFactory;
import com.boothj5.shithead.player.SwapResponse;

public class ComputerBattleConsoleEngine implements ShitheadEngine {
	ShitheadGame game ;
	ShitheadConsole console = new ShitheadConsole() ;
	int numPlayers, numCards, numGames, turns ;
	List<String> playerNames = new ArrayList<String>() ;
	List<String> playerTypes = new ArrayList<String>() ;
	
	Map<String, Integer> shitheadMap = new HashMap<String, Integer>() ;
	int stalemates = 0 ;
	
	List<Integer> numTimesShithead = new ArrayList<Integer>() ;

	
	public void playShithead(String[] args) {
		try {
			boolean stalemate = false ;
			init(args) ;
			
			long start = System.currentTimeMillis() ;
			for (int i = 0 ; i < numGames ; i++) {
				try {
					turns = 0 ;
					stalemate = false ;
					game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCards) ;
					deal() ;
					swap() ;
					firstMove() ;
					play() ;
				} catch (StalemateException e) {
					stalemates++ ;
					stalemate = true ;
				}
				end(stalemate) ;
			}
			
			long stop = System.currentTimeMillis() ;
			long time = stop - start ;
			
			finish(time) ;
		} catch (Exception e) {
			ShitheadGameDetails details = game.getGameDetails() ;
			console.bail(e, details) ;
		}
	}
	
	private void init(String[] args) throws Exception {
		console.clearScreen() ;
		console.welcome() ;

		numCards = 3 ;
		numGames = Integer.parseInt(args[1]) ;

		String name = null;
		String namePrefix = "Computer-";
		
		playerTypes.add("s") ;
		playerTypes.add("a") ;
		playerTypes.add("r") ;
		playerTypes.add("p") ;
		playerTypes.add("d") ;
		playerTypes.add("f") ;
		playerTypes.add("l") ;

		numPlayers = playerTypes.size() ;
	
		
		for (int i = 0 ; i < numPlayers ; i++) { 
			String className = (PlayerFactory.createPlayer(playerTypes.get(i), namePrefix, numCards)).getClass().getName() + "-" + (i+1) ;
			StringTokenizer st = new StringTokenizer(className, ".") ;
			while (st.hasMoreTokens())
				name = st.nextToken();
			
			playerNames.add(name) ;
			shitheadMap.put(name, 0) ;
		}	
	}

	
	private void deal() {
		game.deal() ;
	}

	private void swap() {
		ShitheadGameDetails details = game.getGameDetails() ;
		
		for (Player player : details.getPlayers()) {
			
			// ask player if they want to swap cards
			Boolean wantsToSwap = player.askSwapMore() ;

			// if we get a true of false, its a computer player
			if (wantsToSwap) {
				SwapResponse response = player.askSwapChoice() ;
				player.swapCards(response) ;
				
				// again and keep swapping until 'n'
				wantsToSwap = player.askSwapMore() ;
				while (wantsToSwap) {
					response = player.askSwapChoice() ;
					player.swapCards(response) ;
					wantsToSwap = player.askSwapMore() ;
				}
			}
		}
	}
	
	private void firstMove() {
		game.firstMove() ;
	}
	
	private void play() throws Exception {	
		ShitheadGameDetails details ;
		
		// while no loser
		while (game.canContinueGame()) {
			details = game.getGameDetails() ;

			if (turns == 10000) {
				throw new StalemateException("ERROR - Game got stuck in a loop, " + turns + " turns played!") ;
			}
			
		    Player currentPlayer = details.getCurrentPlayer() ;
		    List<Integer> cardChoice = new ArrayList<Integer>() ;
		    
		    // if player can possibly lay any cards
		    if (game.currentPlayerCanPlay()) {

		    	// if computer player
		    	if (game.isCurrentPlayerComputerPlayer()) {
		    		
		    		// if face down, pick for computer, as we don't want any cheating!!
		    		if (game.playingFromFaceDown()) {
		    			cardChoice.add(0) ;

		    			// play if valid card
		    			if (game.checkValidMove(cardChoice)) 
				    		game.play(cardChoice) ;
		    			// pick up if not
		    			else 
		    				game.playerPickUpPileAndFaceDownCard(cardChoice.get(0)) ;
				    	
		    			// move game on
		    			game.moveToNextPlayer() ;
		    		}
		    		// otherwise ask it to choose a card
		    		else {
				    	details = game.getGameDetails() ;

		    			if (game.playingFromFaceUp()) 
			    			cardChoice = currentPlayer.askCardChoiceFromFaceUp(details) ;				    	
			    		else // play from hand
			    			cardChoice = currentPlayer.askCardChoiceFromHand(details) ;				    	
			    			
		    			// if its a valid move play
		    			if (game.checkValidMove(cardChoice)) 
		    				game.play(cardChoice) ;

		    			// otherwise, computers mustn't try invalid moves when we ask them
		    			// we could get stuck asking them forever
		    			else
		    				throw new Exception("Computer player chose invalid move") ;

		    			// move game on
		    			game.moveToNextPlayer() ;
		    		}
		    	}
		    	else { // else if human player
		    		console.bail(new Exception("Cannot have human player in computer battle!!"), details) ;
		    	}
		    }
		    // current player cannot actually play
		    else {
	    		// make them pick up and move game on
		    	game.playerPickUpPile() ;
		    	game.moveToNextPlayer() ;
		    }
		    
		    turns++ ;
		}
	}

	private void end(boolean stalemate) throws Exception {
		if (!stalemate) {
			String shithead = game.getShithead() ;
			int total = shitheadMap.get(shithead) ;
			total++ ;
			shitheadMap.put(shithead, total) ;
		}
		console.dot() ;
		//console.showMidBattleSummary(shitheadMap, turns, stalemate) ;

	}
	
	private void finish(long time) {
		console.line() ;
		Map<String, Integer> sortedShitheads = sortHashMapByValues(shitheadMap) ;
		console.showBattleSummary(sortedShitheads, stalemates, time) ;		
	}

	private LinkedHashMap<String, Integer> sortHashMapByValues(Map<String, Integer> originalMap) {
	    List<String> sortedKeys = new ArrayList<String>(originalMap.keySet());
	    List<Integer> sortedValues = new ArrayList<Integer>(originalMap.values());
	    Collections.sort(sortedValues);
	    Collections.sort(sortedKeys);
	        
	    LinkedHashMap<String, Integer> newSortedMap = new LinkedHashMap<String, Integer>();
	    
	    for (Integer val : sortedValues) {
	        for (String key : sortedKeys) {
	            Integer valFromOriginalMap = originalMap.get(key);
	            Integer valFromSortedValues = val;
	            
	            if (valFromOriginalMap.equals(valFromSortedValues)) {
	                originalMap.remove(key);
	                sortedKeys.remove(key);
	                newSortedMap.put(key, val);
	                break;
	            }
	        }
	    }
	    return newSortedMap;
	}	
}