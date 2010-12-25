import java.util.* ;
import java.io.* ;

public class Shithead {
	
	public static void main(String[] args) {

		ShitheadGameEngine engine = new ShitheadGameEngine() ;
	
		engine.setupGame() ;
		debug(engine.game) ;

		engine.swapCards() ;
		debug(engine.game) ;

		engine.firstMove() ;
		debug(engine.game) ;
	}
	
	private static void debug(ShitheadGame game) {
		if (game.debug) {
			System.out.println(game.toString()) ;
			Console c = System.console();
			c.readLine("Press enter") ;
		}
	}
}
