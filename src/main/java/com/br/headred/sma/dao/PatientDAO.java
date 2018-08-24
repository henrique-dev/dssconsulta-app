/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.dao;

import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.models.Medic;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.PatientAccount;
import com.br.headred.sma.models.Evaluation;
import com.br.headred.sma.models.PatientProfile;
import com.br.headred.sma.models.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLType;
import java.sql.Types;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class PatientDAO extends BasicDAO {

    public PatientDAO(Connection connection) {
        super(connection);
    }
    
    public Patient login(Patient user) throws DAOException {
        String sql = "select patientUserId from patientUser where patientUserName=? and patientUserPassword=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserPassword());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int patientId = rs.getInt("patientUserId");                
                String sessionId = (user.getUserName().hashCode() + Calendar.getInstance().getTimeInMillis()) + "";                                
                Patient patient = this.getPatient(patientId);
                patient.setUserSessionId(sessionId);
                this.addPatientSessionId(patient);
                return patient;
            }
        } catch (SQLException e) {
            throw new DAOException("Usuario ou senha incorretos", e);
        }
        return null;
    }

    /**
     * Adiciona completamente um paciente na base de dados.
     * @param patient
     * @throws DAOException 
     */
    public void addPatient(PatientProfile patient) throws DAOException {
        addPatientUser(patient);
        addPatientInfo(patient);        
        addPatientProfile(patient);
        PatientAccount patientAccount = new PatientAccount();
        patientAccount.setPatientAccountId(patient.getId());
        patientAccount.setPatientAccountCreationDate(new Date(Calendar.getInstance().getTimeInMillis()));
        addPatientAccount(patientAccount);
    }

    /**
     * Cria um usuário para o paciente.
     * Usado para adicionar um paciente na base de dados.
     * @param patientUser
     * @throws DAOException 
     */
    private void addPatientUser(User patientUser) throws DAOException {
        String sql = "insert into patientUser values (?,?,?,?)";
        int patientUserId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.patientUser);
        patientUser.setId(patientUserId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patientUserId);
            stmt.setString(2, patientUser.getUserName());
            stmt.setString(3, patientUser.getUserPassword());
            stmt.setNull(4, Types.VARCHAR);
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.patientUser, patientUserId);
            if (e instanceof SQLIntegrityConstraintViolationException)
                throw new DAOException("Falha ao adicionar um usuário para o paciente. O nome de usuário já existe", e);
            else
                throw new DAOException("Falha ao adicionar um usuário para o paciente", e);
            
        }
    }

    /**
     * Cria informação primarias para o paciente.
     * Usado para adicionar um paciente na base de dados.
     * @param patient
     * @throws DAOException 
     */
    private void addPatientInfo(Patient patient) throws DAOException {
        String sql = "insert into patient values (?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getId());
            stmt.setString(2, patient.getPatientName());
            stmt.setString(3, patient.getPatientCpf());
            stmt.execute();
        } catch (SQLException e) {
            removePatient(patient);
            if (e instanceof SQLIntegrityConstraintViolationException) {
                throw new DAOException("Falha ao adicionar as informações do paciente. O cpf já existe", e);
            } else {                
                throw new DAOException("Falha ao adicionar as informações do paciente", e);
            }

        }
    }

    /**
     * Cria um perfil para o paciente.
     * Usado para adicionar um paciente na base de dados.
     * @param patientProfile
     * @throws DAOException 
     */
    private void addPatientProfile(PatientProfile patientProfile) throws DAOException {
        String sql = "insert into patientProfile values (?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patientProfile.getId());
            stmt.setString(2, patientProfile.getPatientProfileEmail());
            stmt.setString(3, patientProfile.getPatientProfileGenre());
            stmt.setDate(4, patientProfile.getPatientProfileBirthDate());
            stmt.setFloat(5, patientProfile.getPatientProfileHeight());
            stmt.setString(6, patientProfile.getPatientProfileBloodType());
            stmt.setString(7, patientProfile.getPatientProfileTelephone());
            stmt.execute();
        } catch (SQLException e) {
            removePatient(new Patient(patientProfile.getId()));
            throw new DAOException("Falha ao adicionar o perfil do paciente", e);
        }
    }

    /**
     * Cria uma conta para o paciente
     * Usado para adicionar um paciente na base de dados.
     * @param patientAccount
     * @throws DAOException 
     */
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
    
    public Patient getPatient(int patientId) throws DAOException {
        String sql = "select patientName, patientCpf from patient where patientUser_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Patient patient = new Patient();
                patient.setId(patientId);
                patient.setPatientName(rs.getString("patientName"));
                patient.setPatientCpf(rs.getString("patientCpf"));
                return patient;
            } 
        } catch (SQLException e) {
            throw new DAOException("Falha ao recuperar dados do paciente", e);
        }
        return null;
    }
    
    private void addPatientSessionId(Patient patient) throws DAOException {
        String sql = "update patientUser set patientUserSessionId=? where patientUserId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setString(1, patient.getUserSessionId());
            stmt.setInt(2, patient.getId());            
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao inserir o identificador para a sessão do paciente", e);
        }
    }

    /**
     * Verifica a existencia de um paciente.
     * @param patient
     * @return 
     */
    public boolean existPatient(Patient patient) {
        String sql = "select patientUserId from patientUser where patientUserId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    /**
     * Remove completamente um paciente da base de dados.
     * @param patient
     * @throws DAOException 
     */
    public void removePatient(Patient patient) throws DAOException {
        if (!existPatient(patient)) {
            throw new DAOException("Falha ao remover. O paciente não existe");
        }
        removePatientAccount(patient);
        removePatientProfile(patient);
        removePatientInfo(patient);
        removePatientUser(patient);
    }

    /**
     * Remove o usuario do paciente.
     * Usado para remover um usuario da base de dados.
     * @param patient
     * @throws DAOException 
     */
    private void removePatientUser(Patient patient) throws DAOException {
        String sql = "delete from patientUser where patientUserId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getId());
            stmt.execute();
            stmt.close();
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.patientUser, patient.getId());
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover o usuário do paciente", e);
        }
    }

    /**
     * Remove informações primarias do paciente.
     * Usado para remover um usuario da base de dados.
     * @param patient
     * @throws DAOException 
     */
    private void removePatientInfo(Patient patient) throws DAOException {
        String sql = "delete from patient where patientUser_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover as informações do paciente", e);
        }
    }

    /**
     * Remove o perfil do paciente.
     * Usado para remover um usuario da base de dados.
     * @param patient
     * @throws DAOException 
     */
    private void removePatientProfile(Patient patient) throws DAOException {
        String sql = "delete from patientProfile where patient_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover o perfil do paciente", e);
        }
    }        

    /**
     * Remove a conta do paciente.
     * Usado para remover um usuario da base de dados.
     * @param patient
     * @throws DAOException 
     */
    private void removePatientAccount(Patient patient) throws DAOException {
        String sql = "delete from patientAccount where patientProfile_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover o perfil do paciente", e);
        }
    }
    
    public void addPatientEvaluation(Evaluation evaluation) throws DAOException {
        String sql = "insert into evaluation values (?,?,?,?,?,?)";
        int patientEvaluationId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.evaluation);
        evaluation.setEvaluationId(patientEvaluationId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patientEvaluationId);
            stmt.setInt(2, evaluation.getMedicProfile().getId());
            stmt.setInt(3, evaluation.getPatientProfile().getId());
            stmt.setString(4, evaluation.getEvaluationDescName());
            stmt.setString(5, evaluation.getEvaluationDescInfo());
            stmt.setInt(6, evaluation.getEvaluationScore());
            stmt.execute();
            stmt.close();
            new MedicDAO(super.connection).updateMedicEvaluation(evaluation.getMedicProfile());
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.evaluation, patientEvaluationId);
            throw new DAOException("Falha ao adicionar uma avaliação do medico", e);
        }
    }        

}
