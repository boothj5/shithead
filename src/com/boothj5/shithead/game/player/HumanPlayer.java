package com.boothj5.shithead.game.player;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Hand;

import java.util.*;

public final class HumanPlayer implements Player {

	public static final String description = "Human player" ;
    private String name ;
	private int handSize ;
	
	private Hand faceDown = new Hand() ;
	private Hand faceUp = new Hand() ;
	private Hand hand = new Hand() ;	
	
	public HumanPlayer(String name, int handSize) {
		this.name = name ;
		this.handSize =  handSize ;
	}
	
    @Override
    public boolean isComputer() {
    	return false ;
    }
	
	@Override
	public boolean askSwapMore() {
		throw new IllegalStateException("Human players must be asked questions via the user interface") ;
	}
	
    @Override
	public SwapResponse askSwapChoice() {
		throw new IllegalStateException("Human players must be asked questions via the user interface") ;
	}
	
    @Override
	public List<Integer> askCardChoiceFromHand(PlayerHelper helper) {
		throw new IllegalStateException("Human players must be asked questions via the user interface") ;
	}

    @Override
	public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
		throw new IllegalStateException("Human players must be asked questions via the user interface") ;
	}

    @Override
	public String getName() {
		return name ;
	}

    @Override
	public Hand getFaceDown() {
		return faceDown;
	}

    @Override
	public Hand getFaceUp() {
		return faceUp;
	}

    @Override
	public Hand getHand() {
		return hand;
	}	
	
    @Override
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
	
    @Override
	public void recieve(List<Card> cards) {
		hand.addAll(cards) ;
		hand.sort() ;
	}

    @Override
	public void dealToHand(Card card) {
		hand.add(card) ;
		hand.sort() ;
	}

    @Override
	public void dealToFaceUp(Card card) {
		faceUp.add(card) ;
	}

    @Override
	public void dealToFaceDown(Card card) {
		faceDown.add(card) ;
	}
	
    @Override
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
			hand.sort() ;
		}
	}	
}
