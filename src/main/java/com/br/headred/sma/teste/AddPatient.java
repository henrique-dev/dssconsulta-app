/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.PatientDAO;
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

    /**
     * Creating a full patient
     * @param args 
     */
    public static void main(String[] args) {
        try (Connection connection = new ConnectionFactory().getConnection()) {
            User patientUser = new Patient();
            patientUser.setPatientUserName("12345678910");
            patientUser.setPatientUserPassword(Objects.hashCode("123456") + "");
            new PatientDAO(connection).addPatientUser(patientUser);  
            
            Patient patient = new Patient();
            patient.setPatientUserId(patientUser.getPatientUserId());
            patient.setPatientName("Paulo Henrique Gonçalves Bacelar");
            patient.setPatientCpf("01741053200");
            new PatientDAO(connection).addPatient(patient);
            
            PatientProfile patientProfile = new PatientProfile();
            patientProfile.setPatientUserId(patientUser.getPatientUserId());
            patientProfile.setPatientProfileEmail("henrique.phgb@gmail.com");
            patientProfile.setPatientProfileGenre("masculino");
            patientProfile.setPatientProfileHeight(1.7f);
            patientProfile.setPatientProfileBirthDate(new Date(Calendar.getInstance().getTimeInMillis()));
            patientProfile.setPatientProfileBloodType("A+");
            patientProfile.setPatientProfileTelephone("96991100443");
            new PatientDAO(connection).addPatientProfile(patientProfile);
            
            PatientAccount patientAccount = new PatientAccount();
            patientAccount.setPatientProfileId(patientProfile.getPatientUserId());
            patientAccount.setPatientAccountCreationDate(new Date(Calendar.getInstance().getTimeInMillis()));
            new PatientDAO(connection).addPatientAccount(patientAccount);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
