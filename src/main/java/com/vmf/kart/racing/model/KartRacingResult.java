package com.vmf.kart.racing.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Representa o resultado da corrida como um todo.
 */
public class KartRacingResult {
    private final Collection<KartRacingDetail> details = new ArrayList<>();
    private Map<String, KartRacingRoundPosition> bestPilotRounds;
    private KartRacingRoundPosition bestRaceRound;


    public void addDetail(KartRacingDetail detail) {
        this.details.add(detail);
    }

    public void setBestPilotRounds(Map<String, KartRacingRoundPosition> bestPilotRounds) {
        this.bestPilotRounds = bestPilotRounds;
    }

    public void setBestRaceRound(KartRacingRoundPosition bestRaceRound) {
        this.bestRaceRound = bestRaceRound;
    }

    public Collection<KartRacingDetail> getDetails() {
        return Collections.unmodifiableCollection(this.details);
    }

    public Map<String, KartRacingRoundPosition> getBestPilotRounds() {
        return Collections.unmodifiableMap(this.bestPilotRounds);
    }

    public KartRacingRoundPosition getBestRaceRound() {
        return bestRaceRound;
    }
}
