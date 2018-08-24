/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.dao;

import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.models.AccountSpeciality;
import com.br.headred.sma.models.ClinicProfile;
import com.br.headred.sma.models.Consult;
import com.br.headred.sma.models.Medic;
import com.br.headred.sma.models.MedicProfile;
import com.br.headred.sma.models.MedicSpeciality;
import com.br.headred.sma.models.MedicWorkAddress;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.Speciality;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class ConsultDAO extends BasicDAO {

    public ConsultDAO(Connection connection) {
        super(connection);
    }

    public void addAccountSpeciality(AccountSpeciality accountSpeciality) throws DAOException {
        String sql = "insert into accountSpeciality values (?,?,?,?,?,?,?)";
        int accountSpecialityId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.accountSpeciality);
        accountSpeciality.setAccountSpecialityId(accountSpecialityId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, accountSpecialityId);
            stmt.setInt(2, accountSpeciality.getPatientAccount().getPatientAccountId());
            stmt.setDate(3, accountSpeciality.getPatientAccountSpecialityCreationDate());
            stmt.setNull(4, Types.DATE);
            stmt.setBoolean(5, false);
            stmt.setInt(6, accountSpeciality.getMedicSpeciality().getSpeciality().getSpecialityId());
            stmt.setInt(7, accountSpeciality.getMedicSpeciality().getMedicProfile().getId());
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.accountSpeciality, accountSpecialityId);
            throw new DAOException("Falha ao adicionar um encaminhamento na conta", e);
        }
    }

    public List<AccountSpeciality> getAccountSpecialityList(Patient patient) {
        return null;
    }

    public List<AccountSpeciality> getAccountSpecialityList(Speciality speciality) {
        return null;
    }

    private boolean existAccountSpeciality(AccountSpeciality accountSpeciality) {
        String sql = "select accountSpecialityId from accountSpeciality where accountSpecialityId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, accountSpeciality.getAccountSpecialityId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public void removeAccountSpeciality(AccountSpeciality accountSpeciality) throws DAOException {
        if (!existAccountSpeciality(accountSpeciality)) {
            throw new DAOException("Falha ao remover. O encaminhamento não existe");
        }
        String sql = "delete from accountSpeciality where accountSpecialityId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, accountSpeciality.getAccountSpecialityId());
            stmt.execute();
            stmt.close();
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.accountSpeciality, accountSpeciality.getAccountSpecialityId());
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover o encaminhamento", e);
        }
    }

    public void addConsult(Consult consult, Medic medic, Patient patient) throws DAOException {
        this.addConsult(consult);
        this.addMedicConsult(medic, consult);
        this.addPatientConsult(patient, consult);
    }

    private void addConsult(Consult consult) throws DAOException {
        String sql = "insert into consult values (?,?,?,?,?,?,?)";
        int consultId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.consult);
        consult.setConsultId(consultId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, consultId);
            stmt.setDate(2, consult.getConsultCreationDate());
            stmt.setDate(3, consult.getConsultForDate());
            stmt.setBoolean(4, consult.isConsultConsulted());
            stmt.setInt(5, consult.getMedicSpeciality().getSpeciality().getSpecialityId());
            stmt.setInt(6, consult.getMedicSpeciality().getMedicProfile().getId());
            stmt.setInt(7, consult.getMedicWorkAddress().getMedicWorkAddressId());
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.consult, consultId);
            throw new DAOException("Falha ao adicionar a consulta", e);
        }
    }

    private void updateConsult(Consult consult) throws DAOException {
        String sql = "update consult set consultConsulted=? where consultId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setBoolean(1, true);
            stmt.setInt(2, consult.getConsultId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao atualizar a consulta", e);
        }
    }

    public Consult getConsult(int consultId) throws DAOException {
        Consult consult = null;
        String sql = "select * from consult "
                + "inner join medicWorkAddress on consult.medicWorkAddress_fk=medicWorkAddress.medicWorkAddressId "
                + "inner join speciality on speciality.specialityId=consult.medicSpeciality_speciality_fk "
                + "inner join medic on medic.medicUser_fk=medicWorkAddress.medicSpeciality_medicProfile_fk "
                + "inner join clinic on clinic.clinicId=medicWorkAddress.clinicProfile_fk where consultId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, consultId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                consult = new Consult();
                consult.setConsultId(consultId);
                consult.setConsultCreationDate(rs.getDate("consultCreationDate"));
                consult.setConsultForDate(rs.getDate("consultForDate"));                
                consult.setConsultConsulted(rs.getBoolean("consultConsulted"));

                {
                    MedicSpeciality medicSpeciality = new MedicSpeciality();
                    MedicProfile medicProfile = new MedicProfile();
                    medicProfile.setId(rs.getInt("medicSpeciality_medicProfile_fk"));
                    medicProfile.setMedicName(rs.getString("medicName"));
                    medicProfile.setMedicCrm(rs.getString("medicCrm"));
                    medicSpeciality.setMedicProfile(medicProfile);
                    Speciality speciality = new Speciality();
                    speciality.setSpecialityId(rs.getInt("medicSpeciality_speciality_fk"));
                    speciality.setSpecialityName(rs.getString("specialityName"));
                    medicSpeciality.setSpeciality(speciality);
                    
                    MedicWorkAddress medicWorkAddress = new MedicWorkAddress();
                    medicWorkAddress.setMedicWorkAddressId(rs.getInt("medicWorkAddress_fk"));
                    medicWorkAddress.setMedicWorkAddressComplement(rs.getString("medicWorkAddressComplement"));                    
                    medicWorkAddress.setMedicSpeciality(medicSpeciality);
                    ClinicProfile clinicProfile = new ClinicProfile();
                    clinicProfile.setId(rs.getInt("clinicProfile_fk"));
                    clinicProfile.setClinicName(rs.getString("clinicName"));
                    clinicProfile.setClinicCnpj(rs.getString("clinicCnpj"));
                    medicWorkAddress.setClinicProfile(clinicProfile);
                                        
                    consult.setMedicSpeciality(medicSpeciality);
                    consult.setMedicWorkAddress(medicWorkAddress);
                }                        
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao recuperar a consulta", e);
        }
        return consult;
    }

    public List<Consult> getConsultList(Patient patient) throws DAOException {
        List<Consult> consultList = null;
        String sql = "select * from consult where consultId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getId());
            ResultSet rs = stmt.executeQuery();
            consultList = new ArrayList<>();
            while (rs.next()) {
                Consult consult = new Consult();
                consult.setConsultId(rs.getInt("consultId"));
                consult.setConsultCreationDate(rs.getDate("consultCreationDate"));
                consult.setConsultForDate(rs.getDate("consultForDate"));
                consult.setConsultConsulted(rs.getBoolean("consultConsulted"));
                consult.setMedicSpeciality(
                        new MedicSpeciality(
                                new MedicProfile(rs.getInt("medicSpeciality_medicProfile_fk")),
                                new Speciality(rs.getInt("medicSpeciality_speciality_fk"))));
                consult.setMedicWorkAddress(new MedicWorkAddress(rs.getInt("medicWorkAddress_fk")));
                consultList.add(consult);
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao adquirir a lista de consulta do paciente", e);
        }
        return consultList;
    }

    public List<Consult> getConsultList(Medic medic) {
        return null;
    }

    public void removeConsult(Consult consult) throws DAOException {
        consult.setConsultConsulted(true);
        updateConsult(consult);
        removeMedictConsult(consult);
        removePatientConsult(consult);
    }

    private void addPatientConsult(Patient patient, Consult consult) throws DAOException {
        String sql = "insert into patientConsult values (?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, consult.getConsultId());
            stmt.setInt(2, patient.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar a consulta para o paciente", e);
        }
    }

    private void removePatientConsult(Consult consult) throws DAOException {
        String sql = "delete from patientConsult where consult_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, consult.getConsultId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover a consulta do paciente", e);
        }
    }

    private void addMedicConsult(Medic medic, Consult consult) throws DAOException {
        String sql = "insert into medicConsult values (?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, consult.getConsultId());
            stmt.setInt(2, medic.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar a consulta para o medico", e);
        }
    }

    private void removeMedictConsult(Consult consult) throws DAOException {
        String sql = "delete from medicConsult where consult_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, consult.getConsultId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover a consulta do medico", e);
        }
    }

}
