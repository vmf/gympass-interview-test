package com.vmf.kart.racing.flat;

import com.vmf.flat.files.Column;
import com.vmf.flat.files.Embeddable;

/**
 * Representa o piloto do arquivo(flat file) de log
 */
@Embeddable
public class PilotFlat {

    @Column(name = "PILOT_CODE", index = 1)
    private String code;

    @Column(name = "PILOT_NAME", index = 3)
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
