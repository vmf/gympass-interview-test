package com.vmf.kart.racing.model;

import java.time.Duration;

/**
 * Representa o detalhe da corrida(por piloto)
 */
public class KartRacingDetail {
    private Integer position;
    private String pilotCode;
    private String pilotName;
    private long roundsCompleted;
    private Duration totalDuration;
    private Double pilotAverageSpeed;
    private Duration timeSpaceBetweenWinner;

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getPilotCode() {
        return pilotCode;
    }

    public void setPilotCode(String pilotCode) {
        this.pilotCode = pilotCode;
    }

    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }

    public long getRoundsCompleted() {
        return roundsCompleted;
    }

    public void setRoundsCompleted(long roundsCompleted) {
        this.roundsCompleted = roundsCompleted;
    }

    public Duration getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Duration totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Double getPilotAverageSpeed() {
        return pilotAverageSpeed;
    }

    public void setPilotAverageSpeed(Double pilotAverageSpeed) {
        this.pilotAverageSpeed = pilotAverageSpeed;
    }

    public Duration getTimeSpaceBetweenWinner() {
        return timeSpaceBetweenWinner;
    }

    public void setTimeSpaceBetweenWinner(Duration timeSpaceBetweenWinner) {
        this.timeSpaceBetweenWinner = timeSpaceBetweenWinner;
    }
}
