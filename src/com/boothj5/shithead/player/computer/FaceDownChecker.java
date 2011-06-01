package com.boothj5.shithead.player.computer;

import com.boothj5.shithead.player.PlayerHelper;
import com.boothj5.shithead.player.PlayerSummary;
import com.boothj5.shithead.player.SwapResponse;
import com.boothj5.shithead.card.*;

import java.util.*;


public class FaceDownChecker extends ComputerPlayer {

    public static final String description = "Lays high if next player on facedown" ;

	public FaceDownChecker(String name, int handSize) {
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
		List<Card> myHand = getHand() ;
		PlayerSummary nextPlayer = getNextPlayer(helper) ;
		
		if ((nextPlayer.getHandSize() == 0) && (nextPlayer.getFaceUp().size() == 0) ) 
			return pickHighCards(helper, myHand);
		else 
			return pickLowCards(helper, myHand);
	}

    @Override
	public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
		List<Card> myHand = getFaceUp() ;
		PlayerSummary nextPlayer = getNextPlayer(helper) ;
		
		if ((nextPlayer.getHandSize() == 0) && (nextPlayer.getFaceUp().size() == 0) ) 
			return pickHighCards(helper, myHand);
		else 
			return pickLowCards(helper, myHand);
	}
}
