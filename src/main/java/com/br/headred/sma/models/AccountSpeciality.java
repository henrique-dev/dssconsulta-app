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
public class AccountSpeciality {
    
    private int accountSpecialityId;
    private PatientAccount patientAccount;
    private MedicSpeciality medicSpeciality;
    private Date patientAccountSpecialityCreationDate;
    private Date patientAccountSpecialityUseDate;
    private boolean patientAccountSpecialityUsed;

    public AccountSpeciality() {
    }

    public AccountSpeciality(int accountSpecialityId) {
        this.accountSpecialityId = accountSpecialityId;
    }

    public AccountSpeciality(int accountSpecialityId, PatientAccount patientAccount, MedicSpeciality medicSpeciality, Date patientAccountSpecialityCreationDate, Date patientAccountSpecialityUseDate, boolean patientAccountSpecialityUsed) {
        this.accountSpecialityId = accountSpecialityId;
        this.patientAccount = patientAccount;
        this.medicSpeciality = medicSpeciality;
        this.patientAccountSpecialityCreationDate = patientAccountSpecialityCreationDate;
        this.patientAccountSpecialityUseDate = patientAccountSpecialityUseDate;
        this.patientAccountSpecialityUsed = patientAccountSpecialityUsed;
    }

    public AccountSpeciality(PatientAccount patientAccount, MedicSpeciality medicSpeciality, Date patientAccountSpecialityCreationDate, Date patientAccountSpecialityUseDate, boolean patientAccountSpecialityUsed) {
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

    public Date getPatientAccountSpecialityCreationDate() {
        return patientAccountSpecialityCreationDate;
    }

    public void setPatientAccountSpecialityCreationDate(Date patientAccountSpecialityCreationDate) {
        this.patientAccountSpecialityCreationDate = patientAccountSpecialityCreationDate;
    }

    public Date getPatientAccountSpecialityUseDate() {
        return patientAccountSpecialityUseDate;
    }

    public void setPatientAccountSpecialityUseDate(Date patientAccountSpecialityUseDate) {
        this.patientAccountSpecialityUseDate = patientAccountSpecialityUseDate;
    }

    public boolean isPatientAccountSpecialityUsed() {
        return patientAccountSpecialityUsed;
    }

    public void setPatientAccountSpecialityUsed(boolean patientAccountSpecialityUsed) {
        this.patientAccountSpecialityUsed = patientAccountSpecialityUsed;
    }        
    
}
