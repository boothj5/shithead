package com.boothj5.shithead.game.card;

import java.util.* ;

import static com.boothj5.shithead.game.ShitheadRules.*;

/**
 * Comparator for shithead cards
 *
 * When dealing with special cards:
 * 			<ul>
 * 			<li>If both cards are in ShitheadRules.LAY_ON_ANYTHING_RANKS
 * 				returns 0
 * 			<li>If card1 is in ShitheadRules.LAY_ON_ANYTHING_RANKS and card2 is not
 * 				returns 1
 * 			<li>If card1 is in not in ShitheadRules.LAY_ON_ANYTHING_RANKS and card2 is 
 * 				returns -1
 * 			</ul>
 * 			If neither cards are in ShitheadRules.LAY_ON_ANYTHING_RANKS
 * 			<ul>
 * 			<li>returns 1 if card1 has a higher rank
 * 			<li>returns -1 if card 2 has a higher rank
 * 			<li>returns 0 if both same rank
 * 			</ul>  
 * @author james
 *
 */
public class ShitheadCardComparator implements Comparator<Card> {

	/**
	 * Compares two cards for their value in the game Shithead
	 * 
	 * @param card1 First Card to compare
	 * @param card2 Second Card to compare
	 * @return When dealing with special cards:
	 * 			<ul>
	 * 			<li>If both cards are in ShitheadRules.LAY_ON_ANYTHING_RANKS
	 * 				returns 0
	 * 			<li>If card1 is in ShitheadRules.LAY_ON_ANYTHING_RANKS and card2 is not
	 * 				returns 1
	 * 			<li>If card1 is in not in ShitheadRules.LAY_ON_ANYTHING_RANKS and card2 is 
	 * 				returns -1
	 * 			</ul>
	 * 			If neither cards are in ShitheadRules.LAY_ON_ANYTHING_RANKS
	 * 			<ul>
	 * 			<li>returns 1 if card1 has a higher rank
	 * 			<li>returns -1 if card 2 has a higher rank
	 * 			<li>returns 0 if both same rank
	 * 			</ul> 
	 */
	public int compare(Card card1, Card card2) {
		
		// both special cards
		if (LAY_ON_ANYTHING_RANKS.contains(card1.getRank()) 
					&& LAY_ON_ANYTHING_RANKS.contains(card2.getRank())) 
			return 0 ;
		
		// card1 is special
		else if (LAY_ON_ANYTHING_RANKS.contains(card1.getRank())
					&& !LAY_ON_ANYTHING_RANKS.contains(card2.getRank())) 
			return 1 ;
		
		// card2 is special
		else if (!LAY_ON_ANYTHING_RANKS.contains(card1.getRank())
					&& LAY_ON_ANYTHING_RANKS.contains(card2.getRank())) 
			return -1 ;
		
		// both normal cards
		else 
			return card1.compareTo(card2) ;
	}
}
