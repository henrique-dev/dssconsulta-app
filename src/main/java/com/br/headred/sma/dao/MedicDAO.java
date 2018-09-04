/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.dao;

import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.models.Clinic;
import com.br.headred.sma.models.ClinicProfile;
import com.br.headred.sma.models.Medic;
import com.br.headred.sma.models.MedicProfile;
import com.br.headred.sma.models.MedicSpeciality;
import com.br.headred.sma.models.MedicWorkAddress;
import com.br.headred.sma.models.MedicWorkScheduling;
import com.br.headred.sma.models.Speciality;
import com.br.headred.sma.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class MedicDAO extends BasicDAO {

    public MedicDAO(Connection connection) {
        super(connection);
    }

    public void addMedic(MedicProfile medic) throws DAOException {
        addMedicUser(medic);
        addMedicInfo(medic);
        addMedicProfile(medic);
        addMedicEvaluation(medic);
    }

    private void addMedicUser(User medicUser) throws DAOException {
        String sql = "insert into medicUser values (?,?,?,?)";
        int medicUserId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.medicUser);
        medicUser.setId(medicUserId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicUserId);
            stmt.setString(2, medicUser.getUserName());
            stmt.setString(3, medicUser.getUserPassword());
            stmt.setNull(4, Types.VARCHAR);
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.medicUser, medicUserId);
            if (e instanceof SQLIntegrityConstraintViolationException) {
                throw new DAOException("Falha ao adicionar um usuário para o medico. O nome de usuário já existe", e);
            } else {
                throw new DAOException("Falha ao adicionar um usuário para o médico", e);
            }
        }
    }

    private void addMedicInfo(Medic medic) throws DAOException {
        String sql = "insert into medic values (?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getId());
            stmt.setString(2, medic.getMedicName());
            stmt.setString(3, medic.getMedicCrm());
            stmt.execute();
        } catch (SQLException e) {
            removeMedic(medic);
            if (e instanceof SQLIntegrityConstraintViolationException) {
                throw new DAOException("Falha ao adicionar as informações do médico. O crm já existe", e);
            } else {
                throw new DAOException("Falha ao adicionar as informações do médico", e);
            }
        }
    }

    private void addMedicProfile(MedicProfile medicProfile) throws DAOException {
        String sql = "insert into medicProfile values (?,?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicProfile.getId());
            stmt.setString(2, medicProfile.getMedicProfileBio());
            stmt.setInt(3, medicProfile.getMedicProfileExpAge());
            stmt.setString(4, medicProfile.getMedicProfileInfoCompl());
            stmt.execute();
        } catch (SQLException e) {
            removeMedic(medicProfile);
            throw new DAOException("Falha ao adicionar o perfil do médico", e);
        }
    }

    private void addMedicEvaluation(MedicProfile medicProfile) throws DAOException {
        String sql = "insert into medicEvaluation values (?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicProfile.getId());
            stmt.setNull(2, Types.FLOAT);
            stmt.setInt(3, 0);
            stmt.execute();
        } catch (SQLException e) {
            removeMedic(medicProfile);
            throw new DAOException("Falha ao adicionar o perfil de avaliação do medico", e);
        }
    }

    public boolean existMedic(Medic medic) {
        String sql = "select medicUserId from medicUser where medicUserId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public void removeMedic(Medic medic) throws DAOException {
        if (!existMedic(medic)) {
            throw new DAOException("Falha ao remover. O medico não existe");
        }
        removeMedicEvaluation(medic);
        removeMedicProfile(medic);
        removeMedicInfo(medic);
        removeMedicUser(medic);
    }

    private void removeMedicUser(Medic medic) throws DAOException {
        String sql = "delete from medicUser where medicUserId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getId());
            stmt.execute();
            stmt.close();
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.medicUser, medic.getId());
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover o usuário do médico", e);
        }
    }

    private void removeMedicInfo(Medic medic) throws DAOException {
        String sql = "delete from medic where medicUser_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover as informações do médico", e);
        }
    }

    private void removeMedicProfile(Medic medic) throws DAOException {
        String sql = "delete from medicProfile where medic_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover o perfil do médico", e);
        }
    }

    private void removeMedicEvaluation(Medic medic) throws DAOException {
        String sql = "delete from medicEvaluation where medicProfile_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover o perfil de avaliação do medico", e);
        }
    }

    public List<MedicProfile> getMedicProfileList(int specialityId) throws DAOException {
        List<MedicProfile> medicProfileList = null;
        String sql = "select "
                + "medic_fk, medicName, medicWorkAddressId, medicProfileExpAge, medicEvaluationAvg, "
                + "medicEvaluationCount, clinicId, clinicName, specialityId, specialityName, specialityPriv "
                + "from medicWorkAddress "
                + "join medicProfile on medicWorkAddress.medicSpeciality_medicProfile_fk=medicProfile.medic_fk "
                + "join medicEvaluation on medicEvaluation.medicProfile_fk=medicProfile.medic_fk "
                + "join medic on medic.medicUser_fk=medicProfile.medic_fk "
                + "join clinic on clinic.clinicId=medicWorkAddress.clinicProfile_fk "
                + "join speciality on speciality.specialityId=medicWorkAddress.medicSpeciality_speciality_fk where specialityId=? "
                + "order by medic_fk";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, specialityId);
            ResultSet rs = stmt.executeQuery();
            int currentMedic = -1;
            MedicProfile medicProfile = new MedicProfile();
            medicProfileList = new ArrayList<>();
            List<MedicWorkAddress> medicWorkAddressList = new ArrayList<>();
            List<MedicSpeciality> medicSpecialityList = new ArrayList<>();;
            while (rs.next()) {
                int medicId = rs.getInt("medic_fk");
                if (medicId != currentMedic) {
                    if (currentMedic != -1) {
                        medicProfile.setMedicWorkAddressList(medicWorkAddressList);
                        medicProfile.setMedicSpecialityList(medicSpecialityList);
                        medicProfileList.add(medicProfile);
                    }
                    currentMedic = medicId;
                    medicProfile = new MedicProfile();
                    medicWorkAddressList = new ArrayList<>();
                    medicSpecialityList = new ArrayList<>();
                    medicProfile.setId(medicId);
                    medicProfile.setMedicName(rs.getString("medicName"));
                    medicProfile.setMedicProfileExpAge(rs.getInt("medicProfileExpAge"));
                    medicProfile.setMedicProfileEvaluationAvg(rs.getFloat("medicEvaluationAvg"));
                    medicProfile.setMedicProfileEvaluationCount(rs.getInt("medicEvaluationCount"));
                }
                MedicSpeciality medicSpeciality = new MedicSpeciality();
                Speciality speciality = new Speciality();
                speciality.setSpecialityId(rs.getInt("specialityId"));
                speciality.setSpecialityName(rs.getString("specialityName"));
                speciality.setSpecialityPriv(rs.getBoolean("specialityPriv"));
                medicSpeciality.setSpeciality(speciality);
                medicSpeciality.setMedicProfile(medicProfile);
                medicSpecialityList.add(medicSpeciality);

                MedicWorkAddress medicWorkAddress = new MedicWorkAddress();
                ClinicProfile clinic = new ClinicProfile();
                clinic.setId(rs.getInt("clinicId"));
                clinic.setClinicName(rs.getString("clinicName"));
                medicWorkAddress.setMedicWorkAddressId(rs.getInt("medicWorkAddressId"));
                medicWorkAddress.setClinicProfile(clinic);
                medicWorkAddress.setMedicSpeciality(medicSpeciality);
                medicWorkAddressList.add(medicWorkAddress);
            }
            if (currentMedic != -1) {
                medicProfile.setMedicWorkAddressList(medicWorkAddressList);
                medicProfile.setMedicSpecialityList(medicSpecialityList);
                medicProfileList.add(medicProfile);
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao adquirir a lista de medicos", e);
        }

        return medicProfileList;
    }

    public MedicProfile getMedicProfile(int medicId) throws DAOException {
        MedicProfile medicProfile = null;
        String sql = "select "
                + "medic_fk, medicName, medicWorkAddressId, medicProfileBio, medicProfileInfoCompl, medicProfileExpAge, medicEvaluationAvg, "
                + "medicEvaluationCount, clinicId, clinicName, specialityId, specialityName, specialityPriv "
                + "from medic "
                + "join medicProfile on medicProfile.medic_fk=medic.medicUser_fk "
                + "join medicEvaluation on medicEvaluation.medicProfile_fk=medicProfile.medic_fk "
                + "left join medicWorkAddress on medicWorkAddress.medicSpeciality_medicProfile_fk=medicProfile.medic_fk "
                + "left join clinic on clinic.clinicId=medicWorkAddress.clinicProfile_fk "
                + "left join speciality on speciality.specialityId=medicWorkAddress.medicSpeciality_speciality_fk "
                + "where medic_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicId);
            ResultSet rs = stmt.executeQuery();
            int currentMedic = -1;
            medicProfile = new MedicProfile();
            List<MedicWorkAddress> medicWorkAddressList = new ArrayList<>();
            List<MedicSpeciality> medicSpecialityList = new ArrayList<>();;
            while (rs.next()) {                
                if (currentMedic != medicId) {
                    currentMedic = medicId;                                                            
                    medicProfile.setId(medicId);
                    medicProfile.setMedicName(rs.getString("medicName"));
                    medicProfile.setMedicProfileExpAge(rs.getInt("medicProfileExpAge"));
                    medicProfile.setMedicProfileEvaluationAvg(rs.getFloat("medicEvaluationAvg"));
                    medicProfile.setMedicProfileEvaluationCount(rs.getInt("medicEvaluationCount"));
                    medicProfile.setMedicProfileInfoCompl(rs.getString("medicProfileInfoCompl"));
                    medicProfile.setMedicProfileBio(rs.getString("medicProfileBio"));
                }

                MedicSpeciality medicSpeciality = new MedicSpeciality();
                Speciality speciality = new Speciality();
                speciality.setSpecialityId(rs.getInt("specialityId"));
                speciality.setSpecialityName(rs.getString("specialityName"));
                speciality.setSpecialityPriv(rs.getBoolean("specialityPriv"));
                medicSpeciality.setSpeciality(speciality);
                medicSpeciality.setMedicProfile(medicProfile);
                medicSpecialityList.add(medicSpeciality);

                MedicWorkAddress medicWorkAddress = new MedicWorkAddress();
                ClinicProfile clinic = new ClinicProfile();
                clinic.setId(rs.getInt("clinicId"));
                clinic.setClinicName(rs.getString("clinicName"));
                medicWorkAddress.setMedicWorkAddressId(rs.getInt("medicWorkAddressId"));
                medicWorkAddress.setClinicProfile(clinic);
                medicWorkAddress.setMedicSpeciality(medicSpeciality);
                medicWorkAddressList.add(medicWorkAddress);
                
            }
            if (currentMedic != -1) {
                medicProfile.setMedicWorkAddressList(medicWorkAddressList);
                medicProfile.setMedicSpecialityList(medicSpecialityList);
                System.out.println(medicWorkAddressList.size());
                System.out.println(medicSpecialityList.size());
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao adquirir a lista de medicos", e);
        }

        return medicProfile;
    }

    public void addSpeciality(Speciality speciality) throws DAOException {
        String sql = "insert into speciality values (?,?,?)";
        int specialityIndex = new SystemDAO(super.connection).getNextId(SystemDAO.Table.speciality);
        speciality.setSpecialityId(specialityIndex);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, specialityIndex);
            stmt.setString(2, speciality.getSpecialityName());
            stmt.setBoolean(3, speciality.isSpecialityPriv());
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.speciality, specialityIndex);
            throw new DAOException("Falha ao adicionar a especialidade", e);
        }
    }

    public Speciality getSpeciality(int specialityId) throws DAOException {
        Speciality speciality = null;
        String sql = "select * from speciality where specialityId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, specialityId);
            ResultSet rs = stmt.executeQuery();
            speciality = new Speciality();
            if (rs.next()) {
                speciality.setSpecialityId(specialityId);
                speciality.setSpecialityName(rs.getString("specialityName"));
                speciality.setSpecialityPriv(rs.getBoolean("specialityPriv"));
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao recuperar a lista de especialidades", e);
        }
        return speciality;
    }

    public List<Speciality> getSpecialityList() throws DAOException {
        List<Speciality> specialityList = null;
        String sql = "select * from speciality";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            specialityList = new ArrayList<>();
            while (rs.next()) {
                Speciality speciality = new Speciality();
                speciality.setSpecialityId(rs.getInt("specialityId"));
                speciality.setSpecialityName(rs.getString("specialityName"));
                speciality.setSpecialityPriv(rs.getBoolean("specialityPriv"));
                specialityList.add(speciality);
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao recuperar a lista de especialidades", e);
        }
        return specialityList;
    }
    
    public List<Speciality> getSpecialityListFromMedicSpeciality() throws DAOException {
        List<Speciality> specialityList = null;
        String sql = "select specialityId, specialityName, specialityPriv from medicSpeciality "
                + "join speciality on medicSpeciality.speciality_fk=speciality.specialityId "
                + "group by speciality_fk";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            specialityList = new ArrayList<>();
            while (rs.next()) {
                Speciality speciality = new Speciality();
                speciality.setSpecialityId(rs.getInt("specialityId"));
                speciality.setSpecialityName(rs.getString("specialityName"));
                speciality.setSpecialityPriv(rs.getBoolean("specialityPriv"));
                specialityList.add(speciality);
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao recuperar a lista de especialidades", e);
        }
        return specialityList;
    }

    public boolean existSpeciality(Speciality speciality) {
        String sql = "select specialityId from speciality where specialityId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, speciality.getSpecialityId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public void removeSpeciality(Speciality speciality) throws DAOException {
        if (!existSpeciality(speciality)) {
            throw new DAOException("Falha ao remover. A especialidade não existe");
        }
        String sql = "delete from speciality where specialityId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, speciality.getSpecialityId());
            stmt.execute();
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.speciality, speciality.getSpecialityId());
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover a especialidade", e);
        }
    }

    @Deprecated
    public void addMedicSpecialityList(List<MedicSpeciality> medicSpecialityList) throws DAOException {
        StringBuilder argumentsSize = new StringBuilder();
        for (int i = 0; i < medicSpecialityList.size(); i++) {
            argumentsSize.append("(?,?),");
        }
        argumentsSize.deleteCharAt(argumentsSize.length() - 1);
        String sql = "insert into medicSpeciality values " + argumentsSize.toString();
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            int counter = 1;
            for (int i = 0; i < medicSpecialityList.size(); i++) {
                stmt.setInt(counter++, medicSpecialityList.get(i).getMedicProfile().getId());
                stmt.setInt(counter++, medicSpecialityList.get(i).getSpeciality().getSpecialityId());
            }
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao inserir as especialidades do medico", e);
        }
    }

    public boolean existMedicSpeciality(MedicSpeciality medicSpeciality) {
        String sql = "select * from medicSpeciality where medicProfile_fk=? and speciality_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicSpeciality.getMedicProfile().getId());
            stmt.setInt(2, medicSpeciality.getSpeciality().getSpecialityId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public void addMedicSpeciality(MedicSpeciality medicSpeciality) throws DAOException {
        if (existMedicSpeciality(medicSpeciality)) {
            return;
        }
        String sql = "insert into medicSpeciality values (?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicSpeciality.getMedicProfile().getId());
            stmt.setInt(2, medicSpeciality.getSpeciality().getSpecialityId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar a especialidade para o medico", e);
        }
    }

    public void removeMedicSpeciality(MedicSpeciality medicSpeciality) throws DAOException {
        String sql = "delete from medicSpeciality where medicProfile_fk=? and speciality_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicSpeciality.getMedicProfile().getId());
            stmt.setInt(2, medicSpeciality.getSpeciality().getSpecialityId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover a especialidade do medico", e);
        }
    }

    public void removeAllMedicSpeciality(Medic medic) throws DAOException {
        String sql = "delete from medicSpeciality where medicProfile_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover as especialidades do medico", e);
        }
    }

    public void addMedicWorkAddressAndScheduling(MedicWorkAddress medicWorkAddress, MedicWorkScheduling medicWorkScheduling) throws DAOException {
        addMedicWorkAddress(medicWorkAddress);
        medicWorkScheduling.setMedicWorkSchedulingId(medicWorkAddress.getMedicWorkAddressId());
        addMedicWorkScheduling(medicWorkScheduling);
    }

    public void removeMedicWorkAddressAndScheduling(MedicWorkAddress medicWorkAddress) throws DAOException {
        removeMedicWorkScheduling(new MedicWorkScheduling(medicWorkAddress.getMedicWorkAddressId()));
        removeMedicWorkAddress(medicWorkAddress);
    }

    public void addMedicWorkAddressList(List<MedicWorkAddress> medicWorkAddressList) throws DAOException {
        StringBuilder argumentsSize = new StringBuilder();
        for (int i = 0; i < medicWorkAddressList.size(); i++) {
            argumentsSize.append("(?,?,?,?,?),");
            int medicWorkAddressId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.medicWorkAddress);
            medicWorkAddressList.get(i).setMedicWorkAddressId(medicWorkAddressId);
        }
        argumentsSize.deleteCharAt(argumentsSize.length() - 1);
        String sql = "insert into medicWorkAddress values " + argumentsSize.toString();
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            int counter = 1;
            for (int i = 0; i < medicWorkAddressList.size(); i++) {
                stmt.setInt(counter++, medicWorkAddressList.get(i).getMedicWorkAddressId());
                stmt.setInt(counter++, medicWorkAddressList.get(i).getClinicProfile().getId());
                stmt.setString(counter++, medicWorkAddressList.get(i).getMedicWorkAddressComplement());
                stmt.setInt(counter++, medicWorkAddressList.get(i).getMedicSpeciality().getSpeciality().getSpecialityId());
                stmt.setInt(counter++, medicWorkAddressList.get(i).getMedicSpeciality().getMedicProfile().getId());
            }
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar os endereco de trabalho do medico", e);
        }
    }

    private void addMedicWorkAddress(MedicWorkAddress medicWorkAddress) throws DAOException {
        String sql = "insert into medicWorkAddress values (?,?,?,?,?)";
        int medicWorkAddressId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.medicWorkAddress);
        medicWorkAddress.setMedicWorkAddressId(medicWorkAddressId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicWorkAddressId);
            stmt.setInt(2, medicWorkAddress.getClinicProfile().getId());
            stmt.setString(3, medicWorkAddress.getMedicWorkAddressComplement());
            stmt.setInt(4, medicWorkAddress.getMedicSpeciality().getSpeciality().getSpecialityId());
            stmt.setInt(5, medicWorkAddress.getMedicSpeciality().getMedicProfile().getId());
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.medicWorkAddress, medicWorkAddressId);
            throw new DAOException("Falha ao adicionar o endereco de trabalho do medico", e);
        }
    }
    
    public List<MedicWorkAddress> getMedicWorkAddressList(Medic medic) throws DAOException {
        List<MedicWorkAddress> medicWorkAddressList = null;
        String sql = "select medicWorkAddressid, clinicName, medicWorkAddressComplement, specialityName from medicWorkAddress "
                + "join clinic on medicWorkAddress.clinicProfile_fk=clinic.clinicId "
                + "join speciality on medicWorkAddress.medicSpeciality_speciality_fk=speciality.specialityId "
                + "where medicSpeciality_medicProfile_fk=? order by clinicName";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getId());
            medicWorkAddressList = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MedicWorkAddress medicWorkAddress = new MedicWorkAddress();
                medicWorkAddress.setMedicWorkAddressId(rs.getInt("medicWorkAddressId"));
                medicWorkAddress.setMedicWorkAddressComplement(rs.getString("medicWorkAddressComplement"));
                ClinicProfile clinicProfile = new ClinicProfile();
                clinicProfile.setClinicName(rs.getString("clinicName"));                
                medicWorkAddress.setClinicProfile(clinicProfile);
                Speciality speciality = new Speciality();
                speciality.setSpecialityName(rs.getString("specialityName"));
                medicWorkAddress.setMedicSpeciality(new MedicSpeciality(null, speciality));
                medicWorkAddressList.add(medicWorkAddress);
            }
        } catch (SQLException e) {            
            throw new DAOException("Falha ao recuperar a lista de endereços de trabalho do medico", e);
        }
        return medicWorkAddressList;
    }

    private boolean existMedicWorkAddress(MedicWorkAddress medicWorkAddress) {
        String sql = "select medicWorkAddressId from medicWorkAddress where medicWorkAddressId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicWorkAddress.getMedicWorkAddressId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    private void removeMedicWorkAddress(MedicWorkAddress medicWorkAddress) throws DAOException {
        if (!existMedicWorkAddress(medicWorkAddress)) {
            throw new DAOException("Falha ao remover. O endereço de trabalho do medico não existe");
        }
        String sql = "delete from medicWorkAddress where medicWorkAddressId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicWorkAddress.getMedicWorkAddressId());
            stmt.execute();
            stmt.close();
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.medicWorkAddress, medicWorkAddress.getMedicWorkAddressId());
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover o endereco de trabalho do medico", e);
        }
    }

    public void addMedicWorkSchedulingList(List<MedicWorkScheduling> medicWorkSchedulingList) throws DAOException {
        StringBuilder argumentsSize = new StringBuilder();
        for (int i = 0; i < medicWorkSchedulingList.size(); i++) {
            argumentsSize.append("(?,?,?,?,?,?),");
        }
        argumentsSize.deleteCharAt(argumentsSize.length() - 1);
        String sql = "insert into medicWorkScheduling values " + argumentsSize.toString();
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            int counter = 1;
            for (int i = 0; i < medicWorkSchedulingList.size(); i++) {
                stmt.setInt(counter++, medicWorkSchedulingList.get(i).getMedicWorkSchedulingId());
                stmt.setInt(counter++, medicWorkSchedulingList.get(i).getMedicWorkSchedulingPerDay());
                stmt.setDate(counter++, medicWorkSchedulingList.get(i).getMedicWorkSchedulingDateLast());
                stmt.setInt(counter++, medicWorkSchedulingList.get(i).getMedicWorkSchedulingCounterOfDay());
                stmt.setString(counter++, medicWorkSchedulingList.get(i).getMedicWorkSchedulingInfo());
                stmt.setString(counter++, medicWorkSchedulingList.get(i).getMedicWorkSchedulingDaysOfWeek());
            }
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar as variaveis de trabalho do medico", e);
        }
    }

    private void addMedicWorkScheduling(MedicWorkScheduling medicWorkScheduling) throws DAOException {
        String sql = "insert into medicWorkScheduling values (?,?,?,?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicWorkScheduling.getMedicWorkSchedulingId());
            stmt.setInt(2, medicWorkScheduling.getMedicWorkSchedulingPerDay());
            stmt.setDate(3, medicWorkScheduling.getMedicWorkSchedulingDateLast());
            stmt.setInt(4, medicWorkScheduling.getMedicWorkSchedulingCounterOfDay());
            stmt.setString(5, medicWorkScheduling.getMedicWorkSchedulingInfo());
            stmt.setString(6, medicWorkScheduling.getMedicWorkSchedulingDaysOfWeek());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar as variaveis de trabalho do medico", e);
        }
    }

    public MedicWorkScheduling getMedicWorkScheduling(MedicWorkAddress medicWorkAddress) throws DAOException {
        MedicWorkScheduling medicWorkScheduling = null;
        String sql = "select * from medicWorkScheduling where medicWorkAddress_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicWorkAddress.getMedicWorkAddressId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                medicWorkScheduling = new MedicWorkScheduling();
                medicWorkScheduling.setMedicWorkSchedulingCounterOfDay(rs.getInt("medicWorkSchedulingCounterOfDay"));
                medicWorkScheduling.setMedicWorkSchedulingPerDay(rs.getInt("medicWorkSchedulingPerDay"));
                medicWorkScheduling.setMedicWorkSchedulingDateLast(rs.getDate("medicWorkSchedulingDateLast", Calendar.getInstance()));
                medicWorkScheduling.setMedicWorkSchedulingInfo(rs.getString("medicWorkSchedulingInfo"));
                medicWorkScheduling.setMedicWorkSchedulingDaysOfWeek(rs.getString("medicWorkSchedulingDaysOfWeek"));
                medicWorkScheduling.setMedicWorkSchedulingDaysOfWeek(rs.getString("medicWorkSchedulingDaysOfWeek"));
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao adquirir as informações de horario do medico", e);
        }
        return medicWorkScheduling;
    }

    public void updateMedicWorkSchedulingFromConsult(MedicWorkScheduling medicWorkScheduling) throws DAOException {
        String sql = "update medicWorkScheduling set "
                + "medicWorkSchedulingCounterOfDay=?, medicWorkSchedulingDateLast=? "
                + "where medicWorkAddress_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicWorkScheduling.getMedicWorkSchedulingCounterOfDay());
            stmt.setDate(2, medicWorkScheduling.getMedicWorkSchedulingDateLast());
            stmt.setInt(3, medicWorkScheduling.getMedicWorkSchedulingId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover as variaveis de trabalho do medico", e);
        }
    }

    private void removeMedicWorkScheduling(MedicWorkScheduling medicWorkScheduling) throws DAOException {
        String sql = "delete from medicWorkScheduling where medicWorkAddress_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicWorkScheduling.getMedicWorkSchedulingId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover as variaveis de trabalho do medico", e);
        }
    }

    void updateMedicEvaluation(Medic medic) throws DAOException {
        String sql = "select medicEvaluationCount from medicEvaluation where medicProfile_fk=?";
        try {
            PreparedStatement stmt = super.connection.prepareStatement(sql);
            stmt.setInt(1, medic.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int evaluationCount = rs.getInt("medicEvaluationCount");
                stmt.close();
                sql = "update medicEvaluation set "
                        + "medicEvaluationAvg = (select avg(evaluationScore) from evaluation where medicEvaluation_fk=?), medicEvaluationCount=? "
                        + "where medicProfile_fk=?";
                stmt = super.connection.prepareStatement(sql);
                stmt.setInt(1, medic.getId());
                stmt.setInt(2, evaluationCount + 1);
                stmt.setInt(3, medic.getId());
                stmt.execute();
                stmt.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao atualizar o perfil de avaliação do medico", e);
        }
    }
    
    public MedicProfile getMedicProfileWithWorkInfoListForScheduleConsult(Medic medic) throws DAOException {
        MedicProfile medicProfile = null;
        String sql = "select medicUser_fk, medicName, medicWorkAddressId, medicWorkSchedulingDateLast, medicWorkSchedulingCounterOfDay,"
                + "medicWorkSchedulingPerDay, medicWorkSchedulingDaysOfWeek, clinicName, specialityId, specialityName, specialityPriv "
                + "from medic "
                + "join medicWorkAddress on medic.medicUser_fk=MedicWorkAddress.medicSpeciality_medicProfile_fk "
                + "join medicWorkScheduling on medicWorkAddress.medicWorkAddressId=medicWorkScheduling.medicWorkAddress_fk "
                + "join clinic on medicWorkAddress.clinicProfile_fk=clinic.clinicId "
                + "join speciality on medicWorkAddress.medicSpeciality_speciality_fk=speciality.specialityId "
                + "where medicUser_fk=? "
                + "order by medicName, specialityName";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getId());
            ResultSet rs = stmt.executeQuery();
            List<MedicWorkAddress> medicWorkAddressList = new ArrayList<>();
            medicProfile = new MedicProfile();
            int currentMedic = -1;
            while (rs.next()) {
                if (currentMedic != medic.getId()) {
                    currentMedic = medic.getId();                    
                    medicWorkAddressList = new ArrayList<>();
                    medicProfile.setId(medic.getId());
                    medicProfile.setMedicName(rs.getString("medicName"));
                }
                MedicWorkAddress medicWorkAddress = new MedicWorkAddress();
                medicWorkAddress.setMedicWorkAddressId(rs.getInt("medicWorkAddressId"));
                
                MedicWorkScheduling medicWorkScheduling = new MedicWorkScheduling();
                medicWorkScheduling.setMedicWorkSchedulingDateLast(rs.getDate("medicWorkSchedulingDateLast", Calendar.getInstance()));                
                medicWorkScheduling.setMedicWorkSchedulingDaysOfWeek(rs.getString("medicWorkSchedulingDaysOfWeek"));
                medicWorkScheduling.setMedicWorkSchedulingCounterOfDay(rs.getInt("medicWorkSchedulingCounterOfDay"));
                medicWorkScheduling.setMedicWorkSchedulingPerDay(rs.getInt("medicWorkSchedulingPerDay"));
                medicWorkAddress.setMedicWorkScheduling(medicWorkScheduling);
                
                ClinicProfile clinicProfile = new ClinicProfile();
                clinicProfile.setClinicName(rs.getString("clinicName"));
                medicWorkAddress.setClinicProfile(clinicProfile);
                
                Speciality speciality = new Speciality();
                speciality.setSpecialityId(rs.getInt("specialityId"));
                speciality.setSpecialityName(rs.getString("specialityName"));
                speciality.setSpecialityPriv(rs.getBoolean("specialityPriv"));
                medicWorkAddress.setMedicSpeciality(new MedicSpeciality(medicProfile, speciality));
                
                medicWorkAddressList.add(medicWorkAddress);
            }
            if (currentMedic == medic.getId())
                medicProfile.setMedicWorkAddressList(medicWorkAddressList);
        } catch (SQLException e) {
            throw new DAOException("Falha ao obter as informações dos médicos", e);
        }
        return medicProfile;
    }

}
