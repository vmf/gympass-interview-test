package com.vmf.kart.racing.factory;

import com.vmf.kart.racing.infra.repository.KartRacingRepositoryImpl;
import com.vmf.kart.racing.service.KartRacingService;
import com.vmf.kart.racing.service.KartRacingServiceImpl;
import com.vmf.kart.racing.service.KartRacingStatsBuilderImpl;

/**
 * Factory para o serviço de corrida
 */
public class KartRacingServiceFactory {
    public static KartRacingService factory() {
        return new KartRacingServiceImpl(new KartRacingRepositoryImpl(), new KartRacingStatsBuilderImpl());
    }
}
