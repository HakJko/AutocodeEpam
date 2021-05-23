package com.efimchick.ifmo.io.filetree.entity;

import java.nio.file.Path;

public class Wrapper
{
    private final Path myPath;
    private final int levelOfNesting;
    private boolean last;

    public Wrapper(Path myPath, int levelOfNesting) {
        this.myPath = myPath;
        this.levelOfNesting = levelOfNesting;
    }

    public Path getMyPath() {
        return myPath;
    }

    public int getLevelOfNesting() {
        return levelOfNesting;
    }

    public boolean isLast() {
        return last;
    }

    public boolean isNested() {
        return levelOfNesting > 0;
    }

    public void initializingLast() {
        last = true;
    }


}