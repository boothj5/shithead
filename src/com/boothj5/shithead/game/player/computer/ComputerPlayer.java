package com.boothj5.shithead.game.player.computer;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadRules;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.ShitheadCardComparator;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.PlayerSummary;
import com.boothj5.shithead.game.player.SwapResponse;

import java.util.*;

public abstract class ComputerPlayer implements Player {

	private String name ;
	private int handSize ;
	
	private List<Card> faceDown = new ArrayList<Card>() ;
	private List<Card> faceUp = new ArrayList<Card>() ;
	private List<Card> hand = new ArrayList<Card>() ;	

	public ComputerPlayer(String name, int handSize) {
		this.name = name ;
		this.handSize = handSize ;
	}

    @Override
    public boolean isComputer() {
    	return true ;
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
	
	protected boolean checkValidMove(Card cardToLay, PlayerHelper helper) {
		Stack<Card> pile = helper.getPile() ;
		if (pile.isEmpty()) {
			return true ;
		}
		else if (ShitheadRules.INVISIBLE.equals(pile.peek().getRank())) {
			//look for first non invisible and check that
			Card testCard = pile.peek() ;
			for (int i = pile.size() -1 ; (i >=0 && (testCard.getRank().equals(ShitheadRules.INVISIBLE))) ; i-- ) {
				testCard = pile.get(i) ;
			}
			if (testCard.getRank().equals(ShitheadRules.INVISIBLE)) {
				return true ;
			}
			else {
				return checkValidMove(testCard, cardToLay) ;
			}
		}
		else {
			return checkValidMove(pile.peek(), cardToLay) ;	
		}	
	}
	
	private boolean checkValidMove(Card onPile, Card toLay) {
		if (ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(toLay.getRank())) {
			return true ;
		}
		else {
			return (onPile.compareTo(toLay) <= 0);
		}
	}	
	
	protected List<Integer> pickHighCards(PlayerHelper helper, List<Card> myHand) {
		List<Integer> returnChoice = new ArrayList<Integer>() ;
		List<Card> handMinusSpecial = new ArrayList<Card>() ;
		
		// get normal cards
		for (Card testCard : myHand) {
			if (!ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(testCard.getRank())) {
				handMinusSpecial.add(testCard) ;
			}
		}
		
		if (handMinusSpecial.size() > 0) {
			// get max normal card
			Card maxNormalCard = 
				myHand.get(myHand.indexOf(Collections.max(handMinusSpecial, new ShitheadCardComparator()))) ;
			
			// if valid search for more and add them to choice
			if (checkValidMove(maxNormalCard, helper)) {
				returnChoice.add(myHand.indexOf(maxNormalCard)) ;
				for (Card toCompare : myHand) {
					if ((myHand.get(returnChoice.get(0)).getRank().compareTo(toCompare.getRank()) == 0) && 
										(!myHand.get(returnChoice.get(0)).equals(toCompare))) 
						returnChoice.add(myHand.indexOf(toCompare)) ;	
				}
				return returnChoice ;
			}
			else {
				returnChoice.add(myHand.indexOf(Collections.max(myHand, new ShitheadCardComparator()))) ;
				return returnChoice ;

			}
		}
		else {
			returnChoice.add(myHand.indexOf(Collections.max(myHand, new ShitheadCardComparator()))) ;
			return returnChoice ;
		}
	}
	
	protected List<Integer> pickLowCards(PlayerHelper helper, List<Card> myHand) {
		List<Integer> chosenCards = null; 
		
		for (Card tryCard : myHand) {
			if (checkValidMove(tryCard, helper)) {
				int chosen = myHand.indexOf(tryCard) ;
				chosenCards = new ArrayList<Integer>() ;
				chosenCards.add(chosen) ;
				break ;
			}
		}

		Card.Rank chosenRank = myHand.get(chosenCards.get(0)).getRank() ;
		
		if (ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(chosenRank)) 
			return chosenCards ;
		else {
			// iterate over the players cards for any of the same rank and add them 
			for (Card toCompare : myHand)
				if ((myHand.get(chosenCards.get(0)).compareTo(toCompare) == 0) && 
									(!myHand.get(chosenCards.get(0)).equals(toCompare))) 
					chosenCards.add(myHand.indexOf(toCompare)) ;	
			return chosenCards;
		}
	}
	
	protected Card getBurnCardInHand(List<Card> myHand) {
		for (Card tryCard : myHand) {
			if (tryCard.getRank().equals(ShitheadRules.BURN)) 
				return tryCard ;
		}
		return null ;
	}
	
	protected PlayerSummary getNextPlayer(PlayerHelper helper) {
		int nextPlayer = helper.getCurrentPlayer();
		List<PlayerSummary> players = helper.getPlayers() ;
		
		nextPlayer ++ ;
		if (nextPlayer >= players.size())
			nextPlayer = 0 ;
		while (!players.get(nextPlayer).hasCards()) {
			nextPlayer++ ;
			if (nextPlayer >= players.size())
				nextPlayer = 0 ;
		}
		return helper.getPlayers().get(nextPlayer) ;
	}
	
	@Override
	public abstract boolean askSwapMore() ;
	
    @Override
	public abstract SwapResponse askSwapChoice() throws ShitheadException ;
	
    @Override
	public abstract List<Integer> askCardChoiceFromHand(PlayerHelper helper) ;
	
    @Override
	public abstract List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) ;

}
