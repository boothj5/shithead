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
		Player james = new HumanPlayer("James", 3) ;

		assertThat(james.getName(), is(equalTo("James"))) ;
	}
	
	@Test
	public void newPlayerDoesNotHaveCards() {
		Player james = new HumanPlayer("James", 3) ;
		
		assertThat(james.hasCards(), is(false)) ;
	}

	@Test
	public void dealToFaceDownAddsCardsToFaceDown() {
		Player james = new HumanPlayer("James", 3) ;
		
		Card card1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card card2 = new Card(Card.Rank.JACK, Card.Suit.SPADES) ;
		
		james.dealToFaceDown(card1) ;
		james.dealToFaceDown(card2) ;
		
		assertThat(james.getFaceDown(), hasItems(card1, card2)) ;
	}
	
	@Test
	public void dealToFaceUpAddsCardsToFaceUp() {
		Player james = new HumanPlayer("James", 3) ;
		
		Card card1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card card2 = new Card(Card.Rank.JACK, Card.Suit.SPADES) ;
		
		james.dealToFaceUp(card1) ;
		james.dealToFaceUp(card2) ;
		
		assertThat(james.getFaceUp(), hasItems(card1, card2)) ;
	}

	@Test
	public void dealToHandAddsCardsToHand() {
		Player james = new HumanPlayer("James", 3) ;
		
		Card card1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card card2 = new Card(Card.Rank.JACK, Card.Suit.SPADES) ;
		
		james.dealToHand(card1) ;
		james.dealToHand(card2) ;
		
		assertThat(james.getHand(), hasItems(card1, card2)) ;
	}
	
	
	@Test
	public void playerHasCardsWhenOnlyCardsInHand() {
		Player james = new HumanPlayer("James", 3) ;
		james.dealToHand(new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;

		assertThat(james.hasCards(), is(true)) ;
	}

	@Test
	public void playerHasCardsWhenOnlyCardsInFaceUp() {
		Player james = new HumanPlayer("James", 3) ;
		james.dealToFaceUp(new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;

		assertThat(james.hasCards(), is(true)) ;
	}

	@Test
	public void playerHasCardsWhenOnlyCardsInFaceDown() {
		Player james = new HumanPlayer("James", 3) ;
		james.dealToFaceDown(new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;

		assertThat(james.hasCards(), is(true)) ;
	}
	
	@Test
	public void pickupAddsCardsToHandWhenCardsInHand() {
		Player james = new HumanPlayer("James", 3) ;
		james.dealToHand(new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;
		
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
		
		assertThat(james.getHand(), hasItems(card1, card2, card3, card4, card5)) ;
	}

	@Test
	public void pickupAddsCardsToHandWhenEmptyHand() {
		Player james = new HumanPlayer("James", 3) ;
		james.dealToFaceDown(new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;
		james.dealToFaceUp(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS)) ;
		
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
		
		assertThat(james.getHand(), hasItems(card1, card2, card3, card4, card5)) ;
	}

	@Test
	public void playerSwapsCardsBetweenHandAndFaceUp() {
		Player james = new HumanPlayer("James", 3) ;

		Card faceDown1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card faceDown2 = new Card(Card.Rank.ACE, Card.Suit.HEARTS) ;
		Card faceDown3 = new Card(Card.Rank.NINE, Card.Suit.SPADES) ;
		james.dealToFaceDown(faceDown1) ;
		james.dealToFaceDown(faceDown2) ;
		james.dealToFaceDown(faceDown3) ;

		Card faceUp1 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		Card faceUp2 = new Card(Card.Rank.TWO, Card.Suit.CLUBS) ;
		Card faceUp3 = new Card(Card.Rank.JACK, Card.Suit.CLUBS) ;
		james.dealToFaceUp(faceUp1) ;
		james.dealToFaceUp(faceUp2) ;
		james.dealToFaceUp(faceUp3) ;
		
		Card hand1 = new Card(Card.Rank.SIX, Card.Suit.HEARTS) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.SPADES) ;
		Card hand3 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		james.dealToHand(hand1) ;
		james.dealToHand(hand2) ;
		james.dealToHand(hand3) ;
		
		SwapResponse response = new SwapResponse(0,2) ;
		james.swapCards(response) ;

		assertThat("hand cards", james.getHand(), hasItems(faceUp3, hand2, hand3)) ;
		assertThat("hand size", james.getHand().size(), is(3)) ;

		assertThat("faceUp cards", james.getFaceUp(), hasItems(faceUp1, faceUp2, hand1)) ;
		assertThat("faceup size", james.getFaceUp().size(), is(3)) ;

		assertThat("faceDown cards", james.getFaceDown(), hasItems(faceDown1, faceDown2, faceDown3)) ;
		assertThat("faceDown size", james.getFaceDown().size(), is(3)) ;
	}

	@Test
	public void playerSwapsCardsWithNegtiveHandCardDoesNothing() {
		Player james = new HumanPlayer("James", 3) ;

		Card faceDown1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card faceDown2 = new Card(Card.Rank.ACE, Card.Suit.HEARTS) ;
		Card faceDown3 = new Card(Card.Rank.NINE, Card.Suit.SPADES) ;
		james.dealToFaceDown(faceDown1) ;
		james.dealToFaceDown(faceDown2) ;
		james.dealToFaceDown(faceDown3) ;

		Card faceUp1 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		Card faceUp2 = new Card(Card.Rank.TWO, Card.Suit.CLUBS) ;
		Card faceUp3 = new Card(Card.Rank.JACK, Card.Suit.CLUBS) ;
		james.dealToFaceUp(faceUp1) ;
		james.dealToFaceUp(faceUp2) ;
		james.dealToFaceUp(faceUp3) ;
		
		Card hand1 = new Card(Card.Rank.SIX, Card.Suit.HEARTS) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.SPADES) ;
		Card hand3 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		james.dealToHand(hand1) ;
		james.dealToHand(hand2) ;
		james.dealToHand(hand3) ;
		
		SwapResponse response = new SwapResponse(-1,2) ;
		james.swapCards(response) ;
		
		assertThat("hand cards", james.getHand(), hasItems(hand1, hand2, hand3)) ;
		assertThat("hand size", james.getHand().size(), is(3)) ;

		assertThat("faceUp cards", james.getFaceUp(), hasItems(faceUp1, faceUp2, faceUp3)) ;
		assertThat("faceup size", james.getFaceUp().size(), is(3)) ;

		assertThat("faceDown cards", james.getFaceDown(), hasItems(faceDown1, faceDown2, faceDown3)) ;
		assertThat("faceDown size", james.getFaceDown().size(), is(3)) ;
	
	}

	@Test
	public void playerSwapsCardsWithNegtiveFaceUpCardDoesNothing() {
		Player james = new HumanPlayer("James", 3) ;

		Card faceDown1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card faceDown2 = new Card(Card.Rank.ACE, Card.Suit.HEARTS) ;
		Card faceDown3 = new Card(Card.Rank.NINE, Card.Suit.SPADES) ;
		james.dealToFaceDown(faceDown1) ;
		james.dealToFaceDown(faceDown2) ;
		james.dealToFaceDown(faceDown3) ;

		Card faceUp1 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		Card faceUp2 = new Card(Card.Rank.TWO, Card.Suit.CLUBS) ;
		Card faceUp3 = new Card(Card.Rank.JACK, Card.Suit.CLUBS) ;
		james.dealToFaceUp(faceUp1) ;
		james.dealToFaceUp(faceUp2) ;
		james.dealToFaceUp(faceUp3) ;
		
		Card hand1 = new Card(Card.Rank.SIX, Card.Suit.HEARTS) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.SPADES) ;
		Card hand3 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		james.dealToHand(hand1) ;
		james.dealToHand(hand2) ;
		james.dealToHand(hand3) ;
		
		SwapResponse response = new SwapResponse(1, -12) ;
		james.swapCards(response) ;
		
		assertThat("hand cards", james.getHand(), hasItems(hand1, hand2, hand3)) ;
		assertThat("hand size", james.getHand().size(), is(3)) ;

		assertThat("faceUp cards", james.getFaceUp(), hasItems(faceUp1, faceUp2, faceUp3)) ;
		assertThat("faceup size", james.getFaceUp().size(), is(3)) ;

		assertThat("faceDown cards", james.getFaceDown(), hasItems(faceDown1, faceDown2, faceDown3)) ;
		assertThat("faceDown size", james.getFaceDown().size(), is(3)) ;
	
	}

	@Test
	public void playerSwapsCardsWithInvalidCardsDoesNothing() {
		Player james = new HumanPlayer("James", 3) ;

		Card faceDown1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		Card faceDown2 = new Card(Card.Rank.ACE, Card.Suit.HEARTS) ;
		Card faceDown3 = new Card(Card.Rank.NINE, Card.Suit.SPADES) ;
		james.dealToFaceDown(faceDown1) ;
		james.dealToFaceDown(faceDown2) ;
		james.dealToFaceDown(faceDown3) ;

		Card faceUp1 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		Card faceUp2 = new Card(Card.Rank.TWO, Card.Suit.CLUBS) ;
		Card faceUp3 = new Card(Card.Rank.JACK, Card.Suit.CLUBS) ;
		james.dealToFaceUp(faceUp1) ;
		james.dealToFaceUp(faceUp2) ;
		james.dealToFaceUp(faceUp3) ;
		
		Card hand1 = new Card(Card.Rank.SIX, Card.Suit.HEARTS) ;
		Card hand2 = new Card(Card.Rank.TEN, Card.Suit.SPADES) ;
		Card hand3 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		james.dealToHand(hand1) ;
		james.dealToHand(hand2) ;
		james.dealToHand(hand3) ;
		
		SwapResponse response = new SwapResponse(1, 3) ;
		james.swapCards(response) ;
		
		assertThat("hand cards", james.getHand(), hasItems(hand1, hand2, hand3)) ;
		assertThat("hand size", james.getHand().size(), is(3)) ;

		assertThat("faceUp cards", james.getFaceUp(), hasItems(faceUp1, faceUp2, faceUp3)) ;
		assertThat("faceup size", james.getFaceUp().size(), is(3)) ;

		assertThat("faceDown cards", james.getFaceDown(), hasItems(faceDown1, faceDown2, faceDown3)) ;
		assertThat("faceDown size", james.getFaceDown().size(), is(3)) ;
	}
}
