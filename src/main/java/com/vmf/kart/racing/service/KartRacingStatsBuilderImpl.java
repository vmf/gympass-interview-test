package com.vmf.kart.racing.service;

import com.vmf.kart.racing.flat.KartRacingFlat;
import com.vmf.kart.racing.model.KartRacingStats;
import com.vmf.kart.racing.model.KartRacingRound;
import com.vmf.kart.racing.model.KartRacingRoundPosition;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Construtor do agente de estatísticas.
 */
public class KartRacingStatsBuilderImpl implements KartRacingStatsBuilder {

    @Override
    public KartRacingStats create(Iterable<KartRacingFlat> flats) {
        Map<Integer, List<KartRacingFlat>> groupedByRound = StreamSupport.stream(flats.spliterator(), false)
                .collect(Collectors.groupingBy(KartRacingFlat::getRound));

        KartRacingStats analytics = new KartRacingStats();
        for (Map.Entry<Integer, List<KartRacingFlat>> entry : groupedByRound.entrySet()) {
            List<KartRacingFlat> racingFlats = entry.getValue();

            List<KartRacingFlat> sortedFlats = racingFlats.stream()
                    .sorted(Comparator.comparing(KartRacingFlat::getRoundDuration))
                    .collect(Collectors.toList());

            KartRacingRound round = new KartRacingRound(entry.getKey());
            for (int index = 0; index < sortedFlats.size(); index++) {
                KartRacingFlat flatReference = sortedFlats.get(index);
                KartRacingRoundPosition roundPosition = new KartRacingRoundPosition(flatReference, round.getNumber(),index + 1);
                round.addRoundPosition(roundPosition);
            }
            analytics.addRound(round);
        }
        return analytics;
    }
}
