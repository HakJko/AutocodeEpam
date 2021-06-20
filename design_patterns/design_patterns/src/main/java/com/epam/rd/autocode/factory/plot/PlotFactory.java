package com.epam.rd.autocode.factory.plot;

public interface PlotFactory
{
    Plot plot();

    Character getHero();

    Character getBeloved();

    Character getVillain();

    EpicCrisis getEpicCrisis();

    Character getFunnyFriend();

    Character[] getCharacters();
}
