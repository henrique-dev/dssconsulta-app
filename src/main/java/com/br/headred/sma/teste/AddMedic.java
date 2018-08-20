/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.MedicDAO;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.Medic;
import com.br.headred.sma.models.MedicProfile;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class AddMedic {
    
    public static void main(String[] args) {
        try (Connection connection = new ConnectionFactory().getConnection()) {           
            
            Medic medic = new Medic();
            
            medic.setUserName("12345");
            medic.setUserPassword("12345");
            
            medic.setMedicName("Fulano Ciclano Beltrano");
            medic.setMedicCrm("123");
            
            MedicProfile medicProfile = new MedicProfile();
            medicProfile.setMedicProfileBio("Eu sou um cara muito bacana");
            medicProfile.setMedicProfileExpAge(18);
            medicProfile.setMedicProfileInfoCompl("Então, sabe como é né");
            
            medic.setMedicProfile(medicProfile);
            
            
            new MedicDAO(connection).addMedic(medic);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
    
}
