package com.vmf.kart.racing.model;

import com.vmf.kart.racing.flat.KartRacingFlat;

import java.time.Duration;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Agente responsável pela extração das estatísticas da corrida.
 */
public class KartRacingStats {
    private final Collection<KartRacingRound> rounds = new ArrayList<>();

    public void addRound(KartRacingRound round) {
        this.rounds.add(round);
    }

    public Collection<KartRacingRound> getRounds() {
        return Collections.unmodifiableCollection(this.rounds);
    }

    public Map<String, KartRacingRoundPosition> findBestPilotRounds() {
        return getRounds().stream()
                .flatMap(rc -> rc.getRoundPositions()
                        .stream())
                .collect(Collectors.toMap(KartRacingRoundPosition::getPilotCode, Function.identity(),
                        BinaryOperator.minBy(Comparator.comparing(KartRacingRoundPosition::getRoundDuration))))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(o -> o.getValue().getPosition()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o1, o2) -> o1, LinkedHashMap::new));
    }

    public Map<String, KartRacingRoundPosition> findLastPilotRound() {
        return getRounds().stream()
                .flatMap(rc -> rc.getRoundPositions()
                        .stream())
                .collect(Collectors.toMap(KartRacingRoundPosition::getPilotCode, Function.identity(),
                        BinaryOperator.minBy(Comparator.comparing(KartRacingRoundPosition::getRoundNumber))));
    }

    public KartRacingRoundPosition findBestRaceRound() {
        return getRounds().stream()
                .flatMap(kr -> kr.getRoundPositions().stream())
                .min(Comparator.comparing(o -> o.getFlatReference().getRoundDuration())).get();
    }

    public Map<String, Double> findPilotsAverageSpeed() {
        Map<String, Double> map = getRounds().stream()
                .flatMap(rc -> rc.getRoundPositions()
                        .stream()
                        .map(rp -> rp.getFlatReference()))
                .collect(Collectors.groupingBy(fr -> fr.getPilotFlat().getCode(), Collectors.averagingDouble(fr -> fr.getRoundAverageSpeed())));
        return map;
    }

    public Map<String, Duration> findTimeSpaceBetweenWinner() {
        KartRacingFlat winningFlat = getRounds().stream()
                .max(Comparator.comparing(KartRacingRound::getNumber))
                .get()
                .getRoundPositions()
                .stream()
                .min(Comparator.comparing(KartRacingRoundPosition::getPosition))
                .get()
                .getFlatReference();

        return getRounds().stream()
                .flatMap(rc -> rc.getRoundPositions()
                        .stream()
                        .map(rp -> rp.getFlatReference()))
                .collect(Collectors.toMap(KartRacingFlat::getPilotCode, Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparing(KartRacingFlat::getRound))))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> Duration.between(winningFlat.getCurrentTime(), e.getValue().getCurrentTime())));
    }

    public long sumPilotCompletedRounds(String pilotCode) {
        return getRounds().stream()
                .flatMap(x -> x.getRoundPositions().stream())
                .filter(x -> x.getPilotCode().equals(pilotCode))
                .count();
    }

    public Duration sumPilotTotalRaceDuration(String pilotCode) {
        return getRounds().stream()
                .flatMap(x -> x.getRoundPositions().stream())
                .filter(x -> x.getPilotCode().equals(pilotCode))
                .map(x -> Duration.from(x.getFlatReference().getRoundDuration()))
                .reduce((duration, duration2) -> duration.plus(duration2)).get();
    }

    public KartRacingResult buildResult() {
        Map<String, KartRacingRoundPosition> lastPilotRound = findLastPilotRound();
        Map<String, Double> pilotsAverageSpeed = findPilotsAverageSpeed();
        Map<String, Duration> timeSpaceBetweenWinner = findTimeSpaceBetweenWinner();

        KartRacingResult result = new KartRacingResult();
        Collection<KartRacingDetail> details = new ArrayList<>();
        for (KartRacingRoundPosition roundPosition : lastPilotRound.values()) {
            KartRacingDetail detail = new KartRacingDetail();

            detail.setPosition(roundPosition.getPosition());
            detail.setPilotCode(roundPosition.getPilotCode());
            detail.setPilotName(roundPosition.getFlatReference().getPilotFlat().getName());
            detail.setRoundsCompleted(sumPilotCompletedRounds(roundPosition.getPilotCode()));
            detail.setTotalDuration(sumPilotTotalRaceDuration(roundPosition.getPilotCode()));
            detail.setPilotAverageSpeed(pilotsAverageSpeed.get(roundPosition.getPilotCode()));
            detail.setTimeSpaceBetweenWinner(timeSpaceBetweenWinner.get(roundPosition.getPilotCode()));

            details.add(detail);
        }
        details.stream().sorted(Comparator.comparing(KartRacingDetail::getPosition))
                .forEachOrdered(x -> result.addDetail(x));

        result.setBestPilotRounds(findBestPilotRounds());
        result.setBestRaceRound(findBestRaceRound());

        return result;
    }
}
