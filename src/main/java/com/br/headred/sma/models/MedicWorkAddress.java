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
    private MedicSpeciality medicSpeciality;
    private ClinicProfile clinicProfile;
    private String medicWorkAddressComplement;
    private MedicWorkScheduling medicWorkScheduling;

    public MedicWorkAddress() {
    }
    
    public MedicWorkAddress(int medicWorkAddressId) {
        this.medicWorkAddressId = medicWorkAddressId;
    }   

    public MedicWorkAddress(int medicWorkAddressId, MedicSpeciality medicSpeciality, ClinicProfile clinicProfile, String medicWorkAddressComplement, MedicWorkScheduling medicWorkScheduling) {
        this.medicWorkAddressId = medicWorkAddressId;
        this.medicSpeciality = medicSpeciality;
        this.clinicProfile = clinicProfile;
        this.medicWorkAddressComplement = medicWorkAddressComplement;
        this.medicWorkScheduling = medicWorkScheduling;
    }

    public MedicWorkAddress(MedicSpeciality medicSpeciality, ClinicProfile clinicProfile, String medicWorkAddressComplement, MedicWorkScheduling medicWorkScheduling) {
        this.medicSpeciality = medicSpeciality;
        this.clinicProfile = clinicProfile;
        this.medicWorkAddressComplement = medicWorkAddressComplement;
        this.medicWorkScheduling = medicWorkScheduling;
    }        

    public MedicSpeciality getMedicSpeciality() {
        return medicSpeciality;
    }

    public void setMedicSpeciality(MedicSpeciality medicSpeciality) {
        this.medicSpeciality = medicSpeciality;
    }           

    public int getMedicWorkAddressId() {
        return medicWorkAddressId;
    }

    public void setMedicWorkAddressId(int medicWorkAddressId) {
        this.medicWorkAddressId = medicWorkAddressId;
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

    public MedicWorkScheduling getMedicWorkScheduling() {
        return medicWorkScheduling;
    }

    public void setMedicWorkScheduling(MedicWorkScheduling medicWorkScheduling) {
        this.medicWorkScheduling = medicWorkScheduling;
    }        
    
}
