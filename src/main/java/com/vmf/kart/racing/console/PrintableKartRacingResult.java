package com.vmf.kart.racing.console;

import com.vmf.kart.racing.model.KartRacingDetail;
import com.vmf.kart.racing.model.KartRacingResult;
import com.vmf.kart.racing.model.KartRacingRoundPosition;

import java.time.Duration;

/**
 * Agente responsável pelo 'output' do resultado da corrida
 */
public class PrintableKartRacingResult {

    public static void print(KartRacingResult result) {
        printDetails(result);
        printBestPilotsRound(result);
        printBestRaceRound(result);
    }

    private static void printBestRaceRound(KartRacingResult result) {
        String bestRoundSeparator = buildHorizontalSeparator(65);
        System.out.println();
        System.out.println(bestRoundSeparator);
        System.out.println("-> MELHOR CORRIDA");
        System.out.println(bestRoundSeparator);
        System.out.printf("%13s   %15s   %15s   %10s", "CÓDIGO PILOTO", "NOME PILOTO", "NÚMERO DA VOLTA", "TEMPO");
        System.out.println();
        System.out.println(bestRoundSeparator);

        KartRacingRoundPosition bestRaceRound = result.getBestRaceRound();
        System.out.printf("%13s   %15s   %15d   %10s", bestRaceRound.getPilotCode(),
                bestRaceRound.getFlatReference().getPilotFlat().getName(),
                bestRaceRound.getRoundNumber(), formatDuration(bestRaceRound.getFlatReference().getRoundDuration()));
        System.out.println();
        System.out.println(bestRoundSeparator);
    }

    private static void printBestPilotsRound(KartRacingResult result) {
        String bestPilotRoundsSeparator = buildHorizontalSeparator(60);
        System.out.println();
        System.out.println(bestPilotRoundsSeparator);
        System.out.println("-> MELHOR CORRIDA POR PILOTO");
        System.out.println(bestPilotRoundsSeparator);
        System.out.printf("%13s   %15s   %7s   %10s", "CÓDIGO PILOTO", "NOME PILOTO", "POSIÇÃO", "TEMPO");
        System.out.println();
        System.out.println(bestPilotRoundsSeparator);
        for (KartRacingRoundPosition roundPosition : result.getBestPilotRounds().values()) {
            System.out.printf("%13s   %15s   %7d   %10s", roundPosition.getPilotCode(),
                    roundPosition.getFlatReference().getPilotFlat().getName(),
                    roundPosition.getPosition(),
                    formatDuration(roundPosition.getRoundDuration()));
            System.out.println();
        }
        System.out.println(bestPilotRoundsSeparator);
    }

    private static void printDetails(KartRacingResult result) {
        String detailsSeparator = buildHorizontalSeparator(120);
        System.out.println(detailsSeparator);
        System.out.println("-> RESUMO DETALHADO DA CORRIDA POR PILOTO");
        System.out.println(detailsSeparator);
        System.out.printf("%15s   %13s   %15s   %23s   %20s   %16s", "POSIÇÃO CHEGADA", "CÓDIGO PILOTO", "NOME PILOTO",
                "QTDE VOLTAS COMPLETADAS", "TEMPO TOTAL DE PROVA", "VELOCIDADE MÉDIA");
        System.out.println();
        System.out.println(detailsSeparator);

        for (KartRacingDetail detail : result.getDetails()) {
            System.out.printf("%15d   %13s   %15s   %23d   %20s   %16f", detail.getPosition(), detail.getPilotCode(),
                    detail.getPilotName(), detail.getRoundsCompleted(), formatDuration(detail.getTotalDuration()),
                    detail.getPilotAverageSpeed());
            System.out.println();
        }
        System.out.println(detailsSeparator);
    }

    private static String buildHorizontalSeparator(int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++)
            builder.append("-");
        return builder.toString();
    }

    private static String formatDuration(Duration duration) {
        long s = duration.getSeconds();
        return String.format("%d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60));
    }
}
