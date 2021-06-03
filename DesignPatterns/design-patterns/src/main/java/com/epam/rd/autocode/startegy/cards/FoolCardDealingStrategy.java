package com.epam.rd.autocode.startegy.cards;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FoolCardDealingStrategy implements CardDealingStrategy
{
    private static final String PLAYER = "Player ";
    private static final String TRUMP_CARD = "Trump card";
    private static final String REMAINING = "Remaining";
    private static final int NUMBER_OF_CARDS_FOR_PLAYER = 6;


    @Override
    public Map<String, List<Card>> dealStacks(final Deck deck, final int players) {
        Map<String, List<Card>> stacks = new LinkedHashMap<>();
        createStacks(stacks, players);
        for (int i = 0; i < NUMBER_OF_CARDS_FOR_PLAYER; i++) {
            for (int j = 0; j < players; j++) {
                stacks.get(PLAYER + (j + 1)).add(deck.dealCard());
            }
        }
        stacks.get(TRUMP_CARD).add(deck.dealCard());
        stacks.get(REMAINING).addAll(deck.restCards());
        return stacks;
    }

    private void createStacks(final Map<String, List<Card>> stacks, final int players) {
        for (int i = 0; i < players; i++) {
            stacks.put(PLAYER + (i + 1), new ArrayList<>());
        }
        stacks.put(REMAINING, new ArrayList<>());
        stacks.put(TRUMP_CARD, new ArrayList<>());
    }
}
