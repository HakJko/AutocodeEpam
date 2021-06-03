package com.epam.rd.autocode.factory.plot.plot;

import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.EpicCrisis;
import com.epam.rd.autocode.factory.plot.Plot;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MarvellDisneyPlot implements Plot {
    private final Character[] heroes;
    private final EpicCrisis epicCrisis;
    private final Character villain;
    private final String BRAVE = " brave ";
    private final String plot;

    public MarvellDisneyPlot(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.heroes = heroes;
        this.epicCrisis = epicCrisis;
        this.villain = villain;
        this.plot = getPlot();
    }

    private String getBraveCharacters() {
        return Arrays.stream(heroes).map(character -> BRAVE + character.name()).collect(Collectors.joining(","));
    }

    @Override
    public String getPlot() {
        return epicCrisis.name() + " threatens the world. But" +
                getBraveCharacters() + " on guard. So, no way that intrigues of " +
                villain.name() + " overcome the willpower of inflexible heroes";
    }

    @Override
    public String toString() {
        return plot;
    }
}
