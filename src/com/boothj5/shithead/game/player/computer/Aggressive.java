package com.boothj5.shithead.game.player.computer;

import java.util.List;

import com.boothj5.shithead.game.card.Hand;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;

public class Aggressive extends ComputerPlayer {
    
    public static final String description = "Always plays highest cards" ;

    public Aggressive(String name, int handSize) {
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
        Hand myHand = getHand() ;
		return pickHighCards(helper, myHand);
	}

    @Override
	public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
		Hand myFaceUp = getFaceUp() ;
		return pickHighCards(helper, myFaceUp);
	}	
	
}
