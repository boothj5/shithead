package com.boothj5.shithead.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerFactory;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public class BattleEngine implements ShitheadEngine {
	ShitheadGame game ;
	ShitheadCli cli = new ShitheadCli() ;
	List<String> playerNames = new ArrayList<String>() ; 
	List<String> playerTypes = Arrays.asList("l", "f", "d", "p", "r", "a", "s") ;
    Map<String, Integer> shitheadMap = new HashMap<String, Integer>() ;
    int numPlayers = playerTypes.size() ;
    int numCards, numGames, turns ;
	int stalemates = 0 ;
	long startTime, stopTime, duration ;
	boolean stalemate ;
	
	public void runEngine(String[] args) {
		try {
			globalInit(args) ;
			for (int i = 0 ; i < numGames ; i++) {
		        init() ;
				deal() ;
				swap() ;
				firstMove() ;
				play() ;
				end() ;
			}
			battleSummary() ;
		} catch (Exception e) {
			ShitheadGameDetails details = game.getGameDetails() ;
			cli.bail(e, details) ;
		}
	}
	
	private void globalInit(String[] args) throws Exception {
		cli.line() ;
		cli.welcome() ;
		numCards = 3 ;
		numGames = Integer.parseInt(args[1]) ;
	    playerNames = getPlayerNamesFromTypes(numCards, playerTypes) ;
        shitheadMap = createShitheadMap(playerNames) ;
        startTime = System.currentTimeMillis() ;
	}
	
	private void init() throws Exception {
        Collections.shuffle(playerTypes) ;      
        playerNames = getPlayerNamesFromTypes(numCards, playerTypes) ;
        game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCards) ;
        turns = 0 ;
        stalemate = false ;
	}
	
	private static Map<String, Integer> createShitheadMap(List<String> names) {
        Map<String, Integer> result = new HashMap<String, Integer>() ;
	    
	    for (String playerName : names) {
            result.put(playerName, 0) ;
        }
        return result ;
	}

	private static List<String> getPlayerNamesFromTypes(int num, List<String> types) throws Exception {
	    String name = null;
	    String namePrefix = "Computer-";
	    List<String> result = new ArrayList<String>() ;
        
	    for (int i = 0 ; i < types.size() ; i++) { 
            String className = (PlayerFactory.createPlayer(types.get(i), namePrefix, num)).getClass().getName() ;
            StringTokenizer st = new StringTokenizer(className, ".") ;
            while (st.hasMoreTokens())
                name = st.nextToken();
            result.add(name) ;
        }
	    
	    return result ;
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
				stalemate = true ;
				stalemates++ ;
				return ;
			}
			else {
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
                            PlayerHelper helper = game.getPlayerHelper() ;
                            
                            details = game.getGameDetails() ;
    
                            if (game.playingFromFaceUp()) 
                                cardChoice = currentPlayer.askCardChoiceFromFaceUp(helper) ;                        
                            else // play from hand
                                cardChoice = currentPlayer.askCardChoiceFromHand(helper) ;                      
                                
                            // if its a valid move play
                            if (game.checkValidMove(cardChoice)) 
                                game.play(cardChoice) ;
    
                            // otherwise, computers mustn't try invalid moves when we ask them
                            // we could get stuck asking them forever
                            else {
                                String name = currentPlayer.getName() ;
                                Card card ;
                                
                                if (game.playingFromHand()) 
                                    card = currentPlayer.getHand().get(0) ;
                                else
                                    card = currentPlayer.getFaceUp().get(0) ;
                                throw new Exception("Computer player chose invalid move, player:" + 
                                                            name + ", card:" + card) ;
                            }
                            // move game on
                            game.moveToNextPlayer() ;
                        }
                    }
                    else { // else if human player
                        cli.bail(new Exception("Cannot have human player in computer battle!!"), details) ;
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
	}

	private void end() throws Exception {
		if (!stalemate) {
			String shithead = game.getShithead() ;
			int total = shitheadMap.get(shithead) ;
			total++ ;
			shitheadMap.put(shithead, total) ;
		}
		//cli.dot() ;
		//cli.showMidBattleSummary(shitheadMap, turns, stalemate) ;
	}
	
	private void battleSummary() {
        stopTime = System.currentTimeMillis() ;
        duration = stopTime - startTime ;
	    cli.line() ;
		Map<String, Integer> sortedShitheads = sortHashMapByValues(shitheadMap) ;
		cli.showBattleSummary(sortedShitheads, stalemates, duration) ;		
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