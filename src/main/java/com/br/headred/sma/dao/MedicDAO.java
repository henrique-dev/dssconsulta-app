/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.dao;

import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.models.Clinic;
import com.br.headred.sma.models.Medic;
import com.br.headred.sma.models.MedicProfile;
import com.br.headred.sma.models.MedicSchedule;
import com.br.headred.sma.models.MedicSpeciality;
import com.br.headred.sma.models.MedicWorkAddress;
import com.br.headred.sma.models.Speciality;
import com.br.headred.sma.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Types;
import java.util.List;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class MedicDAO extends BasicDAO {
    
    public MedicDAO(Connection connection) {
        super(connection);
    }
    
    public void addMedic(Medic medic) throws DAOException {
        addMedicUser(medic);
        addMedicInfo(medic);
        addMedicProfile(medic.getMedicProfile());
    }
    
    private void addMedicUser(User medicUser) throws DAOException {
        String sql = "insert into medicUser values (?,?,?,?)";
        int medicUserId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.medicUser);
        medicUser.setUserId(medicUserId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicUserId);
            stmt.setString(2, medicUser.getUserName());
            stmt.setString(3, medicUser.getUserPassword());
            stmt.setNull(4, Types.VARCHAR);
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.medicUser, medicUserId);
            if (e instanceof SQLIntegrityConstraintViolationException)
                throw new DAOException("Falha ao adicionar um usuário para o medico. O nome de usuário já existe", e);
            else
                throw new DAOException("Falha ao adicionar um usuário para o médico", e);
        }
    }
    
    private void addMedicInfo(Medic medic) throws DAOException {
        String sql = "insert into medic values (?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getUserId());
            stmt.setString(2, medic.getMedicName());
            stmt.setString(3, medic.getMedicCrm());
            stmt.execute();
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException)
                throw new DAOException("Falha ao adicionar as informações do médico. O crm já existe", e);
            else
                throw new DAOException("Falha ao adicionar as informações do médico", e);
        }
    }
    
    private void addMedicProfile(MedicProfile medicProfile) throws DAOException {
        String sql = "insert into medicProfile values (?,?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicProfile.getMedicProfileId());
            stmt.setString(2, medicProfile.getMedicProfileBio());
            stmt.setInt(3, medicProfile.getMedicProfileExpAge());
            stmt.setString(4, medicProfile.getMedicProfileInfoCompl());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar o perfil do médico", e);
        }
    }       
    
    public boolean existMedic(Medic medic) {
        String sql = "select medicUserId from medicUser where medicUserId=?";        
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getUserId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) 
                return true;
        } catch (SQLException e) {}
        return false;
    }
    
    public Medic getMedicFastDescribe(String crm) {
        return null;
    }
    
    public Medic getMedicFastDescribe(int medicId) {
        return null;
    }
    
    public Medic getMedicFullDescribe(String crm) {
        return null;
    }
    
    public Medic getMedicFullDescribe(int medicId) {
        return null;
    }
    
    public List<Medic> getMedicFastDescribeList(String name) {
        return null;
    }
    
    public List<Medic> getMedicFulltDescribeList(String name) {
        return null;
    }
    
    public List<Medic> getMedicFastDescribeList(Speciality speciality) {
        return null;
    }
    
    public List<Medic> getMedicFastDescribeList(Clinic clinic) {
        return null;
    }
    
    public void removeMedic(Medic medic) throws DAOException {
        if (!existMedic(medic))
            throw new DAOException("Falha ao remover. O medico não existe");
        removeMedicProfile(medic);
        removeMedicInfo(medic);
        removeMedicUser(medic);
    }
    
    private void removeMedicUser(Medic medic) throws DAOException {
        String sql = "delete from medicUser where medicUserId=?";        
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getUserId());            
            stmt.execute();
            stmt.close();
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.medicUser, medic.getUserId());
        } catch (SQLException e) {            
            throw new DAOException("Falha ao remover o usuário do médico", e);
        }
    }        
    
    private void removeMedicInfo(Medic medic) throws DAOException {
        String sql = "delete from medic where medicUser_fk=?";        
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getUserId());            
            stmt.execute();                        
        } catch (SQLException e) {            
            throw new DAOException("Falha ao remover as informações do médico", e);
        }
    }        
    
    private void removeMedicProfile(Medic medic) throws DAOException {
        String sql = "delete from medicProfile where medic_fk=?";        
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getUserId());            
            stmt.execute();                      
        } catch (SQLException e) {            
            throw new DAOException("Falha ao remover o perfil do médico", e);
        }
    }        
    
    public void addSpeciality(Speciality speciality) throws DAOException {
        String sql = "insert into speciality values (?,?)";
        int specialityIndex = new SystemDAO(super.connection).getNextId(SystemDAO.Table.speciality);
        speciality.setSpecialityId(specialityIndex);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, specialityIndex);
            stmt.setString(2, speciality.getSpecialityName());
            stmt.execute();
        } catch (SQLException e) {       
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.speciality, specialityIndex);
            throw new DAOException("Falha ao adicionar a especialidade", e);
        }
    }        
    
    public List<Speciality> getSpecialityList() {
        return null;
    }
    
    public boolean existSpeciality(Speciality speciality) {
        String sql = "select specialityId from speciality where specialityId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, speciality.getSpecialityId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return true;
        } catch (SQLException e) {}
        return false;
    }
    
    public void removeSpeciality(Speciality speciality) throws DAOException {
        if (!existSpeciality(speciality))
            throw new DAOException("Falha ao remover. A especialidade não existe");
        String sql = "delete from speciality where specialityId=?";                
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, speciality.getSpecialityId());            
            stmt.execute();
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.speciality, speciality.getSpecialityId());
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover a especialidade", e);
        }
    }
    
    public void addMedicSpeciality(MedicSpeciality medicSpeciality) throws DAOException {
        String sql = "insert into medicSpeciality values (?,?)";                
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicSpeciality.getMedicProfile().getMedicProfileId());
            stmt.setInt(2, medicSpeciality.getSpeciality().getSpecialityId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar a especialidade para o medico", e);
        }
    }
    
    public List<MedicSpeciality> getMedicSpecialityList() {
        return null;
    }
    
    public List<MedicSpeciality> getMedicSpecialityList(Medic medic) {
        return null;
    }        
    
    public void removeMedicSpeciality(MedicSpeciality medicSpeciality) throws DAOException {
        String sql = "delete from medicSpeciality where medicProfile_fk=? and speciality_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicSpeciality.getMedicProfile().getMedicProfileId());
            stmt.setInt(2, medicSpeciality.getSpeciality().getSpecialityId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover a especialidade do medico", e);
        }
    }
    
    public void removeAllMedicSpeciality(Medic medic) throws DAOException {
        String sql = "delete from medicSpeciality where medicProfile_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medic.getUserId());            
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao remover as especialidades do medico", e);
        }
    }
    
    public void addMedicSchedule(MedicSchedule medicSchedule) throws DAOException {
        String sql = "insert into medicSchedule values (?,?,?,?)";
        int medicScheduleId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.medicSchedule);
        medicSchedule.setMedicScheduleId(medicScheduleId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicScheduleId);
            stmt.setInt(2, medicSchedule.getMedicProfile().getMedicProfileId());
            stmt.setInt(3, medicSchedule.getCliProfile().getClinicProfileId());
            stmt.setString(4, medicSchedule.getSchedule());
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.medicSchedule, medicScheduleId);
            throw new DAOException("Falha ao adicionar um horario para o medico", e);
        }
    }
    
    public MedicSchedule getMedicSchedule(Medic medic, Clinic clinic) {
        return null;
    }
    
    public List<MedicSchedule> getMedicScheduleList(Medic medic) {
        return null;
    }
    
    public List<MedicSchedule> getMedicScheduleList(Clinic clinic) {
        return null;
    }
    
    public boolean existMedicSchedule(MedicSchedule medicSchedule) {
        String sql = "select medicScheduleId from medicSchedule where medicScheduleId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicSchedule.getMedicScheduleId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return true;
        } catch (SQLException e) {}
        return false;
    }
    
    public void removeMedicSchedule(MedicSchedule medicSchedule) throws DAOException {
        if (!existMedicSchedule(medicSchedule))
            throw new DAOException("Falha ao remover. O horario do medico não existe");
        String sql = "delete from medicSchedule where medicScheduleId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicSchedule.getMedicScheduleId());            
            stmt.execute();
            stmt.close();
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.medicSchedule, medicSchedule.getMedicScheduleId());
        } catch (SQLException e) {            
            throw new DAOException("Falha ao remover o horario do medico", e);
        }
    }
    
    public void addMedicWorkAddress(MedicWorkAddress medicWorkAddress) throws DAOException {
        String sql = "insert into medicWorkAddress values (?,?,?,?)";
        int medicWorkAddressId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.medicWorkAddress);
        medicWorkAddress.setMedicWorkAddressId(medicWorkAddressId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicWorkAddressId);
            stmt.setInt(2, medicWorkAddress.getMedicProfile().getMedicProfileId());
            stmt.setInt(3, medicWorkAddress.getClinicProfile().getClinicProfileId());
            stmt.setString(4, medicWorkAddress.getMedicWorkAddressComplement());
            stmt.execute();
        } catch (SQLException e) {            
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.medicWorkAddress, medicWorkAddressId);
            throw new DAOException("Falha ao adicionar o endereco de trabalho do medico", e);
        }
    }
    
    public MedicWorkAddress getMedicWorkAddress(Medic medic, Clinic clinic) {
        return null;
    }
    
    public List<MedicWorkAddress> getMedicWorkAddressList(Medic medic) {
        return null;
    }
    
    public List<MedicWorkAddress> getMedicWorkAddressList(Clinic clinic) {
        return null;
    }
    
    public boolean existMedicWorkAddress(MedicWorkAddress medicWorkAddress) {
        String sql = "select medicWorkAddressId from medicWorkAddress where medicWorkAddressId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicWorkAddress.getMedicWorkAddressId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return true;
        } catch (SQLException e) {}
        return false;
    }
    
    public void removeMedicWorkAddress(MedicWorkAddress medicWorkAddress) throws DAOException {
        if (!existMedicWorkAddress(medicWorkAddress))
            throw new DAOException("Falha ao remover. O endereço de trabalho do medico não existe");
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
    
}
