package com.efimchick.ifmo.io.filetree.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.efimchick.ifmo.io.filetree.Indentation.addIndent;

public class Directory implements EntityFactory
{

    @Override
    public void pathToString(Wrapper wrapper, StringBuilder out) {
        try {
            convertPath(wrapper, out, getMemory(wrapper.getMyPath()));
        } catch (IOException e) {
            throw new RuntimeException("memory cannot be retrieved", e);
        }

        List<Path> innerPaths = getFilesListSorted(wrapper.getMyPath());
        List<Wrapper> wrappers = wrapList(innerPaths, wrapper.getLevelOfNesting() + 1);

        wrappers.forEach(pathWrapper1 ->
                checkPathAndConvert(pathWrapper1).pathToString(pathWrapper1, out));
    }

    @Override
    public long getMemory(Path path) throws IOException {
        List<Path> files = Files.list(path)
                .collect(Collectors.toList());
        EntityFactory entityFactory = null;
        long size = 0;

        for (Path file : files) {
            if (Files.isRegularFile(file)) {
                entityFactory = new File();
            } else if (Files.isDirectory(file)) {
                entityFactory = new Directory();
            }
            size += entityFactory.getMemory(file);
        }

        return size;
    }

    private EntityFactory checkPathAndConvert(Wrapper wrapper1) {
        EntityFactory entityFactory = null;
        if (Files.isRegularFile(wrapper1.getMyPath())) {
            entityFactory = new File();
        } else if (Files.isDirectory(wrapper1.getMyPath())) {
            entityFactory = new Directory();
        }

        return entityFactory;
    }

    private void convertPath(Wrapper wrapper, StringBuilder stringBuilder, long size) {
        if (wrapper.isNested()) {
            addIndent(wrapper, stringBuilder);
        }

        stringBuilder.append(wrapper.getMyPath().getFileName())
                .append(" ")
                .append(size)
                .append(" ")
                .append("bytes")
                .append("\n");
    }

    private List<Path> getFilesListSorted(Path path) {
        try {
            return Files.list(path)
//                    .sorted(new Comparator<Path>() {
//                        @Override
//                        public int compare(Path p1, Path p2) {
//                            return p1.getFileName().toString()
//                                    .compareToIgnoreCase(p2.getFileName().toString());
//                        }
//                    })
//                    .sorted(new Comparator<Path>() {
//                        @Override
//                        public int compare(Path p1, Path p2) {
//                            return (Files.isDirectory(p2) ? 1 : 0)
//                                    - (Files.isDirectory(p1) ? 1 : 0);
//                        }
//                    })
                    .sorted((p1, p2) -> p1.getFileName().toString()
                            .compareToIgnoreCase(p2.getFileName().toString()))
                    .sorted((p1, p2) -> {
                        int dir1 = Files.isDirectory(p1) ? 1 : 0;
                        int dir2 = Files.isDirectory(p2) ? 1 : 0;
                        return dir2 - dir1;
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("sorting cannot be performed", e);
        }
    }

    private List<Wrapper> wrapList(List<Path> paths, int nesting) {
        List<Wrapper> wrappers = paths.stream()
                .map(path -> new Wrapper(path, nesting))
                .collect(Collectors.toList());

        markLastElement(wrappers);

        return wrappers;
    }

    private void markLastElement(List<Wrapper> wrappers) {
        wrappers.get(wrappers.size() - 1).initializingLast();
    }
}