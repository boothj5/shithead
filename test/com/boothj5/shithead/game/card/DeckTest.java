package com.boothj5.shithead.game.card;

import org.junit.Test ;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Deck;

import static org.junit.Assert.* ;

public class DeckTest {

	@Test
	public void createDeck() {
		Deck deck = new Deck() ;
		
		assertTrue(deck.getCards().size() == 52) ;
		
		for (Card.Suit suit : Card.Suit.values())
			for (Card.Rank rank : Card.Rank.values())
				assertTrue(deck.getCards().contains(new Card(rank, suit))) ;
	}
}
