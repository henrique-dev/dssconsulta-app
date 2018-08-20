/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import com.br.headred.sma.dao.SystemDAO;
import com.br.headred.sma.jdbc.ConnectionFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class SolicitarIndex {
    
    public static void main(String[] args) {
        long index = new SystemDAO(new ConnectionFactory().getConnection()).getNextId(SystemDAO.Table.managerUser);
        JOptionPane.showMessageDialog(null, "Novo index recebido: " + index);
    }
    
}
