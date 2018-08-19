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
    
    private long medicWorkAddress;
    private MedicProfile medicProfile;
    private ClinicProfile clinicProfile;
    private String medicWorkAddressComplement;

    public long getMedicWorkAddress() {
        return medicWorkAddress;
    }

    public void setMedicWorkAddress(long medicWorkAddress) {
        this.medicWorkAddress = medicWorkAddress;
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
