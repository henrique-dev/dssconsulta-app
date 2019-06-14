/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class ConnectionFactory {        
    
    public Connection getConnection() {
        try {
            //String url = "jdbc:mysql://192.168.2.148:3306/smc?useTimezone=true&serverTimezone=UTC";
            String url = "jdbc:mysql://localhost:3306/smc?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "root";
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void disconnect(Connection con) {
        try {
            con.close();            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
