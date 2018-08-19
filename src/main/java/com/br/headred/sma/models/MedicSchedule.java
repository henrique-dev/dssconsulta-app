/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.models;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class MedicSchedule {
    
    private long medicScheduleId;
    private MedicProfile medicProfile;
    private ClinicProfile cliProfile;
    private String schedule;

    public long getMedicScheduleId() {
        return medicScheduleId;
    }

    public void setMedicScheduleId(long medicScheduleId) {
        this.medicScheduleId = medicScheduleId;
    }

    public MedicProfile getMedicProfile() {
        return medicProfile;
    }

    public void setMedicProfile(MedicProfile medicProfile) {
        this.medicProfile = medicProfile;
    }

    public ClinicProfile getCliProfile() {
        return cliProfile;
    }

    public void setCliProfile(ClinicProfile cliProfile) {
        this.cliProfile = cliProfile;
    }        

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }        
    
}
