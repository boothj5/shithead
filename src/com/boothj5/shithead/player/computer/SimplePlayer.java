package com.boothj5.shithead.player.computer;

import com.boothj5.shithead.player.PlayerHelper;
import com.boothj5.shithead.player.SwapResponse;
import com.boothj5.shithead.card.*;

import java.util.*;


public class SimplePlayer extends ComputerPlayer {

    public static final String description = "Always lays lowest";

    public SimplePlayer(String name, int handSize) {
		super(name, handSize) ;
	}

    @Override
    public boolean askSwapMore() {
		return false ;
	}
	
    @Override
	public SwapResponse askSwapChoice() {
		return null ;
	}

    @Override
	public List<Integer> askCardChoiceFromHand(PlayerHelper helper) {
		List<Card> myHand = getHand() ;
		return pickLowCards(helper, myHand);
	}	
	
    @Override
	public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
		List<Card> myHand = getFaceUp() ;
		return pickLowCards(helper, myHand);
	}	
}
