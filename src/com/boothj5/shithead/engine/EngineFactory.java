package com.boothj5.shithead.engine;

import com.boothj5.shithead.ui.cli.ShitheadCli;

public class EngineFactory {
	public static ShitheadEngine createEngine(String gameType, ShitheadCli cli) throws Exception {
			if (gameType.equals("i"))
				return new CliEngine() ;
			else if (gameType.equals("b")) 
				return new BattleEngine(cli) ;
            else if (gameType.equals("g")) 
                return new GuiEngine() ;
			else 
				throw new Exception("Cannot find engine type to create") ;
	}
}
