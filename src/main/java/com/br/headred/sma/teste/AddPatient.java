/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.PatientDAO;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.PatientAccount;
import com.br.headred.sma.models.PatientProfile;
import com.br.headred.sma.models.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Objects;
import javax.print.attribute.HashAttributeSet;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class AddPatient {

    public AddPatient() {
        try (Connection connection = new ConnectionFactory().getConnection()) {                        
            
            PatientProfile patient = new PatientProfile();
            
            patient.setUserName("01741053200");
            patient.setUserPassword("12345");            
                                    
            patient.setPatientName("Paulo Henrique Gonçalves Bacelar");
            patient.setPatientCpf("01741053200");
                                                
            patient.setPatientProfileEmail("henrique.phgb@gmail.com");
            patient.setPatientProfileGenre("masculino");
            patient.setPatientProfileHeight(1.7f);
            patient.setPatientProfileBirthDate(new Date(Calendar.getInstance().getTimeInMillis()));
            patient.setPatientProfileBloodType("A+");
            patient.setPatientProfileTelephone("96991100443");                                    
            
            new PatientDAO(connection).addPatient(patient);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    
    
    public static void main(String[] args) {
        new AddPatient();
    }

}
