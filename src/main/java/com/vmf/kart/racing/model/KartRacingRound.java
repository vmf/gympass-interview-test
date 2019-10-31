package com.vmf.kart.racing.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Representa uma volta da corrida.
 */
public class KartRacingRound {
    private final Integer number;
    private Collection<KartRacingRoundPosition> roundPositions = new ArrayList<>();

    public KartRacingRound(Integer number) {
        this.number = number;
    }

    public void addRoundPosition(KartRacingRoundPosition roundPosition) {
        this.roundPositions.add(roundPosition);
    }

    public Collection<KartRacingRoundPosition> getRoundPositions() {
        return Collections.unmodifiableCollection(roundPositions);
    }

    public Integer getNumber() {
        return number;
    }
}
