package com.boothj5.shithead.player;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.card.ShitheadCardComparator;
import com.boothj5.shithead.game.ShitheadGameDetails;

import java.util.*;

public class HumanPlayer implements Player {

	private String name ;
	private int handSize ;
	
	private List<Card> faceDown = new ArrayList<Card>() ;
	private List<Card> faceUp = new ArrayList<Card>() ;
	private List<Card> hand = new ArrayList<Card>() ;	
	
	public HumanPlayer(String name, int handSize) {
		this.name = name ;
		this.handSize =  handSize ;
	}
	
	public Boolean askSwapMore() {
		return null ;
	}
	
	public SwapResponse askSwapChoice() {
		return null ;
	}
	
	public List<Integer> askCardChoiceFromHand(PlayerHelper helper) {
		return null ;
	}

	public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
		return null ;
	}

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
		Collections.sort(hand, new ShitheadCardComparator()) ;
	}

	public void dealToHand(Card card) {
		this.hand.add(card) ;
		Collections.sort(hand, new ShitheadCardComparator()) ;
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
			Collections.sort(hand, new ShitheadCardComparator()) ;
		}
	}	
	
}
