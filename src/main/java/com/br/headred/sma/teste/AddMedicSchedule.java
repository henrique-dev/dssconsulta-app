/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.MedicDAO;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.ClinicProfile;
import com.br.headred.sma.models.MedicProfile;
import com.br.headred.sma.models.MedicSchedule;
import java.sql.Connection;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class AddMedicSchedule {
    
    public static void main(String[] args) {
        try (Connection connection = new ConnectionFactory().getConnection()) {   
            new MedicDAO(connection).addMedicSchedule(
                    new MedicSchedule(new MedicProfile(0), new ClinicProfile(0), "10h - 21h"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
