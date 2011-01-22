package com.boothj5.shithead.player;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.* ;
import org.junit.Test ;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.player.HumanPlayer;
import com.boothj5.shithead.player.Player;
import com.boothj5.shithead.player.SwapResponse;

import static org.junit.Assert.* ;
import java.util.* ;

public class HumanPlayerTest {
	
	@Test
	public void newPLayerHasName() {
		HumanPlayer james = new HumanPlayer("James", 3) ;

		assertThat(james.getName(), is(equalTo("James"))) ;
	}
	
	@Test
	public void newPlayerDoesNotHaveCards() {
		HumanPlayer james = new HumanPlayer("James", 3) ;
		
		assertThat(james.hasCards(), is(false)) ;
	}

	@Test
	public void dealToFaceDownAddsCardsToFaceDown() {
		HumanPlayer james = new HumanPlayer("James", 3) ;
		
		Card card1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card card2 = new Card(Card.Rank.JACK, Card.Suit.SPADES) ;
		
		james.deal(Player.Hand.FACEDOWN, card1) ;
		james.deal(Player.Hand.FACEDOWN, card2) ;
		
		assertThat(james.getHand(Player.Hand.FACEDOWN), hasItems(card1, card2)) ;
	}
	
	@Test
	public void dealToFaceUpAddsCardsToFaceUp() {
		HumanPlayer james = new HumanPlayer("James", 3) ;
		
		Card card1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card card2 = new Card(Card.Rank.JACK, Card.Suit.SPADES) ;
		
		james.deal(Player.Hand.FACEUP, card1) ;
		james.deal(Player.Hand.FACEUP, card2) ;
		
		assertThat(james.getHand(Player.Hand.FACEUP), hasItems(card1, card2)) ;
	}

