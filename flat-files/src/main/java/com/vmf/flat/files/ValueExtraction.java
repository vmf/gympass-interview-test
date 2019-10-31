package com.vmf.flat.files;

import com.vmf.flat.files.mapper.ValueExtractor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Representa um extrator de valor de um flat file. {@link FlatFile}
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueExtraction {
    Class<? extends ValueExtractor> extractorClass();
}
