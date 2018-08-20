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
public class MedicSpeciality {
    
    private MedicProfile medicProfile;
    private Speciality speciality;

    public MedicSpeciality() {
    }

    public MedicSpeciality(MedicProfile medicProfile) {
        this.medicProfile = medicProfile;
    }

    public MedicSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public MedicSpeciality(MedicProfile medicProfile, Speciality speciality) {
        this.medicProfile = medicProfile;
        this.speciality = speciality;
    }        

    public MedicProfile getMedicProfile() {
        return medicProfile;
    }

    public void setMedicProfile(MedicProfile medicProfile) {
        this.medicProfile = medicProfile;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }        
    
}
