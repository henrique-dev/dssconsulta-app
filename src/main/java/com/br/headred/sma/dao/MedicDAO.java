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
        medicUser.setId(medicUserId);
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
            stmt.setInt(1, medic.getId());
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
            stmt.setInt(1, medicProfile.getId());
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
            stmt.setInt(1, medic.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) 
                return true;
        } catch (SQLException e) {}
        return false;
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
    
    private void addMedicWorkAddress(MedicWorkAddress medicWorkAddress) throws DAOException {
        String sql = "insert into medicWorkAddress values (?,?,?,?,?)";
        int medicWorkAddressId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.medicWorkAddress);
        medicWorkAddress.setMedicWorkAddressId(medicWorkAddressId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicWorkAddressId);
            stmt.setInt(2, medicWorkAddress.getClinicProfile().getClinicId());
            stmt.setString(3, medicWorkAddress.getMedicWorkAddressComplement());
            stmt.setInt(4, medicWorkAddress.getMedicSpeciality().getSpeciality().getSpecialityId());
            stmt.setInt(5, medicWorkAddress.getMedicSpeciality().getMedicProfile().getId());
            stmt.execute();
        } catch (SQLException e) {            
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.medicWorkAddress, medicWorkAddressId);
            throw new DAOException("Falha ao adicionar o endereco de trabalho do medico", e);
        }
    }        
    
    private boolean existMedicWorkAddress(MedicWorkAddress medicWorkAddress) {
        String sql = "select medicWorkAddressId from medicWorkAddress where medicWorkAddressId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicWorkAddress.getMedicWorkAddressId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                return true;
        } catch (SQLException e) {}
        return false;
    }
    
    private void removeMedicWorkAddress(MedicWorkAddress medicWorkAddress) throws DAOException {
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
    
    private void addMedicWorkScheduling(MedicWorkScheduling medicWorkScheduling) throws DAOException {
        String sql = "insert into medicWorkScheduling values (?,?,?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicWorkScheduling.getMedicWorkSchedulingId());
            stmt.setInt(2, medicWorkScheduling.getMedicWorkSchedulingPerDay());
            stmt.setDate(3, medicWorkScheduling.getMedicWorkSchedulingDateLast());
            stmt.setInt(4, medicWorkScheduling.getMedicWorkSchedulingCounterOfDay());
            stmt.setString(5, medicWorkScheduling.getMedicWorkSchedulingInfo());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar as variaveis de trabalho do medico", e);
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
    
}
