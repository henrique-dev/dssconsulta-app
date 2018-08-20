/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.dao;

import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class SystemDAO extends BasicDAO {

    public enum Table {
        managerUser,
        medicUser,
        medicSchedule,
        clinic,
        speciality,
        medicWorkAddress,
        patientEvaluation,
        medicConsult,
        patientConsult,
        patientAccountSpeciality,
        file,
        consult,
        patientUser
    };

    public SystemDAO(Connection connection) {
        super(connection);
    }

    public int getNextId(Table tableName) {
        String sql = "select " + tableName + " from idManager where id=(select " + tableName + "Last from idManager where id=0)"
                + "union all select " + tableName + "Last from idManager where id=0";
        try {
            PreparedStatement stmt = super.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.absolute(1);
            int index = rs.getInt(tableName + "");
            rs.absolute(2);
            int indexLast = rs.getInt(tableName + "");
            stmt.close();
            if (indexLast == 0) {                
                sql = "update idManager set " + tableName + "=" + (index + 1) + " where id=0";
                stmt = super.connection.prepareStatement(sql);
                stmt.execute();
                stmt.close();
                return index;
            } else {
                stmt.close();
                sql = "update idManager set " + tableName + "=" + null + " where id=" + indexLast;
                stmt = super.connection.prepareStatement(sql);
                stmt.execute();
                stmt.close();
                sql = "update idManager set " + tableName + "Last=" + (indexLast - 1) + " where id=" + 0;
                stmt = super.connection.prepareStatement(sql);
                stmt.execute();
                stmt.close();
                return index;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void releaseId(Table tableName, long id) {
        String sql = "select rowSize from idManager where id=0 union all "
                + "select " + tableName + "Last from idManager where id=0";
        try {
            PreparedStatement stmt = super.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.absolute(1);
            long rowSize = rs.getInt("rowSize");
            rs.absolute(2);
            long indexLast = rs.getInt("rowSize");
            stmt.close();

            if (indexLast == rowSize-1) {
                sql = "insert into idManager (id, " + tableName + ") values (" + rowSize + ", " + id + ")";
                stmt = super.connection.prepareStatement(sql);
                stmt.execute();
                stmt.close();
                sql = "update idManager set rowSize=" + (rowSize + 1) + "," + tableName + "Last=" + rowSize + " where id=0";
                stmt = super.connection.prepareStatement(sql);
                stmt.execute();
                stmt.close();
            } else {
                sql = "update idManager set " + tableName + "=" + id + " where id=" + (indexLast+1);
                stmt = super.connection.prepareStatement(sql);
                stmt.execute();
                stmt.close();
                sql = "update idManager set " + tableName + "Last=" + (indexLast+1) + " where id=0";
                stmt = super.connection.prepareStatement(sql);
                stmt.execute();
                stmt.close();
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }    

}
