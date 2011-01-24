package com.boothj5.shithead.player;

import java.util.* ;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.game.ShitheadGameDetails;


public abstract class Player {
	private String name ;
	private int handSize ;
	
	private List<Card> faceDown = new ArrayList<Card>() ;
	private List<Card> faceUp = new ArrayList<Card>() ;
	private List<Card> hand = new ArrayList<Card>() ;
	
	public Player(String name, int handSize) {
		this.name = name ;
		this.handSize = handSize ;
	}
	
	// Abstract methods
	public abstract Boolean askSwapMore() ;
		
	public abstract SwapResponse askSwapChoice() ;
	
	public abstract List<Integer> askCardChoiceFromHand(ShitheadGameDetails details) ;
	
	public abstract List<Integer> askCardChoiceFromFaceUp(ShitheadGameDetails details) ;
	
	// Concrete methods
	public String getName() {
		return name ;
	}

	public List<Card> getFaceDown() {
		return faceDown;
	}

	public List<Card> getFaceUp() {
		return faceUp;
	}

	public List<Card> getHand() {
		return hand;
	}	
	
	public boolean hasCards() {
		if (!faceUp.isEmpty()) 
			return true ;
		else if (!faceDown.isEmpty())
			return true ;
		else if (!hand.isEmpty())
			return true ;
		else 
			return false ;
	}
	
	public void recieve(List<Card> cards) {
		hand.addAll(cards) ;
	}

	public void dealToHand(Card card) {
		this.hand.add(card) ;
	}

	public void dealToFaceUp(Card card) {
		this.faceUp.add(card) ;
	}

	public void dealToFaceDown(Card card) {
		this.faceDown.add(card) ;
	}
	
	public void swapCards(SwapResponse swapResponse) {
		if ((swapResponse.getHandCard() < 0) || !(swapResponse.getHandCard() < handSize) ||
				(swapResponse.getFaceUpCard() < 0) || !(swapResponse.getFaceUpCard() < handSize)) {
			return ;
		}
		else {
			Card savedFromHand = hand.get(swapResponse.getHandCard()) ;
			Card savedFromFaceUp = faceUp.get(swapResponse.getFaceUpCard()) ;
			faceUp.set(swapResponse.getFaceUpCard(), savedFromHand) ;
			hand.set(swapResponse.getHandCard(), savedFromFaceUp) ;		
		}
	}
}
