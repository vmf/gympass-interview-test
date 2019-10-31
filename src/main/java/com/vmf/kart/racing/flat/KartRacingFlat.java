package com.vmf.kart.racing.flat;

import com.vmf.flat.files.Column;
import com.vmf.flat.files.Embedded;
import com.vmf.flat.files.FlatFile;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Representa o arquivo(flat file) de log
 */
@FlatFile(ignoreFirstLine = true)
public class KartRacingFlat {

    @Column(name = "TIME", index = 0)
    private LocalTime currentTime;

    @Embedded
    private PilotFlat pilotFlat;

    @Column(name = "ROUND", index = 4)
    private Integer round;

    @Column(name = "ROUND_TIME", index = 5)
    private Duration roundDuration;

    @Column(name = "ROUND_AVERAGE_SPEED", index = 6)
    private Double roundAverageSpeed;

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    public PilotFlat getPilotFlat() {
        return pilotFlat;
    }

    public void setPilotFlat(PilotFlat pilotFlat) {
        this.pilotFlat = pilotFlat;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Duration getRoundDuration() {
        return roundDuration;
    }

    public void setRoundDuration(Duration roundDuration) {
        this.roundDuration = roundDuration;
    }

    public Double getRoundAverageSpeed() {
        return roundAverageSpeed;
    }

    public void setRoundAverageSpeed(Double roundAverageSpeed) {
        this.roundAverageSpeed = roundAverageSpeed;
    }

    public String getPilotCode() {
        return this.pilotFlat.getCode();
    }
}
