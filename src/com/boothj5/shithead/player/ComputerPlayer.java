package com.boothj5.shithead.player;

import com.boothj5.shithead.game.ShitheadGameDetails;

import java.util.*;

public abstract class ComputerPlayer extends Player {

	public ComputerPlayer(String name, int handSize) {
		super(name, handSize) ;
	}
	
	public abstract Boolean askSwapMore() ;
	
	public abstract SwapResponse askSwapChoice()  ;
	
	public abstract List<Integer> askCardChoiceFromHand(ShitheadGameDetails details, Player.Hand hand) ; 
	
}
