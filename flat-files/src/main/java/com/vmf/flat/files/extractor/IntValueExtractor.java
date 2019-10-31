package com.vmf.flat.files.extractor;

import com.vmf.flat.files.mapper.ExtractionContext;
import com.vmf.flat.files.mapper.ValueExtractor;

/**
 * Agente que extrai valores em {@link Integer}
 */
public class IntValueExtractor implements ValueExtractor<Integer> {

    @Override
    public Integer extract(ExtractionContext context) {
        return Integer.valueOf(context.getValue());
    }
}
