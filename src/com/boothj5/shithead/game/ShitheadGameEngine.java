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

		numPlayers = console.getNumPlayers() ;
		numCards = console.getNumCardsPerHand() ;
		
		String name, type ;
		for (int i = 1 ; i <= numPlayers ; i++) { 
			name = console.getPlayerName(i) ;
			playerNames.add(name) ;
				
			type = console.getPlayerType(name) ;
			playerTypes.add(type) ;
		}
		
		game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCards) ;		
	}
	
	private void deal() {
		game.deal() ;
		ShitheadGameDetails details = game.getGameDetails() ;
		showGame(details);
		
		c.readLine("Cards dealt, press enter to continue:") ;
	}

	private void swap() {
		ShitheadGameDetails details = game.getGameDetails() ;
		
		for (Player player : details.getPlayers()) {
			
			// ask player if they want to swap cards
			String swap = player.askSwapMore() ;
			
			// if we get a y or n, its a computer player
			if ("y".equals(swap)) {
				SwapResponse response = player.askSwapChoice() ;
				player.swapCards(response) ;
				
				// again and keep swapping until 'n'
				swap = player.askSwapMore() ;
				while ("y".equals(swap)) {
					response = player.askSwapChoice() ;
					player.swapCards(response) ;
					swap = player.askSwapMore() ;
				}
			}
			
			// if we got null, its a human and we need to interact
			else if (null == swap) {
				ShitheadConsole.clearScreen() ;
				showPlayerName(details, player, false);

				System.out.println() ;

				showHand(details, player, false) ;
				showFaceUp(player) ;

				System.out.println() ;
				swap = c.readLine(player.getName() + 
								", do you want to swap cards (y/n) ? ") ;
				
				if ("y".equals(swap)) {
					boolean keepSwapping = true ;
		    
					while (keepSwapping) {
						int cardFromHand = Integer.parseInt(
								c.readLine("Which card from your" + 
										" hand do you want to swap (1-" + details.getNumCardsPerHand() + ") ? ")) ;

						int cardFromPile = Integer.parseInt(
								c.readLine("Which card from the " + 
										"pile do you want to swap (1-" + details.getNumCardsPerHand() + ") ? ")) ;

						SwapResponse response = new SwapResponse(cardFromHand-1, cardFromPile-1) ;
						player.swapCards(response) ;

						ShitheadConsole.clearScreen() ;
						System.out.println() ;
						showHand(details, player, false) ;
						showFaceUp(player) ;
						System.out.println() ;

						String swapAgain = c.readLine(player.getName() + 
									", do you want to swap more cards (y/n) ? ") ;

						keepSwapping = ("y".equals(swapAgain)) ;
					}
				}
			}
		}
		
		details = game.getGameDetails() ;
		showGame(details) ;
	}
	
	private void firstMove() {
		game.firstMove() ;
		ShitheadGameDetails details = game.getGameDetails() ;
		showGame(details) ;
		showLastMove(details) ;
	}
	
	private void play() {
		
	}
	
	private void end() {
		
	}

	private void showLastMove(ShitheadGameDetails details) {
		LastMove lastMove = details.getLastmove() ;
		
		StringBuffer buffer = new StringBuffer() ;
		for (Card card : lastMove.getCards())
			buffer.append(card.toString() + ", ") ;
		System.out.print(lastMove.getPlayer().getName() + " played: " + buffer.toString()) ;
	}
	
	private void showGame(ShitheadGameDetails details) {
		ShitheadConsole.clearScreen() ;
		
		// show pile
	    int pileRemaining = details.getPile().size() ;
		System.out.println(pileRemaining + " on pile:") ;

		for (int i = pileRemaining - 1 ; i >= 0 ; i--) {
			Card card = details.getPile().get(i) ;
			if (card.equals(details.getPile().peek())) 
				System.out.println("\t(*)" + card.toString()) ;
			else
				System.out.println("\t" + card.toString()) ;
		}
		System.out.println() ;
		
		// deck left
		System.out.println(details.getDeck().getSize() + " remaing on deck") ;
		System.out.println() ;		
		
		// burnt
		System.out.println(details.getBurnt().size() + " burnt") ;
		System.out.println() ;		
		
		for (Player player : details.getPlayers()) {
			showPlayerName(details, player, true);
			showHand(details, player, true);
			showFaceUp(player);
			showFaceDown(player);

			System.out.println() ;
		}
	}

	private void showFaceDown(Player player) {
		// player face down
		System.out.print("FACE UP: ") ;
		for (Card card : player.getHand(Player.Hand.FACEDOWN)) {
			System.out.print("****, ") ;
		}			
		System.out.println() ;
	}

	private void showFaceUp(Player player) {
		// player face up
		System.out.print("FACE UP: ") ;
		for (Card card : player.getHand(Player.Hand.FACEUP)) {
			System.out.print(card + ", ") ;
		}			
		System.out.println() ;
	}

	private void showHand(ShitheadGameDetails details, Player player, boolean hideWhenNotCurrent) {
		if (hideWhenNotCurrent && !details.isCurrentPlayer(player)) {
			System.out.print("HAND:    ") ;
			System.out.println(player.getHand(Player.Hand.HAND).size() + " cards."); 
		}
		else {
				System.out.print("HAND:    ") ;
				for (Card card : player.getHand(Player.Hand.HAND)) { 
					System.out.print(card + ", ") ;
				}
				System.out.println() ;
			}
	}

	private void showPlayerName(ShitheadGameDetails details, Player player, boolean indicateCurrent) {
		String currentPlayer = "" ;
		if (indicateCurrent && (details.isCurrentPlayer(player))) 
			currentPlayer = "(*)" ;
		// player name
		System.out.println("-----------------------------------------") ;
		System.out.println("PLAYER" + currentPlayer + ":" + player.getName()) ;
		System.out.println("-----------------------------------------") ;
	}

	


	
}
