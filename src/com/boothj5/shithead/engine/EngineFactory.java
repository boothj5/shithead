package com.boothj5.shithead.engine;

public class EngineFactory {
	public static ShitheadEngine createEngine(String gameType) throws Exception {
			if (gameType.equals("i"))
				return new InteractiveConsoleEngine() ;
			else if (gameType.equals("b")) 
				return new ComputerBattleConsoleEngine() ;
			else 
				throw new Exception("Cannot find engine type to create") ;
	}
}
