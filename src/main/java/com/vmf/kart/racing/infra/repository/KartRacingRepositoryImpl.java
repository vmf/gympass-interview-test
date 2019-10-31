package com.vmf.kart.racing.infra.repository;

import com.vmf.flat.files.mapper.FlatFileMapper;
import com.vmf.kart.racing.flat.KartRacingFlat;
import com.vmf.kart.racing.exception.KartRacingRetrieveFailure;
import com.vmf.kart.racing.interfaces.KartRacingRepository;

import java.io.IOException;

/**
 * Responsável pela extração do resultado da corrida.
 */
public class KartRacingRepositoryImpl implements KartRacingRepository {

    @Override
    public Iterable<KartRacingFlat> findAll() {
        try {
            FlatFileMapper<KartRacingFlat> mapper = FlatFileMapper.of(KartRacingFlat.class);
            ClassLoader classLoader = this.getClass().getClassLoader();
            String fileName = classLoader.getResource("kart_racing_sample.txt").getFile();
            Iterable<KartRacingFlat> kartRacingCollection = mapper.map(fileName);
            return kartRacingCollection;
        } catch (IOException e) {
            throw new KartRacingRetrieveFailure("Ocorreu uma falha na recuperação dos logs da corrida.", e);
        }
    }
}
