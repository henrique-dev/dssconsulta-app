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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.Calendar;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class PatientDAO extends BasicDAO {

    public PatientDAO(Connection connection) {
        super(connection);
    }        
    
    public void addPatient(Patient patient) throws DAOException {
        addPatientUser(patient);
        addPatientInfo(patient);
        patient.getPatientProfile().setPatientProfileId(patient.getUserId());
        addPatientProfile(patient.getPatientProfile());
        PatientAccount patientAccount = new PatientAccount();
        patientAccount.setPatientAccountId(patient.getUserId());
        patientAccount.setPatientAccountCreationDate(new Date(Calendar.getInstance().getTimeInMillis()));
        addPatientAccount(patientAccount);
    }
    
    private void addPatientUser(User patientUser) throws DAOException {
        String sql = "insert into patientUser values (?,?,?,?)";
        int patientUserId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.patientUser);
        patientUser.setUserId(patientUserId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patientUserId);
            stmt.setString(2, patientUser.getUserName());
            stmt.setString(3, patientUser.getUserPassword());
            stmt.setNull(4, Types.VARCHAR);
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.patientUser, patientUserId);
            throw new DAOException("Falha ao adicionar um usuário para o paciente", e);
        }
    }
    
    private void addPatientInfo(Patient patient) throws DAOException {
        String sql = "insert into patient values (?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getUserId());
            stmt.setString(2, patient.getPatientName());
            stmt.setString(3, patient.getPatientCpf());
            stmt.execute();
        } catch (SQLException e) {
            removePatient(patient);
            throw new DAOException("Falha ao adicionar as informações do paciente", e);
        }
    }
    
    private void addPatientProfile(PatientProfile patientProfile) throws DAOException {
        String sql = "insert into patientProfile values (?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patientProfile.getPatientProfileId());
            stmt.setString(2, patientProfile.getPatientProfileEmail());
            stmt.setString(3, patientProfile.getPatientProfileGenre());
            stmt.setDate(4, patientProfile.getPatientProfileBirthDate());
            stmt.setFloat(5, patientProfile.getPatientProfileHeight());
            stmt.setString(6, patientProfile.getPatientProfileBloodType());
            stmt.setString(7, patientProfile.getPatientProfileTelephone());
            stmt.execute();
        } catch (SQLException e) {
            removePatient(new Patient(patientProfile.getPatientProfileId()));
            throw new DAOException("Falha ao adicionar o perfil do paciente", e);
        }
    }
    
    private void addPatientAccount(PatientAccount patientAccount) throws DAOException {
        String sql = "insert into patientAccount values (?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patientAccount.getPatientAccountId());
            stmt.setDate(2, patientAccount.getPatientAccountCreationDate());
            stmt.execute();
        } catch (SQLException e) {
            removePatient(new Patient(patientAccount.getPatientAccountId()));
            throw new DAOException("Falha ao adicionar a conta do paciente", e);
        }
    }
    
    public boolean existPatient(Patient patient) {
        String sql = "select patientUserId from patientUser where patientUserId=?";        
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getUserId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) 
                return true;
        } catch (SQLException e) {}
        return false;
    }
    
    public void removePatient(Patient patient) throws DAOException {
        if (!existPatient(patient))
            throw new DAOException("Falha ao remover. O paciente não existe");
        removePatientAccount(patient);
        removePatientProfile(patient);
        removePatientInfo(patient);
        removePatientUser(patient);
    }
    
    private void removePatientUser(Patient patient) throws DAOException {
        String sql = "delete from patientUser where patientUserId=?";        
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getUserId());            
            stmt.execute();
            stmt.close();
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.patientUser, patient.getUserId());
        } catch (SQLException e) {            
            throw new DAOException("Falha ao remover o usuário do paciente", e);
        }
    }
    
    private void removePatientInfo(Patient patient) throws DAOException {
        String sql = "delete from patient where patientUser_fk=?";        
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getUserId());            
            stmt.execute();            
        } catch (SQLException e) {            
            throw new DAOException("Falha ao remover as informações do paciente", e);
        }
    }
    
    private void removePatientProfile(Patient patient) throws DAOException {
        String sql = "delete from patientProfile where patient_fk=?";        
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getUserId());            
            stmt.execute();            
        } catch (SQLException e) {            
            throw new DAOException("Falha ao remover o perfil do paciente", e);
        }
    }
    
    private void removePatientAccount(Patient patient) throws DAOException {
        String sql = "delete from patientAccount where patientProfile_fk=?";        
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getUserId());            
            stmt.execute();            
        } catch (SQLException e) {            
            throw new DAOException("Falha ao remover o perfil do paciente", e);
        }
    }
            
        

}
