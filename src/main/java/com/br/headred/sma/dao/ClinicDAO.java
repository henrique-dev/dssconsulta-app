/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.dao;

import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.models.Clinic;
import com.br.headred.sma.models.ClinicProfile;
import com.br.headred.sma.models.ClinicTelephone;
import com.br.headred.sma.models.Medic;
import com.br.headred.sma.models.Speciality;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class ClinicDAO extends BasicDAO {

    public ClinicDAO(Connection connection) {
        super(connection);
    }

    public void addClinic(ClinicProfile clinic) throws DAOException {
        addClinicInfo(clinic);
        addClinicProfile(clinic);
    }

    private void addClinicInfo(Clinic clinic) throws DAOException {
        String sql = "insert into clinic values (?,?,?)";
        int clinicId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.clinic);
        clinic.setId(clinicId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, clinicId);
            stmt.setString(2, clinic.getClinicName());
            stmt.setString(3, clinic.getClinicCnpj());
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.patientUser, clinicId);
            removeClinic(clinic);
            if (e instanceof SQLIntegrityConstraintViolationException) {
                throw new DAOException("Falha ao adicionar as informações da clinica. O cnpj ja existe", e);
            } else {
                throw new DAOException("Falha ao adicionar as informações da clinica", e);
            }
        }
    }

    private void addClinicProfile(ClinicProfile clinicProfile) throws DAOException {
        String sql = "insert into clinicProfile values (?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, clinicProfile.getId());
            stmt.setString(2, clinicProfile.getClinicProfileBio());
            stmt.setString(3, clinicProfile.getClinicProfileAddress());
            stmt.execute();
            if (clinicProfile.getClinicTelephoneList() != null) {
                stmt.close();
                for (ClinicTelephone ct : clinicProfile.getClinicTelephoneList()) {
                    ct.setClinicProfile(clinicProfile);
                    addClinicTelephone(ct);
                }
            }
            if (clinicProfile.getClinicProfileFileList() != null) {

            }
        } catch (SQLException e) {
            removeClinic(clinicProfile);
            throw new DAOException("Falha ao adicionar um perfil para a clinica", e);
        }
    }

    public List<ClinicProfile> getClinicProfileList() throws DAOException {
        List<ClinicProfile> clinicProfileList = null;
        String sql = "select clinicId, clinicName, clinicCnpj, clinicProfileAddress, clinicTelephoneNumber from clinic "
                + "inner join clinicProfile on clinic.clinicId=clinicProfile.clinic_fk "
                + "left join clinicTelephone on clinic.clinicId=clinicTelephone.clinicProfile_fk";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {            
            ResultSet rs = stmt.executeQuery();
            int currentClinic = -1;
            ClinicProfile clinicProfile = new ClinicProfile();
            clinicProfileList = new ArrayList<>();
            List<ClinicTelephone> clinicTelephoneList = new ArrayList<>();
            while (rs.next()) {
                int clinicId = rs.getInt("clinicId");
                if (clinicId != currentClinic) {
                    if (currentClinic != -1) {
                        clinicProfile.setClinicTelephoneList(clinicTelephoneList);
                        clinicProfileList.add(clinicProfile);
                    }
                    currentClinic = clinicId;
                    clinicProfile = new ClinicProfile();
                    clinicTelephoneList = new ArrayList<>();
                    clinicProfile.setId(clinicId);
                    clinicProfile.setClinicCnpj(rs.getString("clinicCnpj"));
                    clinicProfile.setClinicName(rs.getString("clinicName"));
                    clinicProfile.setClinicProfileAddress(rs.getString("clinicProfileAddress"));
                }
                ClinicTelephone clinicTelephone = new ClinicTelephone();
                clinicTelephone.setClinicTelephoneNumber(rs.getString("clinicTelephoneNumber"));
                clinicTelephone.setClinicProfile(clinicProfile);
                clinicTelephoneList.add(clinicTelephone);
            }
            if (currentClinic != -1) {
                clinicProfile.setClinicTelephoneList(clinicTelephoneList);
                clinicProfileList.add(clinicProfile);
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao recuperar a lista de clincas", e);
        }
        return clinicProfileList;
    }

    public ClinicProfile getClinicProfile(int clinicId) throws DAOException {
        ClinicProfile clinicProfile = null;
        String sql = "select clinicName, clinicCnpj, clinicProfileBio, clinicProfileAddress from clinic "
                + "inner join clinicProfile on clinic.clinicId=clinicProfile.clinic_fk where clinicId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, clinicId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                clinicProfile = new ClinicProfile();
                clinicProfile.setId(clinicId);
                clinicProfile.setClinicName(rs.getString("clinicName"));
                clinicProfile.setClinicCnpj(rs.getString("clinicCnpj"));
                clinicProfile.setClinicProfileAddress(rs.getString("clinicProfileAddress"));
                clinicProfile.setClinicProfileBio("clinicProfileBio");
                stmt.close();
                clinicProfile.setClinicTelephoneList(getClinicTelephoneList(clinicProfile));
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao adquirir o perfil da clinica", e);
        }
        return clinicProfile;
    }

    public boolean existClinic(Clinic clinic) {
        String sql = "select clinicId from clinic where clinicId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, clinic.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public void removeClinic(Clinic clinic) throws DAOException {
        if (!existClinic(clinic)) {
            throw new DAOException("Falha ao remover. A clinica não existe");
        }
        removeClinicTelephone(clinic);
        removeClinicProfile(clinic);
        removeClinicInfo(clinic);
    }

    public void removeClinicInfo(Clinic clinic) throws DAOException {
        String sql = "delete from clinic where clinicId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, clinic.getId());
            stmt.execute();
            stmt.close();
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.clinic, clinic.getId());
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover a informação da clinica", e);
        }
    }

    public void removeClinicProfile(Clinic clinic) throws DAOException {
        String sql = "delete from clinicProfile where clinic_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, clinic.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover o perfil da clinica", e);
        }
    }
    
    public void addClinicTelephoneList(List<ClinicTelephone> clinicTelephoneList) throws DAOException {
        StringBuilder argumentsSize = new StringBuilder();
        for (int i = 0; i < clinicTelephoneList.size(); i++) {
            argumentsSize.append("(?,?),");
        }
        argumentsSize.deleteCharAt(argumentsSize.length() - 1);
        String sql = "insert into clinicTelephone values " + argumentsSize.toString();
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            int counter = 1;
            for (int i = 0; i < clinicTelephoneList.size(); i++) {
                stmt.setInt(counter++, clinicTelephoneList.get(i).getClinicProfile().getId());
                stmt.setString(counter++, clinicTelephoneList.get(i).getClinicTelephoneNumber());                
            }
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar as variaveis de trabalho do medico", e);
        }
    }

    public void addClinicTelephone(ClinicTelephone clinicTelephone) throws DAOException {
        String sql = "insert into clinicTelephone values (?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, clinicTelephone.getClinicProfile().getId());
            stmt.setString(2, clinicTelephone.getClinicTelephoneNumber());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar o numero de telefone para a clinica", e);
        }
    }

    public List<ClinicTelephone> getClinicTelephoneList(ClinicProfile clinicProfile) throws DAOException {
        List<ClinicTelephone> clinicTelephoneList = null;
        String sql = "select clinicTelephoneNumber from clinicTelephone where clinicProfile_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, clinicProfile.getId());
            ResultSet rs = stmt.executeQuery();
            clinicTelephoneList = new ArrayList<>();
            while (rs.next()) {
                ClinicTelephone clinicTelephone = new ClinicTelephone();
                clinicTelephone.setClinicProfile(clinicProfile);
                clinicTelephone.setClinicTelephoneNumber(rs.getString("clinicTelephoneNumber"));
                clinicTelephoneList.add(clinicTelephone);
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao adquirir a lista de telefones da clinica", e);
        }
        return clinicTelephoneList;
    }

    public void removeClinicTelephone(Clinic clinic) throws DAOException {
        String sql = "delete from clinicTelephone where clinicProfile_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, clinic.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover o numero de telefone da clinica", e);
        }
    }

}
