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
public class Consult {
    
    private long consultId;
    private Date consultCreationDate;
    private Date consultForDate;
    private boolean consultConsulted;
    private MedicSpeciality medicSpeciality;
    private MedicWorkAddress medicWorkAddress;

    public long getConsultId() {
        return consultId;
    }

    public void setConsultId(long consultId) {
        this.consultId = consultId;
    }

    public Date getConsultCreationDate() {
        return consultCreationDate;
    }

    public void setConsultCreationDate(Date consultCreationDate) {
        this.consultCreationDate = consultCreationDate;
    }

    public Date getConsultForDate() {
        return consultForDate;
    }

    public void setConsultForDate(Date consultForDate) {
        this.consultForDate = consultForDate;
    }

    public boolean isConsultConsulted() {
        return consultConsulted;
    }

    public void setConsultConsulted(boolean consultConsulted) {
        this.consultConsulted = consultConsulted;
    }

    public MedicSpeciality getMedicSpeciality() {
        return medicSpeciality;
    }

    public void setMedicSpeciality(MedicSpeciality medicSpeciality) {
        this.medicSpeciality = medicSpeciality;
    }

    public MedicWorkAddress getMedicWorkAddress() {
        return medicWorkAddress;
    }

    public void setMedicWorkAddress(MedicWorkAddress medicWorkAddress) {
        this.medicWorkAddress = medicWorkAddress;
    }        
    
}
