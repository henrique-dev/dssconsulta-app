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
public class PatientProfile {

    private int patientProfileId;
    private String patientProfileEmail;
    private String patientProfileGenre;
    private Date patientProfileBirthDate;
    private float patientProfileHeight;
    private String patientProfileBloodType;
    private String patientProfileTelephone;
    private List<PatientEvaluation> patientEvaluations;
    private List<PatientConsult> patientConsults;
    private PatientAccount patientAccount;
    private List<PatientProfileFile> patientProfileFiles;

    public int getPatientProfileId() {
        return patientProfileId;
    }

    public void setPatientProfileId(int patientProfileId) {
        this.patientProfileId = patientProfileId;
    }        

    public String getPatientProfileEmail() {
        return patientProfileEmail;
    }

    public void setPatientProfileEmail(String patientProfileEmail) {
        this.patientProfileEmail = patientProfileEmail;
    }

    public String getPatientProfileGenre() {
        return patientProfileGenre;
    }

    public void setPatientProfileGenre(String patientProfileGenre) {
        this.patientProfileGenre = patientProfileGenre;
    }

    public Date getPatientProfileBirthDate() {
        return patientProfileBirthDate;
    }

    public void setPatientProfileBirthDate(Date patientProfileBirthDate) {
        this.patientProfileBirthDate = patientProfileBirthDate;
    }

    public float getPatientProfileHeight() {
        return patientProfileHeight;
    }

    public void setPatientProfileHeight(float patientProfileHeight) {
        this.patientProfileHeight = patientProfileHeight;
    }

    public String getPatientProfileBloodType() {
        return patientProfileBloodType;
    }

    public void setPatientProfileBloodType(String patientProfileBloodType) {
        this.patientProfileBloodType = patientProfileBloodType;
    }

    public String getPatientProfileTelephone() {
        return patientProfileTelephone;
    }

    public void setPatientProfileTelephone(String patientProfileTelephone) {
        this.patientProfileTelephone = patientProfileTelephone;
    }

    public List<PatientEvaluation> getPatientEvaluations() {
        return patientEvaluations;
    }

    public void setPatientEvaluations(List<PatientEvaluation> patientEvaluations) {
        this.patientEvaluations = patientEvaluations;
    }

    public List<PatientConsult> getPatientConsults() {
        return patientConsults;
    }

    public void setPatientConsults(List<PatientConsult> patientConsults) {
        this.patientConsults = patientConsults;
    }

    public PatientAccount getPatientAccount() {
        return patientAccount;
    }

    public void setPatientAccount(PatientAccount patientAccount) {
        this.patientAccount = patientAccount;
    }

    public List<PatientProfileFile> getPatientProfileFiles() {
        return patientProfileFiles;
    }

    public void setPatientProfileFiles(List<PatientProfileFile> patientProfileFiles) {
        this.patientProfileFiles = patientProfileFiles;
    }        
                   
}
