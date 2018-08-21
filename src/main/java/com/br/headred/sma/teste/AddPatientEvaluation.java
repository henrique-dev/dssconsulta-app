/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.PatientDAO;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.MedicProfile;
import com.br.headred.sma.models.PatientEvaluation;
import com.br.headred.sma.models.PatientProfile;
import java.sql.Connection;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class AddPatientEvaluation {
    
    public static void main(String[] args) {
        try (Connection connection = new ConnectionFactory().getConnection()) {   
            new PatientDAO(connection).addPatientEvaluation(
                    new PatientEvaluation(new MedicProfile(0), new PatientProfile(0), "Muito bom", "Ele me atendeu super gentilmente", 5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
