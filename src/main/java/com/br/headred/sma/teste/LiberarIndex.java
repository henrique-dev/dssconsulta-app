/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.SystemDAO;
import com.br.headred.sma.dao.SystemDAO.Table;
import com.br.headred.sma.jdbc.ConnectionFactory;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class LiberarIndex {
    
    public static void main(String[] args) {
        new SystemDAO(new ConnectionFactory().getConnection()).releaseId(Table.managerUser, 3);
    }
    
}
