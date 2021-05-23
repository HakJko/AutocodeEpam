package com.efimchick.ifmo.io.filetree.entity;

import java.io.IOException;
import java.nio.file.Path;

public interface EntityFactory
{
    void pathToString(Wrapper wrapper, StringBuilder stringBuilder);

    long getMemory(Path path) throws IOException;
}