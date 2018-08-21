/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.ConsultDAO;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.Consult;
import com.br.headred.sma.models.Medic;
import com.br.headred.sma.models.MedicProfile;
import com.br.headred.sma.models.MedicSpeciality;
import com.br.headred.sma.models.MedicWorkAddress;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.Speciality;
import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class AddConsult {

    public AddConsult() {
        try (Connection connection = new ConnectionFactory().getConnection()) {   
            new ConsultDAO(connection).addConsult(
                    new Consult(
                            new Date(Calendar.getInstance().getTimeInMillis()),
                            new Date(Calendar.getInstance().getTimeInMillis()),
                            false,
                            new MedicSpeciality(new MedicProfile(0), new Speciality(0)),
                            new MedicWorkAddress(0)),
                    new Medic(0),
                    new Patient(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new AddConsult();
    }
    
    
    
}
