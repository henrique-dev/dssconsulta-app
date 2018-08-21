/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.ClinicDAO;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.Clinic;
import com.br.headred.sma.models.ClinicProfile;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class AddClinic {
    
    public AddClinic() {
        try (Connection connection = new ConnectionFactory().getConnection()) {   
            Clinic clinic = new Clinic();
            clinic.setClinicCnpj("123456789");
            clinic.setClinicName("Hospital Geral");
            
            ClinicProfile clinicProfile = new ClinicProfile();
            clinicProfile.setClinicProfileBio("Um hospital muito bonito");
            clinicProfile.setClinicProfileAddress("Avenida Brasil");
            
            clinic.setClinicProfile(clinicProfile);
            
            new ClinicDAO(connection).addClinic(clinic);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new AddMedic();
    }
    
}
