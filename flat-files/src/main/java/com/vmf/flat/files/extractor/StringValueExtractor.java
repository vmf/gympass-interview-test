package com.vmf.flat.files.extractor;

import com.vmf.flat.files.mapper.ExtractionContext;
import com.vmf.flat.files.mapper.ValueExtractor;

/**
 * Agente que extrai valores em {@link String}.
 */
public class StringValueExtractor implements ValueExtractor<String> {

    @Override
    public String extract(ExtractionContext context) {
        return context.getValue();
    }
}
