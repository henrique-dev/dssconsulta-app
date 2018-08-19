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
public class ClinicTelephone {
    
    private ClinicProfile clinicProfile;
    private String clinicTelephoneNumber;

    public ClinicProfile getClinicProfile() {
        return clinicProfile;
    }

    public void setClinicProfile(ClinicProfile clinicProfile) {
        this.clinicProfile = clinicProfile;
    }

    public String getClinicTelephoneNumber() {
        return clinicTelephoneNumber;
    }

    public void setClinicTelephoneNumber(String clinicTelephoneNumber) {
        this.clinicTelephoneNumber = clinicTelephoneNumber;
    }        
    
}
