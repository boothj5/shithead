package com.boothj5.shithead.player;

import com.boothj5.shithead.game.ShitheadGameDetails;

import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.game.ShitheadRules;
import com.boothj5.shithead.card.*;

import java.util.*;


public class SimpleComputerPlayer extends ComputerPlayer {

	public SimpleComputerPlayer(String name, int handSize) {
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
		
		// look through my hand and find the first card that I am allowed to lay
		List<Card> myHand = getHand() ;
		return pickCards(details, chosenCards, myHand);
	}

	private List<Integer> pickCards(ShitheadGameDetails details,
			List<Integer> chosenCards, List<Card> myHand) {
		for (Card tryCard : myHand) {
			if (checkValidMove(tryCard, details)) {
				int chosen = myHand.indexOf(tryCard) ;
				chosenCards = new ArrayList<Integer>() ;
				chosenCards.add(chosen) ;
				break ;
			}
		}
		return chosenCards;
	}	
	

	public List<Integer> askCardChoiceFromFaceUp(ShitheadGameDetails details) {
		List<Integer> chosenCards = null ;
		
		// look through my hand and find the first card that I am allowed to lay
		List<Card> myHand = getFaceUp() ;
		return pickCards(details, chosenCards, myHand);
	}	
	
	private boolean checkValidMove(Card cardToLay, ShitheadGameDetails details) {
		Stack<Card> pile = details.getPile() ;
		if (pile.empty()) 
			return true ;
		else if (Card.Rank.SEVEN.equals(pile.peek().rank)) {
			//look for first non invisible and check that
			Card testCard = pile.peek() ;
			for (int i = pile.size() -1 ; (i >=0 && (testCard.rank.equals(ShitheadRules.INVISIBLE))) ; i-- ) {
				testCard = pile.get(i) ;
			}
			if (testCard.rank.equals(ShitheadRules.INVISIBLE))
				return true ;
			else
				return checkValidMove(testCard, cardToLay) ;
		}
		else 
			return checkValidMove(pile.peek(), cardToLay) ;	
	}	
	private boolean checkValidMove(Card onPile, Card toLay) {
		if (ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(toLay.rank)) 
			return true ;
		else {
			return (onPile.compareTo(toLay) <= 0);
		}
	}	
}
