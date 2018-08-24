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
public class AccountSpeciality {
    
    private int accountSpecialityId;
    private PatientAccount patientAccount;
    private MedicSpeciality medicSpeciality;
    private Timestamp patientAccountSpecialityCreationDate;
    private Timestamp patientAccountSpecialityUseDate;
    private boolean patientAccountSpecialityUsed;

    public AccountSpeciality() {
    }

    public AccountSpeciality(int accountSpecialityId) {
        this.accountSpecialityId = accountSpecialityId;
    }

    public AccountSpeciality(int accountSpecialityId, PatientAccount patientAccount, MedicSpeciality medicSpeciality, Timestamp patientAccountSpecialityCreationDate, Timestamp patientAccountSpecialityUseDate, boolean patientAccountSpecialityUsed) {
        this.accountSpecialityId = accountSpecialityId;
        this.patientAccount = patientAccount;
        this.medicSpeciality = medicSpeciality;
        this.patientAccountSpecialityCreationDate = patientAccountSpecialityCreationDate;
        this.patientAccountSpecialityUseDate = patientAccountSpecialityUseDate;
        this.patientAccountSpecialityUsed = patientAccountSpecialityUsed;
    }

    public AccountSpeciality(PatientAccount patientAccount, MedicSpeciality medicSpeciality, Timestamp patientAccountSpecialityCreationDate, Timestamp patientAccountSpecialityUseDate, boolean patientAccountSpecialityUsed) {
        this.patientAccount = patientAccount;
        this.medicSpeciality = medicSpeciality;
        this.patientAccountSpecialityCreationDate = patientAccountSpecialityCreationDate;
        this.patientAccountSpecialityUseDate = patientAccountSpecialityUseDate;
        this.patientAccountSpecialityUsed = patientAccountSpecialityUsed;
    }    

    public int getAccountSpecialityId() {
        return accountSpecialityId;
    }

    public void setAccountSpecialityId(int accountSpecialityId) {
        this.accountSpecialityId = accountSpecialityId;
    }

    public PatientAccount getPatientAccount() {
        return patientAccount;
    }

    public void setPatientAccount(PatientAccount patientAccount) {
        this.patientAccount = patientAccount;
    }

    public MedicSpeciality getMedicSpeciality() {
        return medicSpeciality;
    }

    public void setMedicSpeciality(MedicSpeciality medicSpeciality) {
        this.medicSpeciality = medicSpeciality;
    }

    public Timestamp getPatientAccountSpecialityCreationDate() {
        return patientAccountSpecialityCreationDate;
    }

    public void setPatientAccountSpecialityCreationDate(Timestamp patientAccountSpecialityCreationDate) {
        this.patientAccountSpecialityCreationDate = patientAccountSpecialityCreationDate;
    }

    public Timestamp getPatientAccountSpecialityUseDate() {
        return patientAccountSpecialityUseDate;
    }

    public void setPatientAccountSpecialityUseDate(Timestamp patientAccountSpecialityUseDate) {
        this.patientAccountSpecialityUseDate = patientAccountSpecialityUseDate;
    }    

    public boolean isPatientAccountSpecialityUsed() {
        return patientAccountSpecialityUsed;
    }

    public void setPatientAccountSpecialityUsed(boolean patientAccountSpecialityUsed) {
        this.patientAccountSpecialityUsed = patientAccountSpecialityUsed;
    }        
    
}
