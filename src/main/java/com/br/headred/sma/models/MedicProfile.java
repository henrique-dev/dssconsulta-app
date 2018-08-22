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
public class MedicProfile extends Medic {
    
    private String medicProfileBio;
    private int medicProfileExpAge;
    private String medicProfileInfoCompl;       
    private List<MedicProfileFile> medicProfileFiles;
    private List<MedicSpeciality> medicSpecialities;
    private List<MedicConsult> medicAgenda;
    private List<MedicWorkAddress> medicWorkAddresses;
    private Evaluation medicPatientEvaluation;

    public MedicProfile() {
    }

    public MedicProfile(int medicId) {
        super(medicId);
    }

    public MedicProfile(String medicProfileBio, int medicProfileExpAge, String medicProfileInfoCompl, List<MedicProfileFile> medicProfileFiles, List<MedicSpeciality> medicSpecialities, List<MedicConsult> medicAgenda, List<MedicWorkAddress> medicWorkAddresses, Evaluation medicPatientEvaluation, int medicId) {
        super(medicId);
        this.medicProfileBio = medicProfileBio;
        this.medicProfileExpAge = medicProfileExpAge;
        this.medicProfileInfoCompl = medicProfileInfoCompl;
        this.medicProfileFiles = medicProfileFiles;
        this.medicSpecialities = medicSpecialities;
        this.medicAgenda = medicAgenda;
        this.medicWorkAddresses = medicWorkAddresses;
        this.medicPatientEvaluation = medicPatientEvaluation;
    }

    public MedicProfile(String medicProfileBio, int medicProfileExpAge, String medicProfileInfoCompl, List<MedicProfileFile> medicProfileFiles, List<MedicSpeciality> medicSpecialities, List<MedicConsult> medicAgenda, List<MedicWorkAddress> medicWorkAddresses, Evaluation medicPatientEvaluation) {
        this.medicProfileBio = medicProfileBio;
        this.medicProfileExpAge = medicProfileExpAge;
        this.medicProfileInfoCompl = medicProfileInfoCompl;
        this.medicProfileFiles = medicProfileFiles;
        this.medicSpecialities = medicSpecialities;
        this.medicAgenda = medicAgenda;
        this.medicWorkAddresses = medicWorkAddresses;
        this.medicPatientEvaluation = medicPatientEvaluation;
    }    

    public String getMedicProfileBio() {
        return medicProfileBio;
    }

    public void setMedicProfileBio(String medicProfileBio) {
        this.medicProfileBio = medicProfileBio;
    }

    public int getMedicProfileExpAge() {
        return medicProfileExpAge;
    }

    public void setMedicProfileExpAge(int medicProfileExpAge) {
        this.medicProfileExpAge = medicProfileExpAge;
    }

    public String getMedicProfileInfoCompl() {
        return medicProfileInfoCompl;
    }

    public void setMedicProfileInfoCompl(String medicProfileInfoCompl) {
        this.medicProfileInfoCompl = medicProfileInfoCompl;
    }    

    public List<MedicProfileFile> getMedicProfileFiles() {
        return medicProfileFiles;
    }

    public void setMedicProfileFiles(List<MedicProfileFile> medicProfileFiles) {
        this.medicProfileFiles = medicProfileFiles;
    }

    public List<MedicSpeciality> getMedicSpecialities() {
        return medicSpecialities;
    }

    public void setMedicSpecialities(List<MedicSpeciality> medicSpecialities) {
        this.medicSpecialities = medicSpecialities;
    }

    public List<MedicConsult> getMedicAgenda() {
        return medicAgenda;
    }

    public void setMedicAgenda(List<MedicConsult> medicAgenda) {
        this.medicAgenda = medicAgenda;
    }

    public List<MedicWorkAddress> getMedicWorkAddresses() {
        return medicWorkAddresses;
    }

    public void setMedicWorkAddresses(List<MedicWorkAddress> medicWorkAddresses) {
        this.medicWorkAddresses = medicWorkAddresses;
    }   

    public Evaluation getMedicPatientEvaluation() {
        return medicPatientEvaluation;
    }

    public void setMedicPatientEvaluation(Evaluation medicPatientEvaluation) {
        this.medicPatientEvaluation = medicPatientEvaluation;
    }        
        
}
