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
    private float medicProfileEvaluationAvg;
    private int medicProfileEvaluationCount;
    private File file;
    private List<MedicSpeciality> medicSpecialityList;
    private List<MedicConsult> medicConsultList;
    private List<MedicWorkAddress> medicWorkAddressList;
    private Evaluation medicPatientEvaluation;

    public MedicProfile() {
    }

    public MedicProfile(int medicId) {
        super(medicId);
    }

    public MedicProfile(String medicProfileBio, int medicProfileExpAge, String medicProfileInfoCompl, float medicProfileEvaluationAvg, int medicProfileEvaluationCount, File file, List<MedicSpeciality> medicSpecialityList, List<MedicConsult> medicConsultList, List<MedicWorkAddress> medicWorkAddressList, Evaluation medicPatientEvaluation, int medicId) {
        super(medicId);
        this.medicProfileBio = medicProfileBio;
        this.medicProfileExpAge = medicProfileExpAge;
        this.medicProfileInfoCompl = medicProfileInfoCompl;
        this.medicProfileEvaluationAvg = medicProfileEvaluationAvg;
        this.medicProfileEvaluationCount = medicProfileEvaluationCount;
        this.file = file;
        this.medicSpecialityList = medicSpecialityList;
        this.medicConsultList = medicConsultList;
        this.medicWorkAddressList = medicWorkAddressList;
        this.medicPatientEvaluation = medicPatientEvaluation;
    }

    public MedicProfile(String medicProfileBio, int medicProfileExpAge, String medicProfileInfoCompl, float medicProfileEvaluationAvg, int medicProfileEvaluationCount, File file, List<MedicSpeciality> medicSpecialityList, List<MedicConsult> medicConsultList, List<MedicWorkAddress> medicWorkAddressList, Evaluation medicPatientEvaluation) {
        this.medicProfileBio = medicProfileBio;
        this.medicProfileExpAge = medicProfileExpAge;
        this.medicProfileInfoCompl = medicProfileInfoCompl;
        this.medicProfileEvaluationAvg = medicProfileEvaluationAvg;
        this.medicProfileEvaluationCount = medicProfileEvaluationCount;
        this.file = file;
        this.medicSpecialityList = medicSpecialityList;
        this.medicConsultList = medicConsultList;
        this.medicWorkAddressList = medicWorkAddressList;
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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }    

    public List<MedicSpeciality> getMedicSpecialityList() {
        return medicSpecialityList;
    }

    public void setMedicSpecialityList(List<MedicSpeciality> medicSpecialityList) {
        this.medicSpecialityList = medicSpecialityList;
    }

    public List<MedicConsult> getMedicConsultList() {
        return medicConsultList;
    }

    public void setMedicConsultList(List<MedicConsult> medicConsultList) {
        this.medicConsultList = medicConsultList;
    }

    public List<MedicWorkAddress> getMedicWorkAddressList() {
        return medicWorkAddressList;
    }

    public void setMedicWorkAddressList(List<MedicWorkAddress> medicWorkAddressList) {
        this.medicWorkAddressList = medicWorkAddressList;
    }    

    public Evaluation getMedicPatientEvaluation() {
        return medicPatientEvaluation;
    }

    public void setMedicPatientEvaluation(Evaluation medicPatientEvaluation) {
        this.medicPatientEvaluation = medicPatientEvaluation;
    }       

    public float getMedicProfileEvaluationAvg() {
        return medicProfileEvaluationAvg;
    }

    public void setMedicProfileEvaluationAvg(float medicProfileEvaluationAvg) {
        this.medicProfileEvaluationAvg = medicProfileEvaluationAvg;
    }

    public int getMedicProfileEvaluationCount() {
        return medicProfileEvaluationCount;
    }

    public void setMedicProfileEvaluationCount(int medicProfileEvaluationCount) {
        this.medicProfileEvaluationCount = medicProfileEvaluationCount;
    }        
        
}
