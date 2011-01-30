package com.boothj5.shithead.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.player.Player;
import com.boothj5.shithead.player.SwapResponse;

public class ComputerBattleConsoleEngine implements ShitheadEngine {
	ShitheadGame game ;
	ShitheadConsole console = new ShitheadConsole() ;
	int numPlayers, numCards, numGames, turns ;
	List<String> playerNames = new ArrayList<String>() ;
	List<String> playerTypes = new ArrayList<String>() ;
	
	Map<String, Integer> shitheadMap = new HashMap<String, Integer>() ;
	
	List<Integer> numTimesShithead = new ArrayList<Integer>() ;

	
	public void playShithead(String[] args) {
		try {
			init(args) ;
			
			for (int i = 0 ; i < numGames ; i++) {
				turns = 0 ;
				game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCards) ;
				deal() ;
				swap() ;
				firstMove() ;
				console.line() ;
				play() ;
				end() ; 
			}
			
			finish() ;
		} catch (Exception e) {
			ShitheadGameDetails details = game.getGameDetails() ;
			console.bail(e, details) ;
		}
	}
	
	private void init(String[] args) throws Exception {
		console.clearScreen() ;
		console.welcome() ;

		numPlayers = 2 ;
		numCards = 3 ;
		numGames = Integer.parseInt(args[0]) ;

		String name, type;
		String namePrefix = "Computer-";
		
		
		for (int i = 1 ; i <= numPlayers ; i++) { 
			name = namePrefix + i ;
			playerNames.add(name) ;
			shitheadMap.put(name, 0) ;
		}	
		playerTypes.add("s") ;
		playerTypes.add("s") ;
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
				throw new Exception("ERROR - Game got stuck in a loop, " + turns + " turns played!") ;
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

	private void end() throws Exception {
		String shithead = game.getShithead() ;
		int total = shitheadMap.get(shithead) ;
		total++ ;
		shitheadMap.put(shithead, total) ;
		console.showMidBattleSummary(shitheadMap, turns) ;

	}
	
	private void finish() {
		console.line() ;
		console.showBattleSummary(shitheadMap) ;		
	}

}