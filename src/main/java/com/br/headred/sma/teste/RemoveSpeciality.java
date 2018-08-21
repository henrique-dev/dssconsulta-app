/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.MedicDAO;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.Speciality;
import java.sql.Connection;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class RemoveSpeciality {

    public RemoveSpeciality() {
        try (Connection connection = new ConnectionFactory().getConnection()) {   
            new MedicDAO(connection).removeSpeciality(new Speciality(0));
            new MedicDAO(connection).removeSpeciality(new Speciality(1));
            new MedicDAO(connection).removeSpeciality(new Speciality(2));
            new MedicDAO(connection).removeSpeciality(new Speciality(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public static void main(String[] args) {
        new RemoveSpeciality();
    }
    
}
