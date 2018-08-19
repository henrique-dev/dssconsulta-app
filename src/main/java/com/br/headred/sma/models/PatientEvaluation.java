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
public class PatientEvaluation {
       
    private long patientEvaluationId;
    private MedicProfile medicProfile;
    private PatientProfile patientProfile;
    private String patientEvaluationDescName;
    private String patientEvaluationDescInfo;
    private int patientEvaluationScore;

    public long getPatientEvaluationId() {
        return patientEvaluationId;
    }

    public void setPatientEvaluationId(long patientEvaluationId) {
        this.patientEvaluationId = patientEvaluationId;
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

    public String getPatientEvaluationDescName() {
        return patientEvaluationDescName;
    }

    public void setPatientEvaluationDescName(String patientEvaluationDescName) {
        this.patientEvaluationDescName = patientEvaluationDescName;
    }

    public String getPatientEvaluationDescInfo() {
        return patientEvaluationDescInfo;
    }

    public void setPatientEvaluationDescInfo(String patientEvaluationDescInfo) {
        this.patientEvaluationDescInfo = patientEvaluationDescInfo;
    }

    public int getPatientEvaluationScore() {
        return patientEvaluationScore;
    }

    public void setPatientEvaluationScore(int patientEvaluationScore) {
        this.patientEvaluationScore = patientEvaluationScore;
    }        
    
}
