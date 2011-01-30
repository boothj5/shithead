package com.boothj5.shithead.player.computer;

import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test ;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.card.Deck;
import com.boothj5.shithead.game.LastMove;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.player.Player;
import com.boothj5.shithead.player.computer.Aggressive;
import com.boothj5.shithead.player.computer.Pyromaniac;
import com.boothj5.shithead.player.computer.SimplePlayer;

import static org.junit.Assert.* ;


public class PyromaniacTest {

	
	@Test
	public void picksTenWhenOnlyOneCard() {
		int numCardsPerHand = 3 ;
		Player james = new Pyromaniac("James", numCardsPerHand) ;
		List<Player> players = new ArrayList<Player>();
		players.add(james) ;
		
		Card hand1 = new Card(Card.Rank.TEN, Card.Suit.SPADES) ;
		james.dealToHand(hand1) ;

		ShitheadGameDetails details = new ShitheadGameDetails(players, new Deck(), 1, numCardsPerHand, 0, new Stack<Card>(), 
					new ArrayList<Card>(), new LastMove(james, new ArrayList<Card>()) ) ;

		List<Integer> choices = james.askCardChoiceFromHand(details) ;
		Card chosenCard = james.getHand().get(choices.get(0)) ;
		
		assertThat(choices.size(), is(1)) ;
		assertThat(chosenCard.equals(new Card(Card.Rank.TEN, Card.Suit.SPADES)), is(true)) ;
	}	
	
	@Test
	public void picksTenWhenTwoCardsOneTenOtherNormal() {
		int numCardsPerHand = 3 ;
		Player james = new Pyromaniac("James", numCardsPerHand) ;
		List<Player> players = new ArrayList<Player>();
		players.add(james) ;
		
		Card hand1 = new Card(Card.Rank.FOUR, Card.Suit.SPADES) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		james.dealToHand(hand1) ;
		james.dealToHand(hand2) ;

		ShitheadGameDetails details = new ShitheadGameDetails(players, new Deck(), 1, numCardsPerHand, 0, new Stack<Card>(), 
					new ArrayList<Card>(), new LastMove(james, new ArrayList<Card>()) ) ;

		List<Integer> choices = james.askCardChoiceFromHand(details) ;
		Card chosenCard = james.getHand().get(choices.get(0)) ;
		
		assertThat(choices.size(), is(1)) ;
		assertThat(chosenCard.equals(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS)), is(true)) ;
	}	
	
	@Test
	public void picksTenWhenTwoCardsOneTenOtherSpecial() {
		int numCardsPerHand = 3 ;
		Player james = new Pyromaniac("James", numCardsPerHand) ;
		List<Player> players = new ArrayList<Player>();
		players.add(james) ;
		
		Card hand1 = new Card(Card.Rank.SEVEN, Card.Suit.SPADES) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		james.dealToHand(hand1) ;
		james.dealToHand(hand2) ;

		ShitheadGameDetails details = new ShitheadGameDetails(players, new Deck(), 1, numCardsPerHand, 0, new Stack<Card>(), 
					new ArrayList<Card>(), new LastMove(james, new ArrayList<Card>()) ) ;

		List<Integer> choices = james.askCardChoiceFromHand(details) ;
		Card chosenCard = james.getHand().get(choices.get(0)) ;
		
		assertThat(choices.size(), is(1)) ;
		assertThat(chosenCard.equals(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS)), is(true)) ;
	}
	
	@Test
	public void picksOnlyOneTenWhenTwo() {
		int numCardsPerHand = 3 ;
		Player james = new Pyromaniac("James", numCardsPerHand) ;
		List<Player> players = new ArrayList<Player>();
		players.add(james) ;
		
		Card hand1 = new Card(Card.Rank.TEN, Card.Suit.SPADES) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		james.dealToHand(hand1) ;
		james.dealToHand(hand2) ;

		ShitheadGameDetails details = new ShitheadGameDetails(players, new Deck(), 1, numCardsPerHand, 0, new Stack<Card>(), 
					new ArrayList<Card>(), new LastMove(james, new ArrayList<Card>()) ) ;

		List<Integer> choices = james.askCardChoiceFromHand(details) ;
		
		assertThat(choices.size(), is(1)) ;
	}
}
