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
    private List<AccountSpeciality> accountSpecialities;

    public PatientAccount() {
    }

    public PatientAccount(int patientAccountId) {
        this.patientAccountId = patientAccountId;
    }

    public PatientAccount(int patientAccountId, Date patientAccountCreationDate, List<PatientAccountFile> patientAccountFiles, List<AccountSpeciality> accountSpecialities) {
        this.patientAccountId = patientAccountId;
        this.patientAccountCreationDate = patientAccountCreationDate;
        this.patientAccountFiles = patientAccountFiles;
        this.accountSpecialities = accountSpecialities;
    }

    public PatientAccount(Date patientAccountCreationDate, List<PatientAccountFile> patientAccountFiles, List<AccountSpeciality> accountSpecialities) {
        this.patientAccountCreationDate = patientAccountCreationDate;
        this.patientAccountFiles = patientAccountFiles;
        this.accountSpecialities = accountSpecialities;
    }        

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

    public List<AccountSpeciality> getAccountSpecialities() {
        return accountSpecialities;
    }

    public void setAccountSpecialities(List<AccountSpeciality> accountSpecialities) {
        this.accountSpecialities = accountSpecialities;
    }           
           
}
