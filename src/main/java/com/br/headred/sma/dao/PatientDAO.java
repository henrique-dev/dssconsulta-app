/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.dao;

import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.PatientAccount;
import com.br.headred.sma.models.PatientProfile;
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
    
    /***************************************************************************************************************/
    /* PARA CRIAÇÃO DE UM USUÁRIO COMPLETO, OS SEGUINTES MÉTODOS DEVEM SER CHAMADOS EM ORDEM                       */
    /*                                                                                                             */

    /** 1º
     * 
     * @param patientUser 
     */
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

    /** 2º
     * 
     * @param patient 
     */
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

    /** 3º
     * 
     * @param patientProfile 
     */
    public void addPatientProfile(PatientProfile patientProfile) {
        String sql = "insert into patientProfile values (?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patientProfile.getPatientUserId());
            stmt.setString(2, patientProfile.getPatientProfileEmail());
            stmt.setString(3, patientProfile.getPatientProfileGenre());
            stmt.setDate(4, patientProfile.getPatientProfileBirthDate());
            stmt.setFloat(5, patientProfile.getPatientProfileHeight());
            stmt.setString(6, patientProfile.getPatientProfileBloodType());
            stmt.setString(7, patientProfile.getPatientProfileTelephone());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    /** 4º
     * 
     * @param patientAccount 
     */
    public void addPatientAccount(PatientAccount patientAccount) {
        String sql = "insert into patientAccount values (?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patientAccount.getPatientProfileId());
            stmt.setDate(2, patientAccount.getPatientAccountCreationDate());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
        
    /*                                                                                                             */
    /***************************************************************************************************************/
        

}
