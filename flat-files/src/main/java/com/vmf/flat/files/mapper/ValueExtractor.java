package com.vmf.flat.files.mapper;

/**
 * Representa o extrator de um determinado tipo.
 * @param <T> : Tipo a ser extraído.
 */
public interface ValueExtractor<T> {
    T extract(ExtractionContext context);
}
