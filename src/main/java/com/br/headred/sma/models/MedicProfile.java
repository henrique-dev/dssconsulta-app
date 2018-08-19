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
    private List<MedicSchedule> medicSchedules;
    private List<MedicProfileFile> medicProfileFiles;
    private List<MedicSpeciality> medicSpecialities;
    private List<MedicConsult> medicAgenda;
    private MedicWorkAddress medicWorkAddress;
    private PatientEvaluation medicPatientEvaluation;        
        
}
