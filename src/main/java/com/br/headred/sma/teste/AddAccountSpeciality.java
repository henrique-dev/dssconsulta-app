/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.ConsultDAO;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.AccountSpeciality;
import com.br.headred.sma.models.MedicProfile;
import com.br.headred.sma.models.MedicSpeciality;
import com.br.headred.sma.models.PatientAccount;
import com.br.headred.sma.models.Speciality;
import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class AddAccountSpeciality {

    public AddAccountSpeciality() {
        try (Connection connection = new ConnectionFactory().getConnection()) {   
            new ConsultDAO(connection).addAccountSpeciality(
                    new AccountSpeciality(
                    new PatientAccount(0, null, null, null),
                    new MedicSpeciality(new MedicProfile(0), new Speciality(0)),
                    new Date(Calendar.getInstance().getTimeInMillis()),
                    null,
                    false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new AddAccountSpeciality();
    }
    
    
    
}
