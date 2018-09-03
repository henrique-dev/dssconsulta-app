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
import com.br.headred.sma.models.MedicSpeciality;
import com.br.headred.sma.models.MedicWorkAddress;
import com.br.headred.sma.models.MedicWorkScheduling;
import com.br.headred.sma.models.Speciality;
import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class AddMedicWorkAddress {

    public AddMedicWorkAddress() {
        try (Connection connection = new ConnectionFactory().getConnection()) {
            new MedicDAO(connection).addMedicWorkAddressAndScheduling(
                    new MedicWorkAddress(
                            new MedicSpeciality(new MedicProfile(0), new Speciality(2)),
                            new ClinicProfile(0), 
                            "Sala 205", null), 
                    new MedicWorkScheduling(
                            10, 
                            new Date(Calendar.getInstance().getTimeInMillis()), 
                            0,
                            "13h ~ 18h",
                            "0101010"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    public static void main(String[] args) {
        new AddMedicWorkAddress();
    }

}
