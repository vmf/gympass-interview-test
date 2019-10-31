package com.vmf.flat.files.mapper;

/**
 * Representa o contexto de extração de um determinado valor
 */
public class ExtractionContext {
    private final String value;

    public ExtractionContext(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
