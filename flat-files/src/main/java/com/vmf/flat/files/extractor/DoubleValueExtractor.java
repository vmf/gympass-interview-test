package com.vmf.flat.files.extractor;

import com.vmf.flat.files.mapper.ExtractionContext;
import com.vmf.flat.files.mapper.ValueExtractor;

/**
 * Agente que extrai valores em {@link Double}
 */
public class DoubleValueExtractor implements ValueExtractor<Double> {

    @Override
    public Double extract(ExtractionContext context) {
        return Double.valueOf(context.getValue().replace(",", "."));
    }
}
