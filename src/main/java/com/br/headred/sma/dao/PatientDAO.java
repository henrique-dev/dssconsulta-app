/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.dao;

import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class PatientDAO extends BasicDAO {
    
    public PatientDAO(Connection connection) {
        super(connection);
    }
    
    public void addPatientUser(User patientUser) {
        String sql = "insert into patientUser values (?,?,?,?)";
        int patientUserId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.patientUser);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patientUserId);
            stmt.setString(2, patientUser.getPatientUserName());
            stmt.setString(3, patientUser.getPatientUserPassword());
            stmt.setNull(4, Types.VARCHAR);
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.patientUser, patientUserId);
            throw new DAOException(e);
        }
    }
    
    public void addPatient(Patient patient) {
        String sql = "insert into patient values (?,?,?)";        
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getPatientUserId());
            stmt.setString(2, patient.getPatientName());
            stmt.setString(3, patient.getPatientCpf());
            stmt.execute();
        } catch (SQLException e) {            
            throw new DAOException(e);
        }
    }
    
    
    
}
