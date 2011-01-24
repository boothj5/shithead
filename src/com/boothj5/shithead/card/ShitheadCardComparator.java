package com.boothj5.shithead.card;
import java.util.* ;

import com.boothj5.shithead.game.ShitheadGame;

public class ShitheadCardComparator implements Comparator<Card> {

	public int compare(Card o1, Card o2) {
		Card card1 = (Card) o1 ;
		Card card2 = (Card) o2 ;
		
		// both special cards
		if (ShitheadGame.LAY_ON_ANYTHING_RANKS.contains(card1.rank) 
					&& ShitheadGame.LAY_ON_ANYTHING_RANKS.contains(card2.rank)) 
			return 0 ;
		
		// card1 is special
		else if (ShitheadGame.LAY_ON_ANYTHING_RANKS.contains(card1.rank)
					&& !ShitheadGame.LAY_ON_ANYTHING_RANKS.contains(card2.rank)) 
			return 1 ;
		
		// card2 is special
		else if (!ShitheadGame.LAY_ON_ANYTHING_RANKS.contains(card1.rank)
					&& ShitheadGame.LAY_ON_ANYTHING_RANKS.contains(card2.rank)) 
			return -1 ;
		
		// both normal cards
		else 
			return card1.compareTo(card2) ;
	}
}
