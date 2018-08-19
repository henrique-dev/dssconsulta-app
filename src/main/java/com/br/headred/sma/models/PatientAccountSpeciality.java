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
public class PatientAccountSpeciality {
    
    private long patientAccountSpecialityId;
    private PatientAccount patientAccount;
    private MedicSpeciality medicSpeciality;
    private Date patientAccountSpecialityCreationDate;
    private Date patientAccountSpecialityUseDate;
    private boolean patientAccountSpecialityUsed;

    public long getPatientAccountSpecialityId() {
        return patientAccountSpecialityId;
    }

    public void setPatientAccountSpecialityId(long patientAccountSpecialityId) {
        this.patientAccountSpecialityId = patientAccountSpecialityId;
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
