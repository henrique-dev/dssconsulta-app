/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.models;

import java.util.List;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class ClinicProfile {
    
    private int clinicProfileId;
    private String clinicProfileBio;
    private String clinicProfileAddress;
    private List<ClinicTelephone> clicClinicTelephones;
    private List<MedicSchedule> medicSchedules;
    private List<MedicWorkAddress> medicWorkAddresses;
    private List<ClinicProfileFile> clinicProfileFiles;

    public int getClinicProfileId() {
        return clinicProfileId;
    }

    public void setClinicProfileId(int clinicProfileId) {
        this.clinicProfileId = clinicProfileId;
    }        

    public String getClinicProfileBio() {
        return clinicProfileBio;
    }

    public void setClinicProfileBio(String clinicProfileBio) {
        this.clinicProfileBio = clinicProfileBio;
    }

    public String getClinicProfileAddress() {
        return clinicProfileAddress;
    }

    public void setClinicProfileAddress(String clinicProfileAddress) {
        this.clinicProfileAddress = clinicProfileAddress;
    }        

    public List<ClinicTelephone> getClicClinicTelephones() {
        return clicClinicTelephones;
    }

    public void setClicClinicTelephones(List<ClinicTelephone> clicClinicTelephones) {
        this.clicClinicTelephones = clicClinicTelephones;
    }

    public List<MedicSchedule> getMedicSchedules() {
        return medicSchedules;
    }

    public void setMedicSchedules(List<MedicSchedule> medicSchedules) {
        this.medicSchedules = medicSchedules;
    }

    public List<MedicWorkAddress> getMedicWorkAddresses() {
        return medicWorkAddresses;
    }

    public void setMedicWorkAddresses(List<MedicWorkAddress> medicWorkAddresses) {
        this.medicWorkAddresses = medicWorkAddresses;
    }

    public List<ClinicProfileFile> getClinicProfileFiles() {
        return clinicProfileFiles;
    }

    public void setClinicProfileFiles(List<ClinicProfileFile> clinicProfileFiles) {
        this.clinicProfileFiles = clinicProfileFiles;
    }        
    
}
