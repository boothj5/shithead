package com.boothj5.shithead.game;

import java.util.EnumSet;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Card.Rank;

public class ShitheadRules {
	public static final EnumSet<Rank> LAY_ON_ANYTHING_RANKS = 
		EnumSet.<Rank>of(Rank.TWO, Rank.SEVEN, Rank.TEN) ;

	public static final EnumSet<Rank> NORMAL_RANKS = 
		EnumSet.complementOf(LAY_ON_ANYTHING_RANKS) ;

	public static final Rank INVISIBLE = Rank.SEVEN ;
	public static final Rank MISS_A_TURN = Rank.EIGHT ;
	public static final Rank BURN = Rank.TEN ;
	
	public static boolean isSpecial(Card card) {
	    return LAY_ON_ANYTHING_RANKS.contains(card.getRank()) ;
	}
	
	public static boolean isInvisible(Card card) {
	    return INVISIBLE.equals(card.getRank()) ;
	}
}
