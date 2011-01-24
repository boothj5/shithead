package com.boothj5.shithead.player;

import com.boothj5.shithead.game.ShitheadGameDetails;

import java.util.*;

public class HumanPlayer extends Player {

	public HumanPlayer(String name, int handSize) {
		super(name, handSize) ;
	}
	
	public Boolean askSwapMore() {
		return null ;
	}
	
	public SwapResponse askSwapChoice() {
		return null ;
	}
	
	public List<Integer> askCardChoiceFromHand(ShitheadGameDetails details) {
		return null ;
	}

	public List<Integer> askCardChoiceFromFaceUp(ShitheadGameDetails details) {
		return null ;
	}
	
}
