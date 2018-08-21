/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.MedicDAO;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.MedicWorkAddress;
import java.sql.Connection;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class RemoveMedicWorkAddress {

    public RemoveMedicWorkAddress() {
        try (Connection connection = new ConnectionFactory().getConnection()) {   
            new MedicDAO(connection).removeMedicWorkAddressAndScheduling(new MedicWorkAddress(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public static void main(String[] args) {
        new RemoveMedicWorkAddress();
    }
    
}
