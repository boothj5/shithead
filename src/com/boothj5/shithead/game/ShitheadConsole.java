package com.boothj5.shithead.game;

import java.io.Console;

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
	
	public int getNumPlayers() {
		return Integer.parseInt(c.readLine("How many players? ")) ;

	}
	
	public int getNumCardsPerHand() {
		return Integer.parseInt(c.readLine("How many cards each? ")) ;
		
	}
	
	
	public String getPlayerName(int playerNumber) {
		return (c.readLine("Enter name for player " + playerNumber + ": ")) ;
	}
	
	public String getPlayerType(String playerName) {
		showPlayerTypes() ;
		return (c.readLine("Player type for  " + playerName + ": ")) ;
	}
	
	private void showPlayerTypes() {
		System.out.println("(h)uman  - Human player") ;
		System.out.println("(s)imple - A very simple computer player") ;
	}
	
}
