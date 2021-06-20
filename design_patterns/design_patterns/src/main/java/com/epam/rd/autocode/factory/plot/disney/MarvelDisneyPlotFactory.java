package com.epam.rd.autocode.factory.plot.disney;

import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.EpicCrisis;
import com.epam.rd.autocode.factory.plot.Plot;
import com.epam.rd.autocode.factory.plot.PlotFactory;
import com.epam.rd.autocode.factory.plot.plot.MarvellDisneyPlot;

public class MarvelDisneyPlotFactory implements PlotFactory {
    private final Character[] heroes;
    private final EpicCrisis epicCrisis;
    private final Character villain;

    public MarvelDisneyPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.heroes = heroes;
        this.epicCrisis = epicCrisis;
        this.villain = villain;
    }

    @Override
    public Plot plot() {
        return new MarvellDisneyPlot(getCharacters(), getEpicCrisis(), getVillain());
    }

    @Override
    public Character getHero() {
        return null;
    }

    @Override
    public Character getBeloved() {
        return null;
    }

    @Override
    public Character getVillain() {
        return villain;
    }

    @Override
    public EpicCrisis getEpicCrisis() {
        return epicCrisis;
    }

    @Override
    public Character getFunnyFriend() {
        return null;
    }

    @Override
    public Character[] getCharacters() {
        return heroes;
    }
}
