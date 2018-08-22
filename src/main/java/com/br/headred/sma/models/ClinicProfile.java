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
public class ClinicProfile extends Clinic {
        
    private String clinicProfileBio;
    private String clinicProfileAddress;
    private List<ClinicTelephone> clicClinicTelephones;    
    private List<MedicWorkAddress> medicWorkAddresses;
    private List<ClinicProfileFile> clinicProfileFiles;

    public ClinicProfile() {
    }

    public ClinicProfile(int clinicId) {
        super(clinicId);
    }        

    public ClinicProfile(String clinicProfileBio, String clinicProfileAddress, List<ClinicTelephone> clicClinicTelephones, List<MedicWorkAddress> medicWorkAddresses, List<ClinicProfileFile> clinicProfileFiles, int clinicId) {
        super(clinicId);
        this.clinicProfileBio = clinicProfileBio;
        this.clinicProfileAddress = clinicProfileAddress;
        this.clicClinicTelephones = clicClinicTelephones;
        this.medicWorkAddresses = medicWorkAddresses;
        this.clinicProfileFiles = clinicProfileFiles;
    }

    public ClinicProfile(String clinicProfileBio, String clinicProfileAddress, List<ClinicTelephone> clicClinicTelephones, List<MedicWorkAddress> medicWorkAddresses, List<ClinicProfileFile> clinicProfileFiles) {
        this.clinicProfileBio = clinicProfileBio;
        this.clinicProfileAddress = clinicProfileAddress;
        this.clicClinicTelephones = clicClinicTelephones;
        this.medicWorkAddresses = medicWorkAddresses;
        this.clinicProfileFiles = clinicProfileFiles;
    }    

    public ClinicProfile(String clinicProfileBio, String clinicProfileAddress, List<ClinicTelephone> clicClinicTelephones) {
        this.clinicProfileBio = clinicProfileBio;
        this.clinicProfileAddress = clinicProfileAddress;
        this.clicClinicTelephones = clicClinicTelephones;
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
