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
public abstract class User {
    
    private int patientUserId;
    private String patientUserName;
    private String patientUserPassword;
    private String patientUserSessionId;

    public int getPatientUserId() {
        return patientUserId;
    }

    public void setPatientUserId(int patientUserId) {
        this.patientUserId = patientUserId;
    }

    public String getPatientUserName() {
        return patientUserName;
    }

    public void setPatientUserName(String patientUserName) {
        this.patientUserName = patientUserName;
    }

    public String getPatientUserPassword() {
        return patientUserPassword;
    }

    public void setPatientUserPassword(String patientUserPassword) {
        this.patientUserPassword = patientUserPassword;
    }

    public String getPatientUserSessionId() {
        return patientUserSessionId;
    }

    public void setPatientUserSessionId(String patientUserSessionId) {
        this.patientUserSessionId = patientUserSessionId;
    }            
            
}
