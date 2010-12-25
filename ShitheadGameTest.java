import java.util.* ;
import org.junit.Test ;
import static org.junit.Assert.* ;

public class ShitheadGameTest {

	List<String> names = new ArrayList<String>() ;
	
	@Test
	public void test2PlayersCreated() {
		
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2, 4, names) ;
		
		assertTrue(game.players.size() == 2) ;
		
		List<String> playerNamesInGame = new ArrayList<String>() ;
		
		Iterator<Player> playerIterator = game.players.iterator() ;
		while (playerIterator.hasNext()) {
				Player player = playerIterator.next() ;
				playerNamesInGame.add(player.name) ;
		}
		
		Iterator<String> testNamesIterator = names.iterator() ;
		while (testNamesIterator.hasNext()) {
			String name = testNamesIterator.next() ;
			assertTrue(playerNamesInGame.contains(name)) ;
		}
	}
	
	@Test
	public void test3PlayersCreated() {
		
		names.add("James") ;
		names.add("Monkey") ;
		names.add("Stevie") ;
		ShitheadGame game = new ShitheadGame(3, 4, names) ;
		
		assertTrue(game.players.size() == 3) ;
		
		List<String> playerNamesInGame = new ArrayList<String>() ;
		
		Iterator<Player> playerIterator = game.players.iterator() ;
		while (playerIterator.hasNext()) {
				Player player = playerIterator.next() ;
				playerNamesInGame.add(player.name) ;
		}
		
		Iterator<String> testNamesIterator = names.iterator() ;
		while (testNamesIterator.hasNext()) {
			String name = testNamesIterator.next() ;
			assertTrue(playerNamesInGame.contains(name)) ;
		}
	}

	
}
