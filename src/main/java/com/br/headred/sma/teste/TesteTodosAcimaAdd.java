/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class TesteTodosAcimaAdd {

    public TesteTodosAcimaAdd() {
        new AddMedic();
        new AddClinic();
        new AddPatient();
        new AddSpeciality();
        new AddMedicSpeciality();
        new AddMedicWorkAddress();
        //new AddPatientEvaluation();
    }
    
    
    
    public static void main(String[] args) {        
        new TesteTodosAcimaAdd();
    }
    
}
