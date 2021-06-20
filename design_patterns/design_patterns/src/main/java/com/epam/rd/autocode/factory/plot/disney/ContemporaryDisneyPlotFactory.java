package com.epam.rd.autocode.factory.plot.disney;

import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.EpicCrisis;
import com.epam.rd.autocode.factory.plot.Plot;
import com.epam.rd.autocode.factory.plot.PlotFactory;
import com.epam.rd.autocode.factory.plot.plot.ContemporaryDisneyPlot;

public class ContemporaryDisneyPlotFactory implements PlotFactory {
    private final Character hero;
    private final EpicCrisis epicCrisis;
    private final Character funnyFriend;

    public ContemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        this.hero = hero;
        this.epicCrisis = epicCrisis;
        this.funnyFriend = funnyFriend;
    }

    @Override
    public Plot plot() {
        return new ContemporaryDisneyPlot(getHero(), getEpicCrisis(), getFunnyFriend());
    }

    @Override
    public Character getHero() {
        return hero;
    }

    @Override
    public Character getBeloved() {
        return null;
    }

    @Override
    public Character getVillain() {
        return null;
    }

    @Override
    public EpicCrisis getEpicCrisis() {
        return epicCrisis;
    }

    @Override
    public Character getFunnyFriend() {
        return funnyFriend;
    }

    @Override
    public Character[] getCharacters() {
        return new Character[0];
    }
}
