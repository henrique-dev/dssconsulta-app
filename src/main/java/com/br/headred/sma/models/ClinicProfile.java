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
    private List<ClinicTelephone> clinicTelephoneList;    
    private List<MedicWorkAddress> medicWorkAddressList;
    private List<ClinicProfileFile> clinicProfileFileList;

    public ClinicProfile() {
    }

    public ClinicProfile(int clinicId) {
        super(clinicId);
    }        

    public ClinicProfile(String clinicProfileBio, String clinicProfileAddress, List<ClinicTelephone> clicClinicTelephones, List<MedicWorkAddress> medicWorkAddresses, List<ClinicProfileFile> clinicProfileFiles, int clinicId) {
        super(clinicId);
        this.clinicProfileBio = clinicProfileBio;
        this.clinicProfileAddress = clinicProfileAddress;
        this.clinicTelephoneList = clicClinicTelephones;
        this.medicWorkAddressList = medicWorkAddresses;
        this.clinicProfileFileList = clinicProfileFiles;
    }

    public ClinicProfile(String clinicProfileBio, String clinicProfileAddress, List<ClinicTelephone> clicClinicTelephones, List<MedicWorkAddress> medicWorkAddresses, List<ClinicProfileFile> clinicProfileFiles) {
        this.clinicProfileBio = clinicProfileBio;
        this.clinicProfileAddress = clinicProfileAddress;
        this.clinicTelephoneList = clicClinicTelephones;
        this.medicWorkAddressList = medicWorkAddresses;
        this.clinicProfileFileList = clinicProfileFiles;
    }    

    public ClinicProfile(String clinicProfileBio, String clinicProfileAddress, List<ClinicTelephone> clicClinicTelephones) {
        this.clinicProfileBio = clinicProfileBio;
        this.clinicProfileAddress = clinicProfileAddress;
        this.clinicTelephoneList = clicClinicTelephones;
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

    public List<ClinicTelephone> getClinicTelephoneList() {
        return clinicTelephoneList;
    }

    public void setClinicTelephoneList(List<ClinicTelephone> clicClinicTelephones) {
        this.clinicTelephoneList = clicClinicTelephones;
    }   

    public List<MedicWorkAddress> getMedicWorkAddressList() {
        return medicWorkAddressList;
    }

    public void setMedicWorkAddressList(List<MedicWorkAddress> medicWorkAddresses) {
        this.medicWorkAddressList = medicWorkAddresses;
    }

    public List<ClinicProfileFile> getClinicProfileFileList() {
        return clinicProfileFileList;
    }

    public void setClinicProfileFileList(List<ClinicProfileFile> clinicProfileFiles) {
        this.clinicProfileFileList = clinicProfileFiles;
    }        
    
}
