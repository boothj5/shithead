package com.boothj5.shithead.player.computer;

import java.util.List;
import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.player.PlayerHelper;
import com.boothj5.shithead.player.SwapResponse;

public class Aggressive extends ComputerPlayer {
    
    public static final String description = "Always plays highest cards" ;

    public Aggressive(String name, int handSize) {
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
		return pickHighCards(helper, myHand);
	}

    @Override
	public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
		List<Card> myHand = getFaceUp() ;
		return pickHighCards(helper, myHand);
	}	
	
}
