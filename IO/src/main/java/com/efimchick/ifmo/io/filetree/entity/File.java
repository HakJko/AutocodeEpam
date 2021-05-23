package com.efimchick.ifmo.io.filetree.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.efimchick.ifmo.io.filetree.Indentation.addIndent;

public class File implements EntityFactory
{
    @Override
    public void pathToString(Wrapper wrapper, StringBuilder stringBuilder) {
        if (wrapper.isNested()) {
            addIndent(wrapper, stringBuilder);
        }
        long size;
        try {
            size = getMemory(wrapper.getMyPath());
        } catch (IOException e) {
            throw new RuntimeException("memory cannot be retrieved", e);
        }

        stringBuilder.append(wrapper.getMyPath().getFileName())
                .append(" ")
                .append(size)
                .append(" ")
                .append("bytes")
                .append("\n");
    }

    @Override
    public long getMemory(Path path) throws IOException {
        return Files.size(path);
    }
}