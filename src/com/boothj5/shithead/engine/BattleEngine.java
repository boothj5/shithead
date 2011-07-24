package com.boothj5.shithead.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerFactory;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public class BattleEngine extends ShitheadEngine {
    ShitheadCli cli = new ShitheadCli() ;
    List<String> playerNames = new ArrayList<String>() ; 
	List<String> playerTypes = Arrays.asList("l", "f", "d", "p", "r", "a", "s") ;
    Map<String, Integer> shitheadMap = new HashMap<String, Integer>() ;
    int numPlayers = playerTypes.size() ;
    int numCards, turns ;
	int stalemates = 0 ;
	long startTime, stopTime, duration ;
	boolean stalemate ;
	
	@Override
	public void globalInit(String[] args) throws ShitheadException {
		cli.line() ;
		cli.welcome() ;
		numCards = 3 ;
		numGames = Integer.parseInt(args[1]) ;
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
	public void swap() {
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
                                throw new ShitheadException("Computer player chose invalid move, player:" + 
                                                            name + ", card:" + card) ;
                            }
                            // move game on
                            game.moveToNextPlayer() ;
                        }
                    }
                    else { // else if human player
                        cli.bail(new ShitheadException("Cannot have human player in computer battle!!"), details) ;
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

	@Override
	public void end() throws ShitheadException {
		if (!stalemate) {
			String shithead = game.getShithead() ;
			int total = shitheadMap.get(shithead) ;
			total++ ;
			shitheadMap.put(shithead, total) ;
		}
		//cli.dot() ;
		//cli.showMidBattleSummary(shitheadMap, turns, stalemate) ;
	}
	
    @Override
	public void globalEnd() {
        stopTime = System.currentTimeMillis() ;
        duration = stopTime - startTime ;
	    cli.line() ;
		Map<String, Integer> sortedShitheads = sortHashMapByValues(shitheadMap) ;
		cli.showBattleSummary(sortedShitheads, stalemates, duration) ;		
	}

    @Override
    public void error(ShitheadException e) {
        ShitheadGameDetails details = game.getGameDetails() ;
        cli.bail(e, details) ;
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

   private static Map<String, Integer> createShitheadMap(List<String> names) {
        Map<String, Integer> result = new HashMap<String, Integer>() ;
        
        for (String playerName : names) {
            result.put(playerName, 0) ;
        }
        return result ;
    }

    private static List<String> getPlayerNamesFromTypes(int num, List<String> types) throws ShitheadException {
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
}