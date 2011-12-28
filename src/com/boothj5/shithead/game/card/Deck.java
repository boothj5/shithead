package com.boothj5.shithead.game.card;
import java.util.* ;

public final class Deck {

	private final List<Card> cards ;
	
	public Deck(int totalCards) {
        int decksRequired = calcDecksRequired(totalCards) ;
        cards = new ArrayList<Card>() ;

        for (int i = 0 ; i < decksRequired ; i++) {
            for (Card.Suit suit : Card.Suit.values())
                for (Card.Rank rank : Card.Rank.values())
                    cards.add(new Card(rank, suit)) ;
        }
        
        shuffle() ;
    }
	
	public void shuffle() {
		Collections.shuffle(cards) ;
	}
	
	public boolean isEmpty() {
	    return cards.isEmpty() ;
	}
	
	public Card takeCard() {
	    return cards.remove(0) ;
	}
	
	public void removeAll(List<Card> cards) {
		this.cards.removeAll(cards) ;
	}
	
	public int size() {
	    return cards.size() ;
	}
	
	public boolean contains(Card card) {
	    return cards.contains(card) ;
	}

	private static int calcDecksRequired(int totalCardsNeeded) {
        int div = totalCardsNeeded / 52 ;
        int add = ((totalCardsNeeded % 52) > 0) ? 1 : 0 ; ;

        return (div + add) ;
    }
}
