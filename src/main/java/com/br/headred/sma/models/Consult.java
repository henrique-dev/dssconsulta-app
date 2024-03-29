/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.models;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class Consult {
    
    private int consultId;
    private Timestamp consultCreationDate;
    private Timestamp consultForDate;
    private boolean consultConsulted;
    private PatientProfile patient;
    private MedicSpeciality medicSpeciality;
    private MedicWorkAddress medicWorkAddress;

    public Consult() {
    }

    public Consult(int consultId) {
        this.consultId = consultId;
    }

    public Consult(int consultId, Timestamp consultCreationDate, Timestamp consultForDate, boolean consultConsulted, PatientProfile patient, MedicSpeciality medicSpeciality, MedicWorkAddress medicWorkAddress) {
        this.consultId = consultId;
        this.consultCreationDate = consultCreationDate;
        this.consultForDate = consultForDate;
        this.consultConsulted = consultConsulted;
        this.patient = patient;
        this.medicSpeciality = medicSpeciality;
        this.medicWorkAddress = medicWorkAddress;
    }

    public Consult(Timestamp consultCreationDate, Timestamp consultForDate, boolean consultConsulted, PatientProfile patient, MedicSpeciality medicSpeciality, MedicWorkAddress medicWorkAddress) {
        this.consultCreationDate = consultCreationDate;
        this.consultForDate = consultForDate;
        this.consultConsulted = consultConsulted;
        this.patient = patient;
        this.medicSpeciality = medicSpeciality;
        this.medicWorkAddress = medicWorkAddress;
    }           

    public int getConsultId() {
        return consultId;
    }

    public void setConsultId(int consultId) {
        this.consultId = consultId;
    }

    public Timestamp getConsultCreationDate() {
        return consultCreationDate;
    }

    public void setConsultCreationDate(Timestamp consultCreationDate) {
        this.consultCreationDate = consultCreationDate;
    }    

    public Timestamp getConsultForDate() {
        return consultForDate;
    }

    public void setConsultForDate(Timestamp consultForDate) {
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

    public PatientProfile getPatientProfile() {
        return patient;
    }

    public void setPatientProfile(PatientProfile patient) {
        this.patient = patient;
    }        
    
}
