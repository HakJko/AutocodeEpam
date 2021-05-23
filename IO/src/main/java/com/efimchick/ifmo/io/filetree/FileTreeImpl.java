package com.efimchick.ifmo.io.filetree;

import com.efimchick.ifmo.io.filetree.entity.File;
import com.efimchick.ifmo.io.filetree.entity.Directory;
import com.efimchick.ifmo.io.filetree.entity.EntityFactory;
import com.efimchick.ifmo.io.filetree.entity.Wrapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class FileTreeImpl implements FileTree {
    private EntityFactory entityFactory;

    @Override
    public Optional<String> tree(Path path) {
        if (path == null || Files.notExists(path)) {
            return Optional.empty();
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            Wrapper wrapper = new Wrapper(path, 0);
            if (Files.isRegularFile(wrapper.getMyPath())) {
                entityFactory = new File();
            } else if (Files.isDirectory(wrapper.getMyPath())) {
                entityFactory = new Directory();
            }

            entityFactory.pathToString(wrapper, stringBuilder);
            return Optional.of(stringBuilder.toString());
        }
    }
}