/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.PatientDAO;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import javax.print.attribute.HashAttributeSet;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class AddPatient {

    public static void main(String[] args) {
        try (Connection connection = new ConnectionFactory().getConnection()) {
            User patientUser = new Patient();
            patientUser.setPatientUserName("12345678910");
            patientUser.setPatientUserPassword(Objects.hashCode("123456") + "");
            new PatientDAO(connection).addPatientUser(patientUser);            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
