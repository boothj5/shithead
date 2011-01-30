package com.boothj5.shithead.player.computer;

import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.player.ComputerPlayer;
import com.boothj5.shithead.player.Player;
import com.boothj5.shithead.player.SwapResponse;
import com.boothj5.shithead.card.*;

import java.util.*;


public class FaceDownChecker extends ComputerPlayer {

	public FaceDownChecker(String name, int handSize) {
		super(name, handSize) ;
	}
	
	public Boolean askSwapMore() {
		return new Boolean(false) ;
	}
	
	public SwapResponse askSwapChoice() {
		return null ;
	}
	
	public List<Integer> askCardChoiceFromHand(ShitheadGameDetails details) {
		List<Integer> chosenCards = null ;
		List<Card> myHand = getHand() ;

		Player nextPlayer = getNextPlayer(details) ;
		
		if ((nextPlayer.getHand().size() == 0) && (nextPlayer.getFaceUp().size() == 0) ) {
			return pickHighCards(details, myHand);
		}
		
		if (details.getPile().size() > 5) {
			Card burnCard = getBurnCardInHand(myHand) ;
			if (burnCard != null) {
				chosenCards = new ArrayList<Integer>() ;
				chosenCards.add(myHand.indexOf(burnCard)) ;
				return chosenCards ; 
			}
			else 
				return pickLowCards(details, myHand);
		}
		else 
			return pickLowCards(details, myHand);
	}
	
	private Player getNextPlayer(ShitheadGameDetails details) {
		int nextPlayer = details.getCurrentIndex() ;
		List<Player> players = details.getPlayers() ;
		
		nextPlayer ++ ;
		if (nextPlayer >= players.size())
			nextPlayer = 0 ;
		while (!players.get(nextPlayer).hasCards()) {
			nextPlayer++ ;
			if (nextPlayer >= players.size())
				nextPlayer = 0 ;
		}
		return details.getPlayers().get(nextPlayer) ;
	}
	

	public List<Integer> askCardChoiceFromFaceUp(ShitheadGameDetails details) {
		List<Integer> chosenCards = null ;
		List<Card> myHand = getFaceUp() ;

		if (details.getPile().size() > 5) {
			Card burnCard = getBurnCardInHand(myHand) ;
			if (burnCard != null) {
				chosenCards = new ArrayList<Integer>() ;
				chosenCards.add(myHand.indexOf(burnCard)) ;
				return chosenCards ;
			}
			else 
				return pickLowCards(details, myHand);
		}	
		else 
			return pickLowCards(details, myHand);
	}
	
}
