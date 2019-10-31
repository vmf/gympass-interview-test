package com.vmf.kart.racing.service;

import com.vmf.kart.racing.flat.KartRacingFlat;
import com.vmf.kart.racing.interfaces.KartRacingRepository;
import com.vmf.kart.racing.model.KartRacingStats;
import com.vmf.kart.racing.model.KartRacingResult;

/**
 * Camada de serviço da corrida.
 */
public class KartRacingServiceImpl implements KartRacingService {

    private final KartRacingRepository repository;
    private final KartRacingStatsBuilder statsBuilder;

    public KartRacingServiceImpl(KartRacingRepository repository,
                                 KartRacingStatsBuilder statsBuilder) {
        this.repository = repository;
        this.statsBuilder = statsBuilder;
    }

    @Override
    public KartRacingResult getResult() {
        Iterable<KartRacingFlat> flats = repository.findAll();
        KartRacingStats stats = statsBuilder.create(flats);
        return stats.buildResult();
    }
}
