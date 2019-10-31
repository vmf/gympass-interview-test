package com.vmf.flat.files.mapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Representa o mapper do flat file.
 * @param <T> Tipo do modelo
 */
public class FlatFileMapper<T> {
    private final FlatFileMapping<T> fileMapping;

    private FlatFileMapper(FlatFileMapping<T> fileMapping) {
        this.fileMapping = fileMapping;
    }

    public static <T> FlatFileMapper of(Class<T> modelClass) {
        FlatFileMapping<T> mapping = FlatFileMapping.create(modelClass);
        return new FlatFileMapper(mapping);
    }

    public Iterable<T> map(String filePath) throws IOException {
        return map(Paths.get(filePath));
    }

    public Iterable<T> map(Path path) throws IOException {
        return map(Files.lines(path));
    }

    public Iterable<T> map(Stream<String> stream) {
        FileConsumer fileConsumer = new FileConsumer(this.fileMapping);
        long skipLines = this.fileMapping.ignoreFirstLine() ? 1 : 0;
        stream.skip(skipLines).forEachOrdered(fileConsumer);
        return fileConsumer.getCollection();
    }
}
