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
public class PatientProfile extends Patient {
    
    private String patientProfileEmail;
    private String patientProfileGenre;
    private Date patientProfileBirthDate;
    private float patientProfileHeight;
    private float patientProfileWeight;
    private String patientProfileBloodType;
    private String patientProfileTelephone;    
    private PatientAccount patientAccount;
    private List<PatientProfileFile> patientProfileFiles;

    public PatientProfile() {
    }

    public PatientProfile(int userId) {
        super(userId);
    }

    public PatientProfile(String patientProfileEmail, String patientProfileGenre, Date patientProfileBirthDate, float patientProfileHeight, float patientProfileWeight, String patientProfileBloodType, String patientProfileTelephone, PatientAccount patientAccount, List<PatientProfileFile> patientProfileFiles, int userId) {
        super(userId);
        this.patientProfileEmail = patientProfileEmail;
        this.patientProfileGenre = patientProfileGenre;
        this.patientProfileBirthDate = patientProfileBirthDate;
        this.patientProfileHeight = patientProfileHeight;
        this.patientProfileWeight = patientProfileWeight;
        this.patientProfileBloodType = patientProfileBloodType;
        this.patientProfileTelephone = patientProfileTelephone;
        this.patientAccount = patientAccount;
        this.patientProfileFiles = patientProfileFiles;
    }

    public PatientProfile(String patientProfileEmail, String patientProfileGenre, Date patientProfileBirthDate, float patientProfileHeight, float patientProfileWeight, String patientProfileBloodType, String patientProfileTelephone, PatientAccount patientAccount, List<PatientProfileFile> patientProfileFiles) {
        this.patientProfileEmail = patientProfileEmail;
        this.patientProfileGenre = patientProfileGenre;
        this.patientProfileBirthDate = patientProfileBirthDate;
        this.patientProfileHeight = patientProfileHeight;
        this.patientProfileWeight = patientProfileWeight;
        this.patientProfileBloodType = patientProfileBloodType;
        this.patientProfileTelephone = patientProfileTelephone;
        this.patientAccount = patientAccount;
        this.patientProfileFiles = patientProfileFiles;
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

    public float getPatientProfileWeight() {
        return patientProfileWeight;
    }

    public void setPatientProfileWeight(float patientProfileWeight) {
        this.patientProfileWeight = patientProfileWeight;
    }        
                   
}
