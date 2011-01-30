package com.boothj5.shithead.game;

import com.boothj5.shithead.engine.*;

public class Shithead {
	
	public static void main(String[] args) {
		ShitheadEngine engine = new ComputerBattleConsoleEngine() ;
		
		engine.playShithead(args) ;
	}
}
