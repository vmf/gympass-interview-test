package com.vmf.kart.racing.model;

import com.vmf.kart.racing.flat.KartRacingFlat;

import java.time.Duration;

/**
 * Representa um determinada posição de um piloto dentro de uma volta.
 */
public class KartRacingRoundPosition {
    private final KartRacingFlat flatReference;
    private final Integer roundNumber;
    private final Integer position;

    public KartRacingRoundPosition(KartRacingFlat flatReference, Integer roundNumber, Integer position) {
        this.flatReference = flatReference;
        this.roundNumber = roundNumber;
        this.position = position;
    }

    public KartRacingFlat getFlatReference() {
        return flatReference;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public Integer getPosition() {
        return position;
    }

    public String getPilotCode() {
        return flatReference.getPilotCode();
    }

    public Duration getRoundDuration() {
        return flatReference.getRoundDuration();
    }
}
