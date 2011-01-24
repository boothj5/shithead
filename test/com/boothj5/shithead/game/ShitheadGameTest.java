package com.boothj5.shithead.game;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.* ;
import org.junit.Test ;

import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.player.Player;

import static org.junit.Assert.* ;
import java.util.* ;

import com.boothj5.shithead.card.*;

public class ShitheadGameTest {

	@Test
	public void newGameContainsSpecifiedPlayers() throws Exception {
		int numPlayers = 3 ;
		int numCardsPerHand = 4 ;
		
		List<String> playerNames = new ArrayList<String>() ;
		playerNames.add("James") ;
		playerNames.add("Bob") ;
		playerNames.add("Monkey") ;
		
		List<String> playerTypes = new ArrayList<String>() ;
		playerTypes.add("h") ;
		playerTypes.add("h") ;
		playerTypes.add("h") ;
		
		ShitheadGame game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCardsPerHand) ;
		ShitheadGameDetails details = game.getGameDetails() ;

		assertThat("Correct number of players", details.getPlayers().size(), is(numPlayers)) ;
		assertThat("Correct cards per hand", details.getNumCardsPerHand(), is(numCardsPerHand)) ;
		
	}
	
	@Test
	public void dealResultsIn52CardsInGame() throws Exception {
		int numPlayers = 3 ;
		int numCardsPerHand = 4 ;
		
		List<String> playerNames = new ArrayList<String>() ;
		playerNames.add("James") ;
		playerNames.add("Bob") ;
		playerNames.add("Monkey") ;

		List<String> playerTypes = new ArrayList<String>() ;
		playerTypes.add("h") ;
		playerTypes.add("h") ;
		playerTypes.add("h") ;
		
		ShitheadGame game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCardsPerHand) ;
		game.deal();
		ShitheadGameDetails details = game.getGameDetails() ;
		
		int totalCardsInGame = details.getDeck().getSize() + 
								details.getPile().size() + 
								details.getBurnt().size() ;
		
		int playerCards = 0 ;
		for (Player player : details.getPlayers()) {
			playerCards += player.getHand().size() ;
			playerCards += player.getFaceUp().size() ;
			playerCards += player.getFaceDown().size() ;
		}
		
		
		totalCardsInGame += playerCards ;
		
		assertThat(totalCardsInGame, is(52)) ;
	}

	@Test
	public void dealLeavesCorrectAmountInDeck() throws Exception{
		int numPlayers = 3 ;
		int numCardsPerHand = 4 ;
		
		List<String> playerNames = new ArrayList<String>() ;
		playerNames.add("James") ;
		playerNames.add("Bob") ;
		playerNames.add("Monkey") ;
		
		List<String> playerTypes = new ArrayList<String>() ;
		playerTypes.add("h") ;
		playerTypes.add("h") ;
		playerTypes.add("h") ;
		
		ShitheadGame game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCardsPerHand) ;
		game.deal();
		ShitheadGameDetails details = game.getGameDetails() ;
		int numberOfCardsThatShouldBeLeft = 52 - ((numPlayers * numCardsPerHand) * 3) ;
		
		assertThat(details.getDeck().getSize(), is(numberOfCardsThatShouldBeLeft)) ;
	}
	
	@Test
	public void firstMovePicksLowestCard() throws Exception {
		int numPlayers = 3 ;
		int numCardsPerHand = 4 ;
		
		List<String> playerNames = new ArrayList<String>() ;
		playerNames.add("James") ;
		playerNames.add("Bob") ;
		playerNames.add("Monkey") ;
		
		List<String> playerTypes = new ArrayList<String>() ;
		playerTypes.add("h") ;
		playerTypes.add("h") ;
		playerTypes.add("h") ;
		
		ShitheadGame game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCardsPerHand) ;
		game.deal();
		game.firstMove() ;
		ShitheadGameDetails details = game.getGameDetails() ;
		
		List<Card> laid = details.getLastmove().getCards() ;
		Card testCard = laid.get(0) ;
		
		boolean foundLower = false ;
		
		for (Player player : details.getPlayers()) {
			List<Card> hand = player.getHand() ;
			
			for (Card card : hand) {
				ShitheadCardComparator comp = new ShitheadCardComparator();
				 foundLower = (comp.compare(testCard, card) > 0) ;
				 if (foundLower) break ;
			}
		}

		assertThat(foundLower, is(false)) ;
	}		

	@Test
	public void firstMovePicksSameRank() throws Exception {
		int numPlayers = 3 ;
		int numCardsPerHand = 4 ;
		
		List<String> playerNames = new ArrayList<String>() ;
		playerNames.add("James") ;
		playerNames.add("Bob") ;
		playerNames.add("Monkey") ;
		
		List<String> playerTypes = new ArrayList<String>() ;
		playerTypes.add("h") ;
		playerTypes.add("h") ;
		playerTypes.add("h") ;
		
		ShitheadGame game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCardsPerHand) ;
		game.deal();
		game.firstMove() ;
		ShitheadGameDetails details = game.getGameDetails() ;
		
		List<Card> laid = details.getLastmove().getCards() ;
		Card firstCard = laid.get(0) ;

		boolean foundDifferent = false ;
		
		for (Card testCard : laid) {
			foundDifferent = (firstCard.compareTo(testCard) != 0) ;
			if (foundDifferent) break ;
		}

		assertThat(foundDifferent, is(false)) ;
	}
}