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
import com.boothj5.shithead.player.PlayerHelper;
import com.boothj5.shithead.player.computer.DeviousPyro;

import static org.junit.Assert.* ;


public class DeviousPyroTest {

	
	@Test
	public void picksTenWhenOnlyOneCard() {
		int numCardsPerHand = 3 ;
		Player james = new DeviousPyro("James", numCardsPerHand) ;
		List<Player> players = new ArrayList<Player>();
		players.add(james) ;
		
		Card hand1 = new Card(Card.Rank.TEN, Card.Suit.SPADES) ;
		james.dealToHand(hand1) ;

		PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, new Stack<Card>(), null, null) ;

		List<Integer> choices = james.askCardChoiceFromHand(helper) ;
		Card chosenCard = james.getHand().get(choices.get(0)) ;
		
		assertThat(choices.size(), is(1)) ;
		assertThat(chosenCard.equals(new Card(Card.Rank.TEN, Card.Suit.SPADES)), is(true)) ;
	}	
	
	@Test
	public void picksTenWhenMoreThanThresholdOnPile() {
		int numCardsPerHand = 3 ;
		Player james = new DeviousPyro("James", numCardsPerHand) ;
		List<Player> players = new ArrayList<Player>();
		players.add(james) ;
		
		Card hand1 = new Card(Card.Rank.ACE, Card.Suit.SPADES) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		james.dealToHand(hand1) ;
		james.dealToHand(hand2) ;

		Stack<Card> pile = new Stack<Card>() ;
		for (int i = 0 ; i < ((DeviousPyro)james).getThreshold() ; i++)
			pile.push(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS)) ;

		PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, pile, null, null) ;

		List<Integer> choices = james.askCardChoiceFromHand(helper) ;
		Card chosenCard = james.getHand().get(choices.get(0)) ;
		
		assertThat(choices.size(), is(1)) ;
		assertThat(chosenCard.equals(new Card(Card.Rank.TEN, Card.Suit.DIAMONDS)), is(true)) ;
	}	
	

	@Test
	public void picksOtherWhenLessThanFiveOnPile() {
		int numCardsPerHand = 3 ;
		Player james = new DeviousPyro("James", numCardsPerHand) ;
		List<Player> players = new ArrayList<Player>();
		players.add(james) ;
		
		Card hand1 = new Card(Card.Rank.JACK, Card.Suit.SPADES) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		james.dealToHand(hand1) ;
		james.dealToHand(hand2) ;

		Stack<Card> pile = new Stack<Card>() ;
		for (int i = 0 ; i < (((DeviousPyro)james).getThreshold()-1) ; i++)
			pile.push(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS)) ;

		PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, pile, null, null) ;

		List<Integer> choices = james.askCardChoiceFromHand(helper) ;
		Card chosenCard = james.getHand().get(choices.get(0)) ;
		
		assertThat(choices.size(), is(1)) ;
		assertThat(chosenCard.equals(new Card(Card.Rank.JACK, Card.Suit.SPADES)), is(true)) ;
	}	
	
	@Test
	public void picksOnlyOneTenWhenTwo() {
		int numCardsPerHand = 3 ;
		Player james = new DeviousPyro("James", numCardsPerHand) ;
		List<Player> players = new ArrayList<Player>();
		players.add(james) ;
		
		Card hand1 = new Card(Card.Rank.TEN, Card.Suit.SPADES) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		james.dealToHand(hand1) ;
		james.dealToHand(hand2) ;

		PlayerHelper helper = new PlayerHelper(0, 1, 1, numCardsPerHand, 0, new Stack<Card>(), null, null) ;

		List<Integer> choices = james.askCardChoiceFromHand(helper) ;
		
		assertThat(choices.size(), is(1)) ;
	}
}
