/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.dao;

import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.models.Medic;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class LoginDAO extends BasicDAO {

    public LoginDAO(Connection connection) {
        super(connection);
    }

    public User login(User user) throws DAOException {
        if (user instanceof Patient) {
            String sql = "select patientUserId, patientName, patientCpf from patientUser "
                    + "join patient on patient.patientId=patientUser.patientId where patientUserName=? and patientUserPassword=?";
            try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
                stmt.setString(1, user.getUserName());
                stmt.setString(2, user.getUserPassword());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int patientId = rs.getInt("patientUserId");
                    Patient patient = new Patient();
                    patient.setId(patientId);
                    patient.setPatientName(rs.getString("patientName"));
                    patient.setPatientCpf(rs.getString("patientCpf"));
                    return patient;
                }
            } catch (SQLException e) {
                throw new DAOException("Usuario ou senha incorretos", e);
            }
        } else if (user instanceof Medic) {
            String sql = "select patientUserId, patientName, patientCpf from patientUser "
                    + "join patient on patient.patientId=patientUser.patientId where patientUserName=? and patientUserPassword=?";
            try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
                stmt.setString(1, user.getUserName());
                stmt.setString(2, user.getUserPassword());
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int patientId = rs.getInt("patientUserId");
                    Patient patient = new Patient();
                    patient.setId(patientId);
                    patient.setPatientName(rs.getString("patientName"));
                    patient.setPatientCpf(rs.getString("patientCpf"));
                    return patient;
                }
            } catch (SQLException e) {
                throw new DAOException("Usuario ou senha incorretos", e);
            }
        }
        return null;
    }

}
