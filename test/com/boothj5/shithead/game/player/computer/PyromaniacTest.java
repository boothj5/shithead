package com.boothj5.shithead.game.player.computer;

import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test ;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.computer.Pyromaniac;

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

		PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, null, null, null) ;

		List<Integer> choices = james.askCardChoiceFromHand(helper) ;
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

		PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, null, null, null) ;

		List<Integer> choices = james.askCardChoiceFromHand(helper) ;
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

		PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, null, null, null) ;

		List<Integer> choices = james.askCardChoiceFromHand(helper) ;
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

		PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, null, null, null) ;

		List<Integer> choices = james.askCardChoiceFromHand(helper) ;
		
		assertThat(choices.size(), is(1)) ;
	}
}
