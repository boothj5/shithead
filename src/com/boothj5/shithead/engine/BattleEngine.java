package com.boothj5.shithead.engine;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerFactory;
import com.boothj5.shithead.game.player.interaction.PlayerInteraction;
import com.boothj5.shithead.ui.cli.ShitheadCli;
import static com.boothj5.util.IterationUtil.doTimes ;
import com.boothj5.util.MapUtil;

public final class BattleEngine extends ShitheadEngine {
    private static final int TURNS_THRESHOLD = 10000;
    final ShitheadCli cli ;
    List<String> playerNames = new ArrayList<String>() ; 
    final List<String> playerTypes = new ArrayList<String>() ;
    final Map<String, Integer> shitheadMap = new HashMap<String, Integer>() ;
    int numPlayers, numCards, turns ;
    int stalemates = 0 ;
    long startTime, stopTime, duration ; 
    boolean stalemate ;

    public BattleEngine(String[] args, ShitheadCli cli) throws ShitheadException {
        this.cli = cli ;
        cli.line() ;
        cli.welcome() ;
        numCards = 3 ;
        numGames = Integer.parseInt(args[1]) ;
        final Map<String, String> compPlayerList = PlayerFactory.computerPlayerList();

        for (String shortName : compPlayerList.keySet())
            playerTypes.add(compPlayerList.get(shortName)) ;

        numPlayers = playerTypes.size() ;
        playerNames = getPlayerNamesFromTypes(numCards, playerTypes) ;
        updateShitheadMap(playerNames, shitheadMap) ;
        startTime = System.currentTimeMillis() ;
    }

    @Override
    public void init() throws ShitheadException {
        Collections.shuffle(playerTypes) ;      
        playerNames = getPlayerNamesFromTypes(numCards, playerTypes) ;
        game = new ShitheadGame(playerNames, playerTypes, numCards) ;
        turns = 0 ;
        stalemate = false ;
    }

    @Override
    public void deal() {
        game.deal() ;
    }

    @Override
    public void swap() throws ShitheadException {
        for (Player player : game.getPlayers()) {
            PlayerInteraction playerInteraction = PlayerInteraction.forPlayer(player, game, cli) ;
            playerInteraction.swap() ;
        }
    }

    @Override
    public void firstMove() {
        game.firstMove() ;
    }

    @Override
    public void play() throws ShitheadException {	
        while (game.canContinue()) {
            if (turns == TURNS_THRESHOLD) {
                stalemate = true ;
                stalemates++ ;
                return ;
            } 
            else {
                final Player currentPlayer = game.getCurrentPlayer() ;

                if (game.currentPlayerCanMove()) {
                    PlayerInteraction playerInteraction = PlayerInteraction.forPlayer(currentPlayer, game, cli) ;
                    playerInteraction.makeMove() ;
                } 
                else {
                    game.playerPickUpPile() ;
                    game.moveToNextPlayer() ;
                }
                turns++ ;
            }
        }
    }

    @Override
    public void end() throws ShitheadException {
        if (!stalemate) {
            final String shithead = game.getShithead() ;
            int total = shitheadMap.get(shithead) ;
            total++ ;
            shitheadMap.put(shithead, total) ;
        }
    }

    @Override
    public void globalEnd() {
        stopTime = System.currentTimeMillis() ;
        duration = stopTime - startTime ;
        final float avg = getAverageGameTime(duration, numGames) ;
        cli.line() ;
        final Map<String, Integer> sortedShitheads = MapUtil.sortHashMapByValues(shitheadMap) ;
        cli.showBattleSummary(sortedShitheads, stalemates, duration, avg) ;		
    }

    @Override
    public void error(ShitheadException e) {
        cli.bail(e, game) ;
    }

    private float getAverageGameTime(long duration, int numGames) {
        final DecimalFormat twoPlaces = new DecimalFormat("#.##");
        final float avg = (new Float(duration) / new Float(numGames)) ;
        final float avgRounded = Float.valueOf(twoPlaces.format(avg)) ;
        return avgRounded ;
    }

    private static void updateShitheadMap(List<String> names, Map<String, Integer> shitheadMap) {
        for (String playerName : names)
            shitheadMap.put(playerName, 0) ;
    }

    private static List<String> getPlayerNamesFromTypes(int numCards, List<String> types) throws ShitheadException {
        String name = null;
        final List<String> result = new ArrayList<String>() ;

        for (int i : doTimes(types.size())) { 
            final String className = (PlayerFactory.createPlayer(types.get(i), "", numCards)).getClass().getName() ;
            final StringTokenizer st = new StringTokenizer(className, ".") ;

            while (st.hasMoreTokens())
                name = st.nextToken();

            result.add(name) ;
        }

        return result ;
    }
}