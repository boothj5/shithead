package com.boothj5.shithead.game;

import java.io.Console ;
import java.util.* ;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.player.Player;
import com.boothj5.shithead.player.SwapResponse;

public class ShitheadGameEngine {

	ShitheadGame game ;
	ShitheadConsole console = new ShitheadConsole() ;
	
	public void playShithead() throws Exception {
		init() ;
		deal() ;
		swap() ;
		firstMove() ;
		play() ;
		end() ;
	}
	
	private void init() throws Exception {
		int numPlayers ;
		int numCards ;
		List<String> playerNames = new ArrayList<String>() ;
		List<String> playerTypes = new ArrayList<String>() ;

		console.clearScreen() ;
		console.welcome() ;

		numPlayers = console.requestNumPlayers() ;
		numCards = console.requestNumCardsPerHand() ;
		
		String name, type ;
		for (int i = 1 ; i <= numPlayers ; i++) { 
			name = console.requestPlayerName(i) ;
			playerNames.add(name) ;
				
			type = console.requestPlayerType(name) ;
			playerTypes.add(type) ;
		}
		
		game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCards) ;		
	}
	
	private void deal() {
		game.deal() ;
		
		ShitheadGameDetails details = game.getGameDetails() ;
		console.showGame(details);
		
		console.waitOnUser("Cards dealt, press enter:") ;
	}

	private void swap() {
		ShitheadGameDetails details = game.getGameDetails() ;
		
		for (Player player : details.getPlayers()) {
			
			// ask player if they want to swap cards
			Boolean wantsToSwap = player.askSwapMore() ;
			
			// if we got null, its a human and we need to interact
			if (null == wantsToSwap) {
				console.clearScreen() ;
				console.showPlayerName(details, player, false);
				console.line() ;
				console.showHand(details, player, false) ;
				console.showFaceUp(player) ;
				console.line() ;
				
				// auto boxing
				wantsToSwap = console.requestIfWantsToSwapCards(player.getName()) ;
				
				while (wantsToSwap) {
					int cardFromHand = console.requestCardFromHandToSwap(details.getNumCardsPerHand()) ;
					int cardFromPile = console.requestCardFromPileToSwap(details.getNumCardsPerHand()) ;

					SwapResponse response = new SwapResponse(cardFromHand, cardFromPile) ;
					player.swapCards(response) ;

					console.clearScreen() ;
					console.line() ;
					console.showHand(details, player, false) ;
					console.showFaceUp(player) ;
					console.line() ;
					
					wantsToSwap = console.requestIfWantsToSwapCards(player.getName()) ;
				}
			}

			// if we get a true of false, its a computer player
			else if (wantsToSwap) {
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
		
		details = game.getGameDetails() ;
		console.showGame(details) ;
	}
	
	private void firstMove() {
		game.firstMove() ;
		ShitheadGameDetails details = game.getGameDetails() ;

		console.showGame(details) ;
		console.showLastMove(details) ;
	}
	
	private void play() {
		
	}
	
	private void end() {
		
	}
}