	@Test
	public void dealToHandAddsCardsToHand() {
		HumanPlayer james = new HumanPlayer("James", 3) ;
		
		Card card1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card card2 = new Card(Card.Rank.JACK, Card.Suit.SPADES) ;
		
		james.deal(Player.Hand.HAND, card1) ;
		james.deal(Player.Hand.HAND, card2) ;
		
		assertThat(james.getHand(Player.Hand.HAND), hasItems(card1, card2)) ;
	}
	
	
	@Test
	public void playerHasCardsWhenOnlyCardsInHand() {
		HumanPlayer james = new HumanPlayer("James", 3) ;
		james.deal(Player.Hand.HAND, new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;

		assertThat(james.hasCards(), is(true)) ;
	}

	@Test
	public void playerHasCardsWhenOnlyCardsInFaceUp() {
		HumanPlayer james = new HumanPlayer("James", 3) ;
		james.deal(Player.Hand.FACEUP, new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;

		assertThat(james.hasCards(), is(true)) ;
	}

	@Test
	public void playerHasCardsWhenOnlyCardsInFaceDown() {
		HumanPlayer james = new HumanPlayer("James", 3) ;
		james.deal(Player.Hand.FACEDOWN, new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;

		assertThat(james.hasCards(), is(true)) ;
	}
	
	@Test
	public void pickupAddsCardsToHandWhenCardsInHand() {
		HumanPlayer james = new HumanPlayer("James", 3) ;
		james.deal(Player.Hand.HAND, new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;
		
		List<Card> cardsToPickup = new ArrayList<Card>() ;

		Card card1 = new Card(Card.Rank.FOUR, Card.Suit.HEARTS) ;
		Card card2 = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		Card card3 = new Card(Card.Rank.SIX, Card.Suit.CLUBS) ;
		Card card4 = new Card(Card.Rank.THREE, Card.Suit.SPADES) ;
		Card card5 = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		
		cardsToPickup.add(card1) ;
		cardsToPickup.add(card2) ;
		cardsToPickup.add(card3) ;
		cardsToPickup.add(card4) ;
		cardsToPickup.add(card5) ;
		
		james.recieve(cardsToPickup) ;
		
		assertThat(james.getHand(Player.Hand.HAND), hasItems(card1, card2, card3, card4, card5)) ;
	}

	@Test
	public void pickupAddsCardsToHandWhenEmptyHand() {
		HumanPlayer james = new HumanPlayer("James", 3) ;
		james.deal(Player.Hand.FACEDOWN, new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;
		james.deal(Player.Hand.FACEUP, new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS)) ;
		
		List<Card> cardsToPickup = new ArrayList<Card>() ;

		Card card1 = new Card(Card.Rank.FOUR, Card.Suit.HEARTS) ;
		Card card2 = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		Card card3 = new Card(Card.Rank.SIX, Card.Suit.CLUBS) ;
		Card card4 = new Card(Card.Rank.THREE, Card.Suit.SPADES) ;
		Card card5 = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		
		cardsToPickup.add(card1) ;
		cardsToPickup.add(card2) ;
		cardsToPickup.add(card3) ;
		cardsToPickup.add(card4) ;
		cardsToPickup.add(card5) ;
		
		james.recieve(cardsToPickup) ;
		
		assertThat(james.getHand(Player.Hand.HAND), hasItems(card1, card2, card3, card4, card5)) ;
	}

	@Test
	public void playerSwapsCardsBetweenHandAndFaceUp() {
		HumanPlayer james = new HumanPlayer("James", 3) ;

		Card faceDown1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card faceDown2 = new Card(Card.Rank.ACE, Card.Suit.HEARTS) ;
		Card faceDown3 = new Card(Card.Rank.NINE, Card.Suit.SPADES) ;
		james.deal(Player.Hand.FACEDOWN, faceDown1) ;
		james.deal(Player.Hand.FACEDOWN, faceDown2) ;
		james.deal(Player.Hand.FACEDOWN, faceDown3) ;

		Card faceUp1 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		Card faceUp2 = new Card(Card.Rank.TWO, Card.Suit.CLUBS) ;
		Card faceUp3 = new Card(Card.Rank.JACK, Card.Suit.CLUBS) ;
		james.deal(Player.Hand.FACEUP, faceUp1) ;
		james.deal(Player.Hand.FACEUP, faceUp2) ;
		james.deal(Player.Hand.FACEUP, faceUp3) ;
		
		Card hand1 = new Card(Card.Rank.SIX, Card.Suit.HEARTS) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.SPADES) ;
		Card hand3 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		james.deal(Player.Hand.HAND, hand1) ;
		james.deal(Player.Hand.HAND, hand2) ;
		james.deal(Player.Hand.HAND, hand3) ;
		
		SwapResponse response = new SwapResponse(0,2) ;
		james.swapCards(response) ;

		assertThat("hand cards", james.getHand(Player.Hand.HAND), hasItems(faceUp3, hand2, hand3)) ;
		assertThat("hand size", james.getHand(Player.Hand.HAND).size(), is(3)) ;

		assertThat("faceUp cards", james.getHand(Player.Hand.FACEUP), hasItems(faceUp1, faceUp2, hand1)) ;
		assertThat("faceup size", james.getHand(Player.Hand.FACEUP).size(), is(3)) ;

		assertThat("faceDown cards", james.getHand(Player.Hand.FACEDOWN), hasItems(faceDown1, faceDown2, faceDown3)) ;
		assertThat("faceDown size", james.getHand(Player.Hand.FACEDOWN).size(), is(3)) ;
	}

	@Test
	public void playerSwapsCardsWithNegtiveHandCardDoesNothing() {
		HumanPlayer james = new HumanPlayer("James", 3) ;

		Card faceDown1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card faceDown2 = new Card(Card.Rank.ACE, Card.Suit.HEARTS) ;
		Card faceDown3 = new Card(Card.Rank.NINE, Card.Suit.SPADES) ;
		james.deal(Player.Hand.FACEDOWN, faceDown1) ;
		james.deal(Player.Hand.FACEDOWN, faceDown2) ;
		james.deal(Player.Hand.FACEDOWN, faceDown3) ;

		Card faceUp1 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		Card faceUp2 = new Card(Card.Rank.TWO, Card.Suit.CLUBS) ;
		Card faceUp3 = new Card(Card.Rank.JACK, Card.Suit.CLUBS) ;
		james.deal(Player.Hand.FACEUP, faceUp1) ;
		james.deal(Player.Hand.FACEUP, faceUp2) ;
		james.deal(Player.Hand.FACEUP, faceUp3) ;
		
		Card hand1 = new Card(Card.Rank.SIX, Card.Suit.HEARTS) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.SPADES) ;
		Card hand3 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		james.deal(Player.Hand.HAND, hand1) ;
		james.deal(Player.Hand.HAND, hand2) ;
		james.deal(Player.Hand.HAND, hand3) ;
		
		SwapResponse response = new SwapResponse(-1,2) ;
		james.swapCards(response) ;
		
		assertThat("hand cards", james.getHand(Player.Hand.HAND), hasItems(hand1, hand2, hand3)) ;
		assertThat("hand size", james.getHand(Player.Hand.HAND).size(), is(3)) ;

		assertThat("faceUp cards", james.getHand(Player.Hand.FACEUP), hasItems(faceUp1, faceUp2, faceUp3)) ;
		assertThat("faceup size", james.getHand(Player.Hand.FACEUP).size(), is(3)) ;

		assertThat("faceDown cards", james.getHand(Player.Hand.FACEDOWN), hasItems(faceDown1, faceDown2, faceDown3)) ;
		assertThat("faceDown size", james.getHand(Player.Hand.FACEDOWN).size(), is(3)) ;
	
	}

	@Test
	public void playerSwapsCardsWithNegtiveFaceUpCardDoesNothing() {
		HumanPlayer james = new HumanPlayer("James", 3) ;

		Card faceDown1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card faceDown2 = new Card(Card.Rank.ACE, Card.Suit.HEARTS) ;
		Card faceDown3 = new Card(Card.Rank.NINE, Card.Suit.SPADES) ;
		james.deal(Player.Hand.FACEDOWN, faceDown1) ;
		james.deal(Player.Hand.FACEDOWN, faceDown2) ;
		james.deal(Player.Hand.FACEDOWN, faceDown3) ;

		Card faceUp1 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		Card faceUp2 = new Card(Card.Rank.TWO, Card.Suit.CLUBS) ;
		Card faceUp3 = new Card(Card.Rank.JACK, Card.Suit.CLUBS) ;
		james.deal(Player.Hand.FACEUP, faceUp1) ;
		james.deal(Player.Hand.FACEUP, faceUp2) ;
		james.deal(Player.Hand.FACEUP, faceUp3) ;
		
		Card hand1 = new Card(Card.Rank.SIX, Card.Suit.HEARTS) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.SPADES) ;
		Card hand3 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		james.deal(Player.Hand.HAND, hand1) ;
		james.deal(Player.Hand.HAND, hand2) ;
		james.deal(Player.Hand.HAND, hand3) ;
		
		SwapResponse response = new SwapResponse(1, -12) ;
		james.swapCards(response) ;
		
		assertThat("hand cards", james.getHand(Player.Hand.HAND), hasItems(hand1, hand2, hand3)) ;
		assertThat("hand size", james.getHand(Player.Hand.HAND).size(), is(3)) ;

		assertThat("faceUp cards", james.getHand(Player.Hand.FACEUP), hasItems(faceUp1, faceUp2, faceUp3)) ;
		assertThat("faceup size", james.getHand(Player.Hand.FACEUP).size(), is(3)) ;

		assertThat("faceDown cards", james.getHand(Player.Hand.FACEDOWN), hasItems(faceDown1, faceDown2, faceDown3)) ;
		assertThat("faceDown size", james.getHand(Player.Hand.FACEDOWN).size(), is(3)) ;
	
	}

	@Test
	public void playerSwapsCardsWithInvalidCardsDoesNothing() {
		HumanPlayer james = new HumanPlayer("James", 3) ;

		Card faceDown1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card faceDown2 = new Card(Card.Rank.ACE, Card.Suit.HEARTS) ;
		Card faceDown3 = new Card(Card.Rank.NINE, Card.Suit.SPADES) ;
		james.deal(Player.Hand.FACEDOWN, faceDown1) ;
		james.deal(Player.Hand.FACEDOWN, faceDown2) ;
		james.deal(Player.Hand.FACEDOWN, faceDown3) ;

		Card faceUp1 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		Card faceUp2 = new Card(Card.Rank.TWO, Card.Suit.CLUBS) ;
		Card faceUp3 = new Card(Card.Rank.JACK, Card.Suit.CLUBS) ;
		james.deal(Player.Hand.FACEUP, faceUp1) ;
		james.deal(Player.Hand.FACEUP, faceUp2) ;
		james.deal(Player.Hand.FACEUP, faceUp3) ;
		
		Card hand1 = new Card(Card.Rank.SIX, Card.Suit.HEARTS) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.SPADES) ;
		Card hand3 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		james.deal(Player.Hand.HAND, hand1) ;
		james.deal(Player.Hand.HAND, hand2) ;
		james.deal(Player.Hand.HAND, hand3) ;
		
		SwapResponse response = new SwapResponse(1, 3) ;
		james.swapCards(response) ;
		
		assertThat("hand cards", james.getHand(Player.Hand.HAND), hasItems(hand1, hand2, hand3)) ;
		assertThat("hand size", james.getHand(Player.Hand.HAND).size(), is(3)) ;

		assertThat("faceUp cards", james.getHand(Player.Hand.FACEUP), hasItems(faceUp1, faceUp2, faceUp3)) ;
		assertThat("faceup size", james.getHand(Player.Hand.FACEUP).size(), is(3)) ;

		assertThat("faceDown cards", james.getHand(Player.Hand.FACEDOWN), hasItems(faceDown1, faceDown2, faceDown3)) ;
		assertThat("faceDown size", james.getHand(Player.Hand.FACEDOWN).size(), is(3)) ;

	
	}
}
