package com.boothj5.shithead.game;

import java.util.EnumSet;

import com.boothj5.shithead.game.card.Card;

public class ShitheadRules {
	public static final EnumSet<Card.Rank> LAY_ON_ANYTHING_RANKS = 
		EnumSet.<Card.Rank>of(Card.Rank.TWO, Card.Rank.SEVEN, Card.Rank.TEN) ;
	public static final EnumSet<Card.Rank> NORMAL_RANKS = 
		EnumSet.complementOf(LAY_ON_ANYTHING_RANKS) ;

	public  static final Card.Rank INVISIBLE = Card.Rank.SEVEN ;
	public  static final Card.Rank MISS_A_TURN = Card.Rank.EIGHT ;
	public  static final Card.Rank BURN = Card.Rank.TEN ;
}
