package com.boothj5.shithead.player;

public class SimpleComputerPlayer extends Player {

	public SimpleComputerPlayer(String name, int handSize) {
		super(name, handSize) ;
	}
	
	public Boolean askSwapMore() {
		return new Boolean(false) ;
	}
	
	public SwapResponse askSwapChoice() {
		return null ;
	}
}
