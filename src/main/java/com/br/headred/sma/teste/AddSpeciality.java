/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.MedicDAO;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.Speciality;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class AddSpeciality {

    public AddSpeciality() {
        try (Connection connection = new ConnectionFactory().getConnection()) {
            new MedicDAO(connection).addSpeciality(new Speciality("Clinico geral", false));
            new MedicDAO(connection).addSpeciality(new Speciality("Cardiologista", true));
            new MedicDAO(connection).addSpeciality(new Speciality("Nutricionista", true));
            new MedicDAO(connection).addSpeciality(new Speciality("Pediatra", true));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
    
    

    public static void main(String[] args) {
        new AddSpeciality();
    }

}
