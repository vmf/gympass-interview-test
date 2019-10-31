package com.vmf.kart.racing;

import com.vmf.kart.racing.console.PrintableKartRacingResult;
import com.vmf.kart.racing.factory.KartRacingServiceFactory;
import com.vmf.kart.racing.model.KartRacingResult;
import com.vmf.kart.racing.service.KartRacingService;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.init();
    }

    private void init() {
        KartRacingService service = KartRacingServiceFactory.factory();
        KartRacingResult result = service.getResult();
        PrintableKartRacingResult.print(result);
    }
}
