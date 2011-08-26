package com.boothj5.shithead.game.player;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.ShitheadCardComparator;

import java.util.*;

public class HumanPlayer implements Player {

	public static final String description = "Human player" ;
    private String name ;
	private int handSize ;
	
	private List<Card> faceDown = new ArrayList<Card>() ;
	private List<Card> faceUp = new ArrayList<Card>() ;
	private List<Card> hand = new ArrayList<Card>() ;	
	
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
	public List<Card> getFaceDown() {
		return faceDown;
	}

    @Override
	public List<Card> getFaceUp() {
		return faceUp;
	}

    @Override
	public List<Card> getHand() {
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
		Collections.sort(hand, new ShitheadCardComparator()) ;
	}

    @Override
	public void dealToHand(Card card) {
		this.hand.add(card) ;
		Collections.sort(hand, new ShitheadCardComparator()) ;
	}

    @Override
	public void dealToFaceUp(Card card) {
		this.faceUp.add(card) ;
	}

    @Override
	public void dealToFaceDown(Card card) {
		this.faceDown.add(card) ;
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
			Collections.sort(hand, new ShitheadCardComparator()) ;
		}
	}	
	
}
