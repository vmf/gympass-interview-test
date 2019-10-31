package com.vmf.kart.racing.exception;

/**
 * Representa uma falha na extração dos logs da corrida
 */
public class KartRacingRetrieveFailure extends RuntimeException {
    public KartRacingRetrieveFailure(String message, Throwable cause) {
        super(message, cause);
    }
}
