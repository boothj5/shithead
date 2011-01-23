package com.boothj5.shithead.game;

import java.io.Console;
import java.util.* ;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.player.Player;

public class ShitheadConsole {
	
	Console c = System.console();
	
	public void clearScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n") ;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n") ;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n") ;
	}
	
	public void welcome() {
		System.out.println("Welcome to JavaHead!") ;
		System.out.println() ;
	}
	
	public int requestNumPlayers() {
		return Integer.parseInt(c.readLine("How many players? ")) ;

	}
	
	public int requestNumCardsPerHand() {
		return Integer.parseInt(c.readLine("How many cards each? ")) ;
		
	}
	
	public String requestPlayerName(int playerNumber) {
		return (c.readLine("Enter name for player " + playerNumber + ": ")) ;
	}
	
	public String requestPlayerType(String playerName) {
		showPlayerTypes() ;
		return (c.readLine("Player type for  " + playerName + ": ")) ;
	}
	
	private void showPlayerTypes() {
		System.out.println("(h)uman  - Human player") ;
		System.out.println("(s)imple - A very simple computer player") ;
	}

	public void waitOnUser(String message) {
		c.readLine(message) ;
	}
	
	public boolean requestIfWantsToSwapCards(String playerName) {
		String swap = c.readLine(playerName + 
				", do you want to swap cards (y/n) ? ") ;
		
		return "y".equals(swap) ? true : false ;
	}
	
	public int requestCardFromHandToSwap(int upperLimit) {
		int cardFromHand = Integer.parseInt(
			c.readLine("Which card from your" + 
					" hand do you want to swap (1-" + upperLimit + ") ? ")) ;

		return (cardFromHand-1);
	}
	
	public int requestCardFromPileToSwap(int upperLimit) {
		int cardFromPile = Integer.parseInt(
			c.readLine("Which card from the " + 
					"pile do you want to swap (1-" + upperLimit + ") ? ")) ;
		return (cardFromPile-1);
	}

	public List<Integer> requestMove(String playerName, Player.Hand handToPlayFrom, int handSize, boolean invalidAttempt) {
		String invalid = "" ;
		
		if (invalidAttempt)
			invalid += "YOU CAN'T DO THAT! " ;
		
		String cardsString = c.readLine(invalid + playerName + ", enter cards to lay: ") ;
		StringTokenizer st = new StringTokenizer(cardsString, ",") ;
		
		List<Integer> choices = new ArrayList<Integer>() ;
		
		while (st.hasMoreElements()) {
			choices.add(Integer.parseInt((String)st.nextElement())-1) ;
		}
		
		return choices ;
		
	}
	
	public void showGame(ShitheadGameDetails details) {
		clearScreen() ;
		
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
	
	public void showLastMove(ShitheadGameDetails details) {
		LastMove lastMove = details.getLastmove() ;
		
		StringBuffer buffer = new StringBuffer() ;
		for (Card card : lastMove.getCards())
			buffer.append(card.toString() + ", ") ;
		System.out.print(lastMove.getPlayer().getName() + " played: " + buffer.toString()) ;
	}
	


	public void showFaceDown(Player player) {
		// player face down
		System.out.print("FACE UP: ") ;
		for (Card card : player.getHand(Player.Hand.FACEDOWN)) {
			System.out.print("****, ") ;
		}			
		System.out.println() ;
	}

	public void showFaceUp(Player player) {
		// player face up
		System.out.print("FACE UP: ") ;
		for (Card card : player.getHand(Player.Hand.FACEUP)) {
			System.out.print(card + ", ") ;
		}			
		System.out.println() ;
	}

	public void showHand(ShitheadGameDetails details, Player player, boolean hideWhenNotCurrent) {
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

	public void showPlayerName(ShitheadGameDetails details, Player player, boolean indicateCurrent) {
		String currentPlayer = "" ;
		if (indicateCurrent && (details.isCurrentPlayer(player))) 
			currentPlayer = "(*)" ;
		// player name
		System.out.println("-----------------------------------------") ;
		System.out.println("PLAYER" + currentPlayer + ":" + player.getName()) ;
		System.out.println("-----------------------------------------") ;
	}
	
	public void line() {
		System.out.println() ;
	}
	
	
}
