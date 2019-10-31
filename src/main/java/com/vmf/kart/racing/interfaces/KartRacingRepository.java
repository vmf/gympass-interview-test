package com.vmf.kart.racing.interfaces;

import com.vmf.kart.racing.flat.KartRacingFlat;

/**
 * Representa um repositório de corrida
 */
public interface KartRacingRepository {

    /**
     * Retorna todos os resultados da corrida
     * @return
     */
    Iterable<KartRacingFlat> findAll();
}
