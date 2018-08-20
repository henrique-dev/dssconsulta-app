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
public class MedicWorkAddress {
    
    private int medicWorkAddressId;
    private MedicProfile medicProfile;
    private ClinicProfile clinicProfile;
    private String medicWorkAddressComplement;

    public MedicWorkAddress() {
    }

    public MedicWorkAddress(int medicWorkAddressId) {
        this.medicWorkAddressId = medicWorkAddressId;
    }

    public MedicWorkAddress(int medicWorkAddressId, MedicProfile medicProfile, ClinicProfile clinicProfile, String medicWorkAddressComplement) {
        this.medicWorkAddressId = medicWorkAddressId;
        this.medicProfile = medicProfile;
        this.clinicProfile = clinicProfile;
        this.medicWorkAddressComplement = medicWorkAddressComplement;
    }

    public MedicWorkAddress(MedicProfile medicProfile, ClinicProfile clinicProfile, String medicWorkAddressComplement) {
        this.medicProfile = medicProfile;
        this.clinicProfile = clinicProfile;
        this.medicWorkAddressComplement = medicWorkAddressComplement;
    }        

    public int getMedicWorkAddressId() {
        return medicWorkAddressId;
    }

    public void setMedicWorkAddressId(int medicWorkAddressId) {
        this.medicWorkAddressId = medicWorkAddressId;
    }

    public MedicProfile getMedicProfile() {
        return medicProfile;
    }

    public void setMedicProfile(MedicProfile medicProfile) {
        this.medicProfile = medicProfile;
    }

    public ClinicProfile getClinicProfile() {
        return clinicProfile;
    }

    public void setClinicProfile(ClinicProfile clinicProfile) {
        this.clinicProfile = clinicProfile;
    }

    public String getMedicWorkAddressComplement() {
        return medicWorkAddressComplement;
    }

    public void setMedicWorkAddressComplement(String medicWorkAddressComplement) {
        this.medicWorkAddressComplement = medicWorkAddressComplement;
    }        
    
}
