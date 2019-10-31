package com.vmf.kart.racing.service;

import com.vmf.kart.racing.flat.KartRacingFlat;
import com.vmf.kart.racing.model.KartRacingStats;

/**
 * Representa o construtor do agente de estatísticas.
 */
public interface KartRacingStatsBuilder {
    KartRacingStats create(Iterable<KartRacingFlat> flats);
}
