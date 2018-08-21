/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.models;

import java.sql.Date;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class MedicWorkScheduling {
    
    private int medicWorkSchedulingId;
    private int medicWorkSchedulingPerDay;
    private Date medicWorkSchedulingDateLast;
    private int medicWorkSchedulingCounterOfDay;
    private String medicWorkSchedulingInfo;

    public MedicWorkScheduling() {
    }

    public MedicWorkScheduling(int medicWorkSchedulingId) {
        this.medicWorkSchedulingId = medicWorkSchedulingId;
    }

    public MedicWorkScheduling(int medicWorkSchedulingId, int medicWorkSchedulingPerDay, Date medicWorkSchedulingDateLast, int medicWorkSchedulingCounterOfDay, String medicWorkSchedulingInfo) {
        this.medicWorkSchedulingId = medicWorkSchedulingId;
        this.medicWorkSchedulingPerDay = medicWorkSchedulingPerDay;
        this.medicWorkSchedulingDateLast = medicWorkSchedulingDateLast;
        this.medicWorkSchedulingCounterOfDay = medicWorkSchedulingCounterOfDay;
        this.medicWorkSchedulingInfo = medicWorkSchedulingInfo;
    }

    public MedicWorkScheduling(int medicWorkSchedulingPerDay, Date medicWorkSchedulingDateLast, int medicWorkSchedulingCounterOfDay, String medicWorkSchedulingInfo) {
        this.medicWorkSchedulingPerDay = medicWorkSchedulingPerDay;
        this.medicWorkSchedulingDateLast = medicWorkSchedulingDateLast;
        this.medicWorkSchedulingCounterOfDay = medicWorkSchedulingCounterOfDay;
        this.medicWorkSchedulingInfo = medicWorkSchedulingInfo;
    }      

    public int getMedicWorkSchedulingId() {
        return medicWorkSchedulingId;
    }

    public void setMedicWorkSchedulingId(int medicWorkSchedulingId) {
        this.medicWorkSchedulingId = medicWorkSchedulingId;
    }

    public int getMedicWorkSchedulingPerDay() {
        return medicWorkSchedulingPerDay;
    }

    public void setMedicWorkSchedulingPerDay(int medicWorkSchedulingPerDay) {
        this.medicWorkSchedulingPerDay = medicWorkSchedulingPerDay;
    }

    public Date getMedicWorkSchedulingDateLast() {
        return medicWorkSchedulingDateLast;
    }

    public void setMedicWorkSchedulingDateLast(Date medicWorkSchedulingDateLast) {
        this.medicWorkSchedulingDateLast = medicWorkSchedulingDateLast;
    }

    public int getMedicWorkSchedulingCounterOfDay() {
        return medicWorkSchedulingCounterOfDay;
    }

    public void setMedicWorkSchedulingCounterOfDay(int medicWorkSchedulingCounterOfDay) {
        this.medicWorkSchedulingCounterOfDay = medicWorkSchedulingCounterOfDay;
    }       

    public String getMedicWorkSchedulingInfo() {
        return medicWorkSchedulingInfo;
    }

    public void setMedicWorkSchedulingInfo(String medicWorkSchedulingInfo) {
        this.medicWorkSchedulingInfo = medicWorkSchedulingInfo;
    }        
    
}
