package com.vmf.flat.files.extractor;

import com.vmf.flat.files.mapper.ExtractionContext;
import com.vmf.flat.files.mapper.ValueExtractor;

import java.time.Duration;

/**
 * Agente que extrai valores em {@link Duration}
 */
public class DurationValueExtractor implements ValueExtractor<Duration> {

    @Override
    public Duration extract(ExtractionContext context) {
        String[] minutesAndSecondMilli = context.getValue().split(":");
        String[] secondAndMilli = minutesAndSecondMilli[1].split("\\.");

        String minutes = minutesAndSecondMilli[0];
        String seconds = secondAndMilli[0];
        String milliseconds = secondAndMilli[1];

        Duration duration = Duration.ofMinutes(Long.valueOf(minutes))
                .plusSeconds(Long.valueOf(seconds))
                .plusMillis(Long.valueOf(milliseconds));

        return duration;
    }
}