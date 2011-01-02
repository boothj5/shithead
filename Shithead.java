import java.io.* ;

public class Shithead {
	
	public static void main(String[] args) {

		ShitheadGameEngine engine = new ShitheadGameEngine() ;
	
		engine.setupGame(false) ;
		debug(engine.game) ;

		engine.swapCards() ;
		debug(engine.game) ;

		engine.firstMove() ;
		debug(engine.game) ;

		boolean keepPlaying = true;
		do 
			keepPlaying = engine.nextMove() ;
		while (keepPlaying) ;
		
		System.out.println(engine.game.getShithead().name + " IS A SHITHEAD!!!!!") ;
		Console c = System.console();
		c.readLine("Press enter to finish") ;
		
	}
	
	private static void debug(ShitheadGame game) {
		if (game.debug) {
			System.out.println(game.toString()) ;
			Console c = System.console();
			c.readLine("Press enter") ;
		}
	}
}
