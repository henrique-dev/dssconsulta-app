/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.models;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class Evaluation {
       
    private int evaluationId;
    private MedicProfile medicProfile;
    private PatientProfile patientProfile;
    private String evaluationDescName;
    private String evaluationDescInfo;
    private int evaluationScore;   

    public Evaluation() {
    }

    public Evaluation(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public Evaluation(int evaluationId, MedicProfile medicProfile, PatientProfile patientProfile, String evaluationDescName, String evaluationDescInfo, int evaluationScore) {
        this.evaluationId = evaluationId;
        this.medicProfile = medicProfile;
        this.patientProfile = patientProfile;
        this.evaluationDescName = evaluationDescName;
        this.evaluationDescInfo = evaluationDescInfo;
        this.evaluationScore = evaluationScore;
    }

    public Evaluation(MedicProfile medicProfile, PatientProfile patientProfile, String evaluationDescName, String evaluationDescInfo, int evaluationScore) {
        this.medicProfile = medicProfile;
        this.patientProfile = patientProfile;
        this.evaluationDescName = evaluationDescName;
        this.evaluationDescInfo = evaluationDescInfo;
        this.evaluationScore = evaluationScore;
    }

    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public MedicProfile getMedicProfile() {
        return medicProfile;
    }

    public void setMedicProfile(MedicProfile medicProfile) {
        this.medicProfile = medicProfile;
    }

    public PatientProfile getPatientProfile() {
        return patientProfile;
    }

    public void setPatientProfile(PatientProfile patientProfile) {
        this.patientProfile = patientProfile;
    }

    public String getEvaluationDescName() {
        return evaluationDescName;
    }

    public void setEvaluationDescName(String evaluationDescName) {
        this.evaluationDescName = evaluationDescName;
    }

    public String getEvaluationDescInfo() {
        return evaluationDescInfo;
    }

    public void setEvaluationDescInfo(String evaluationDescInfo) {
        this.evaluationDescInfo = evaluationDescInfo;
    }

    public int getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(int evaluationScore) {
        this.evaluationScore = evaluationScore;
    }        
    
}
