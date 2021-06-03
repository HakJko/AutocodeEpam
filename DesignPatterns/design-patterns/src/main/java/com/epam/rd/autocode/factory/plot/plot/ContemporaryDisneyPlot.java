package com.epam.rd.autocode.factory.plot.plot;

import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.EpicCrisis;
import com.epam.rd.autocode.factory.plot.Plot;

public class ContemporaryDisneyPlot implements Plot {
    private final Character hero;
    private final EpicCrisis epicCrisis;
    private final Character funnyFriend;
    private final String plot;

    public ContemporaryDisneyPlot(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        this.hero = hero;
        this.epicCrisis = epicCrisis;
        this.funnyFriend = funnyFriend;
        this.plot = getPlot();
    }

    @Override
    public String getPlot() {
        return hero.name() + " feels a bit awkward and uncomfortable. But personal issues fades, when a big trouble comes - " +
                epicCrisis.name() + ". " +
                hero.name() + " stands up against it, but with no success at first.But putting self together and help of friends, including spectacular funny " +
                funnyFriend.name() + " restore the spirit and " +
                hero.name() + " overcomes the crisis and gains gratitude and respect";
    }

    @Override
    public String toString() {
        return plot;
    }
}
