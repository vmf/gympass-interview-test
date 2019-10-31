package com.vmf.flat.files.extractor;

import com.vmf.flat.files.mapper.ExtractionContext;
import com.vmf.flat.files.mapper.ValueExtractor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Agente que extrai valores em {@link LocalTime}.
 */
public class LocalTimeExtractor implements ValueExtractor<LocalTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    @Override
    public LocalTime extract(ExtractionContext context) {
        return LocalTime.parse(context.getValue(), FORMATTER);
    }
}
