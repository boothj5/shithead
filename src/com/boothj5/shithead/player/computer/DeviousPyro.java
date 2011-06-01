package com.boothj5.shithead.player.computer;

import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.player.PlayerHelper;
import com.boothj5.shithead.player.SwapResponse;
import com.boothj5.shithead.card.*;
import java.util.*;


public class DeviousPyro extends ComputerPlayer {
    public static final String description = "Plays a 10 if they have one and more than 5 cards on pile, otherwise lowest" ;

	private int threshold = 10 ;
	
	public int getThreshold() {
		return threshold ;
	}
	
	public DeviousPyro(String name, int handSize) {
		super(name, handSize) ;
	}
	
	
    @Override
	public Boolean askSwapMore() {
		return new Boolean(false) ;
	}
	
    @Override
	public SwapResponse askSwapChoice() {
		return null ;
	}
	
    @Override
	public List<Integer> askCardChoiceFromHand(PlayerHelper helper) {
		List<Integer> chosenCards = null ;
		List<Card> myHand = getHand() ;

		if (helper.getPile().size() >= threshold) {
			Card burnCard = getBurnCardInHand(myHand) ;
			if (burnCard != null) {
				chosenCards = new ArrayList<Integer>() ;
				chosenCards.add(myHand.indexOf(burnCard)) ;
				return chosenCards ; 
			}
			else 
				return pickLowCards(helper, myHand);
		}
		else 
			return pickLowCards(helper, myHand);
	}

    @Override
	public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
		List<Integer> chosenCards = null ;
		List<Card> myHand = getFaceUp() ;

		if (helper.getPile().size() >= threshold) {
			Card burnCard = getBurnCardInHand(myHand) ;
			if (burnCard != null) {
				chosenCards = new ArrayList<Integer>() ;
				chosenCards.add(myHand.indexOf(burnCard)) ;
				return chosenCards ;
			}
			else 
				return pickLowCards(helper, myHand);
		}	
		else 
			return pickLowCards(helper, myHand);
	}
	
}
