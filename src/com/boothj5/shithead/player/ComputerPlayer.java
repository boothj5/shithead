package com.boothj5.shithead.player;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.card.ShitheadCardComparator;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.game.ShitheadRules;

import java.util.*;

public abstract class ComputerPlayer extends Player {

	public ComputerPlayer(String name, int handSize) {
		super(name, handSize) ;
	}

	protected boolean checkValidMove(Card cardToLay, ShitheadGameDetails details) {
		Stack<Card> pile = details.getPile() ;
		if (pile.empty()) 
			return true ;
		else if (ShitheadRules.INVISIBLE.equals(pile.peek().rank)) {
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
	
	protected List<Integer> pickHighCards(ShitheadGameDetails details, List<Card> myHand) {
		
		List<Integer> returnChoice = new ArrayList<Integer>() ;
		returnChoice.add(myHand.indexOf(Collections.max(myHand, new ShitheadCardComparator()))) ;

		// iterate over the players cards for any of the same rank and add them 
		for (Card toCompare : myHand)
			if ((myHand.get(returnChoice.get(0)).compareTo(toCompare) == 0) && 
								(!myHand.get(returnChoice.get(0)).equals(toCompare))) 
				returnChoice.add(myHand.indexOf(toCompare)) ;	
		
		return returnChoice ;
	}	

	protected List<Integer> pickLowCards(ShitheadGameDetails details, List<Card> myHand) {
		List<Integer> chosenCards = null; 
		
		for (Card tryCard : myHand) {
			if (checkValidMove(tryCard, details)) {
				int chosen = myHand.indexOf(tryCard) ;
				chosenCards = new ArrayList<Integer>() ;
				chosenCards.add(chosen) ;
				break ;
			}
		}
		// iterate over the players cards for any of the same rank and add them 
		for (Card toCompare : myHand)
			if ((myHand.get(chosenCards.get(0)).compareTo(toCompare) == 0) && 
								(!myHand.get(chosenCards.get(0)).equals(toCompare))) 
				chosenCards.add(myHand.indexOf(toCompare)) ;	

		
		return chosenCards;
	}
	
	protected Card getBurnCardInHand(List<Card> myHand) {
		for (Card tryCard : myHand) {
			if (tryCard.rank.equals(ShitheadRules.BURN)) 
				return tryCard ;
		}
		return null ;
	}
	
	
	public abstract Boolean askSwapMore() ;
	
	public abstract SwapResponse askSwapChoice()  ;
	
	public abstract List<Integer> askCardChoiceFromHand(ShitheadGameDetails details) ;
	
	public abstract List<Integer> askCardChoiceFromFaceUp(ShitheadGameDetails details) ;

}
