/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.models;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class PatientAccount {
       
    private int patientAccountId;
    private Date patientAccountCreationDate;   
    private List<PatientAccountFile> patientAccountFiles;
    private List<PatientAccountSpeciality> patientAccountSpecialities;

    public int getPatientAccountId() {
        return patientAccountId;
    }

    public void setPatientAccountId(int patientAccountId) {
        this.patientAccountId = patientAccountId;
    }        

    public Date getPatientAccountCreationDate() {
        return patientAccountCreationDate;
    }

    public void setPatientAccountCreationDate(Date PatientAccountCreationDate) {
        this.patientAccountCreationDate = PatientAccountCreationDate;
    }    

    public List<PatientAccountFile> getPatientAccountFiles() {
        return patientAccountFiles;
    }

    public void setPatientAccountFiles(List<PatientAccountFile> patientAccountFiles) {
        this.patientAccountFiles = patientAccountFiles;
    }

    public List<PatientAccountSpeciality> getPatientAccountSpecialities() {
        return patientAccountSpecialities;
    }

    public void setPatientAccountSpecialities(List<PatientAccountSpeciality> patientAccountSpecialities) {
        this.patientAccountSpecialities = patientAccountSpecialities;
    }        
           
}
