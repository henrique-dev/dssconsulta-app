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
public class Clinic {
    
    private int id;
    private String clinicName;
    private String clinicCnpj;
    private ClinicProfile clinicProfile;
    
    public Clinic() {}
    
    public Clinic(int clinicId) {
        this.id = clinicId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getClinicCnpj() {
        return clinicCnpj;
    }

    public void setClinicCnpj(String clinicCnpj) {
        this.clinicCnpj = clinicCnpj;
    }        

    public ClinicProfile getClinicProfile() {
        return clinicProfile;
    }

    public void setClinicProfile(ClinicProfile clinicProfile) {
        this.clinicProfile = clinicProfile;
    }        
    
}
