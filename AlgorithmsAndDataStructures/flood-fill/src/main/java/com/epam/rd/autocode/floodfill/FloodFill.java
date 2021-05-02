package com.epam.rd.autocode.floodfill;

public interface FloodFill
{

    void flood(final String map, final FloodLogger logger);

    static FloodFill getInstance() {
        return new FloodFillInstance();
    }

    char LAND = '█';
    char WATER = '░';
}