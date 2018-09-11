/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.dao;

import com.br.headred.sma.exceptions.ConsultWithPrivilegesException;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.exceptions.DuplicateException;
import com.br.headred.sma.models.AccountSpeciality;
import com.br.headred.sma.models.ClinicProfile;
import com.br.headred.sma.models.Consult;
import com.br.headred.sma.models.File;
import com.br.headred.sma.models.Medic;
import com.br.headred.sma.models.MedicProfile;
import com.br.headred.sma.models.MedicSpeciality;
import com.br.headred.sma.models.MedicWorkAddress;
import com.br.headred.sma.models.MedicWorkScheduling;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.PatientProfile;
import com.br.headred.sma.models.Speciality;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
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
        if (existAccountSpecialityForAdd(accountSpeciality)) {
            return;
        }
        String sql = "insert into accountSpeciality values (?,?,?,?,?,?,?)";
        int accountSpecialityId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.accountSpeciality);
        accountSpeciality.setAccountSpecialityId(accountSpecialityId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, accountSpecialityId);
            stmt.setInt(2, accountSpeciality.getPatientAccount().getPatientAccountId());
            stmt.setObject(3, accountSpeciality.getPatientAccountSpecialityCreationDate());
            stmt.setNull(4, Types.DATE);
            stmt.setBoolean(5, accountSpeciality.isPatientAccountSpecialityUsed());
            stmt.setInt(6, accountSpeciality.getMedicSpeciality().getSpeciality().getSpecialityId());
            stmt.setInt(7, accountSpeciality.getMedicSpeciality().getMedicProfile().getId());
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.accountSpeciality, accountSpecialityId);
            throw new DAOException("Falha ao adicionar um encaminhamento na conta", e);
        }
    }

    public List<String> getAccountSpecialityList(Patient patient) throws DAOException {
        List<String> list = null;
        String sql = "select specialityName from accountSpeciality "
                + "join speciality on accountSpeciality.speciality_fk=speciality.specialityId";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getString("specialityName"));
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao recupear a lista de especialidades da conta", e);
        }
        return list;
    }

    public List<AccountSpeciality> getAccountSpecialityList(Speciality speciality) {
        return null;
    }

    private boolean existAccountSpecialityForAdd(AccountSpeciality accountSpeciality) {
        String sql = "select accountSpecialityId from accountSpeciality where accountSpecialityId=? and patientAccountSpecialityUsed=0";
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

    private boolean existAccountSpecialityForRemove(AccountSpeciality accountSpeciality) {
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
        if (!existAccountSpecialityForRemove(accountSpeciality)) {
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

    public void addConsultForAll(Consult consult) throws DAOException, DuplicateException, ConsultWithPrivilegesException {
        this.addConsult(consult);
        this.addMedicConsult(consult);
        this.addPatientConsult(consult);
    }

    private void setCalendarAvailableDate(Calendar calendar, String daysOfWorkOnWeek) {
        int counter = 0;
        for (int i = calendar.get(Calendar.DAY_OF_WEEK); i <= daysOfWorkOnWeek.length(); i++) {
            if (daysOfWorkOnWeek.charAt(i - 1) == '0') {
                counter++;
            } else {
                break;
            }
            if (i == daysOfWorkOnWeek.length()) {
                i = 0;
            }
        }
        calendar.add(Calendar.DAY_OF_MONTH, counter);
    }

    private int compareCalendars(Calendar currentDate, Calendar lastDate) {
        if (currentDate.get(Calendar.DAY_OF_YEAR) == lastDate.get(Calendar.DAY_OF_YEAR)) {
            if (currentDate.get(Calendar.YEAR) == lastDate.get(Calendar.YEAR)) {
                return 0;
            } else if (currentDate.get(Calendar.YEAR) < lastDate.get(Calendar.YEAR)) {
                return 1;
            } else {
                return -1;
            }
        } else if (currentDate.get(Calendar.DAY_OF_YEAR) < lastDate.get(Calendar.DAY_OF_YEAR)) {
            if (currentDate.get(Calendar.YEAR) <= lastDate.get(Calendar.YEAR)) {
                return 1;
            } else {
                return -1;
            }
        } else {
            if (currentDate.get(Calendar.YEAR) >= lastDate.get(Calendar.YEAR)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    private Timestamp getAvailableDateForConsult(MedicWorkScheduling medicWorkScheduling) throws DAOException {
        Calendar currentDate = Calendar.getInstance();
        Calendar lastDate = Calendar.getInstance();
        lastDate.setTime(medicWorkScheduling.getMedicWorkSchedulingDateLast());

        int diff = compareCalendars(currentDate, lastDate);

        if (diff > 0) { // HÁ CONSULTA AGENDADA
            int counterOfDay = medicWorkScheduling.getMedicWorkSchedulingCounterOfDay();
            int consultsPerDay = medicWorkScheduling.getMedicWorkSchedulingPerDay();
            if (counterOfDay >= consultsPerDay) { // CONSULTAS ENCERRADAS NO DIA, AGENDANDO PARA O PROXIMO DIA
                lastDate.add(Calendar.DAY_OF_MONTH, 1);
                setCalendarAvailableDate(lastDate, medicWorkScheduling.getMedicWorkSchedulingDaysOfWeek());
                lastDate.set(Calendar.HOUR_OF_DAY, 7);
                lastDate.set(Calendar.MINUTE, 0);
                medicWorkScheduling.setMedicWorkSchedulingCounterOfDay(1);
                medicWorkScheduling.setMedicWorkSchedulingDateLast(new Date(lastDate.getTimeInMillis()));
            } else { // AINDA HÁ CONSULTA OARA O DIA, AGENDANDO NO MESMO
                lastDate.set(Calendar.HOUR_OF_DAY, 7);
                lastDate.add(Calendar.MINUTE, 2 * (counterOfDay + 1));
                medicWorkScheduling.setMedicWorkSchedulingCounterOfDay(counterOfDay + 1);
            }
        } else { // NÃO HÁ CONSULTA AGENDADA, NOVO AGENDAGENTO NO DIA CRIADO
            lastDate = currentDate;
            lastDate.add(Calendar.DAY_OF_MONTH, 1);
            setCalendarAvailableDate(lastDate, medicWorkScheduling.getMedicWorkSchedulingDaysOfWeek());
            lastDate.set(Calendar.HOUR_OF_DAY, 7);
            lastDate.set(Calendar.MINUTE, 0);
            lastDate.set(Calendar.SECOND, 0);
            medicWorkScheduling.setMedicWorkSchedulingDateLast(new Date(lastDate.getTimeInMillis()));
            medicWorkScheduling.setMedicWorkSchedulingCounterOfDay(1);
        }

        new MedicDAO(connection).updateMedicWorkSchedulingFromConsult(medicWorkScheduling);
        return new Timestamp(lastDate.getTimeInMillis());
    }

    private boolean checkForDuplicatedConsult(Consult consult) throws DAOException {
        String sql = "select medicWorkAddress_fk from patientConsult "
                + "join consult on consult.consultId=patientConsult.consult_fk where patientConsult.patientProfile_fk=? and consultConsulted=0";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, consult.getPatientProfile().getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int medicWorkAddressId = rs.getInt("medicWorkAddress_fk");
                if (medicWorkAddressId == consult.getMedicWorkAddress().getMedicWorkAddressId()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao checar a duplicidade da consulta", e);
        }
        return false;
    }

    private boolean checkForSpecialityPrivConsult(Consult consult) throws DAOException {
        Speciality speciality = new MedicDAO(connection).getSpeciality(consult.getMedicSpeciality().getSpeciality().getSpecialityId());
        return speciality.isSpecialityPriv();
    }

    private void validateConsultWithPriv(Consult consult) throws DAOException, ConsultWithPrivilegesException {
        String sql = "select * from accountSpeciality where patientAccount_fk=? and patientAccountSpecialityUsed=0 "
                + "and speciality_fk=?";
        try {
            PreparedStatement stmt = super.connection.prepareStatement(sql);
            stmt.setInt(1, consult.getPatientProfile().getId());
            stmt.setInt(2, consult.getMedicSpeciality().getSpeciality().getSpecialityId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int accountSpecialityId = rs.getInt("accountSpecialityId");
                stmt.close();
                sql = "update accountSpeciality "
                        + "set patientAccountSpecialityUseDate=?, patientAccountSpecialityUsed=? where accountSpecialityId=?";
                stmt = super.connection.prepareStatement(sql);
                Timestamp currentDateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
                stmt.setObject(1, currentDateTime);
                stmt.setBoolean(2, true);
                stmt.setInt(3, accountSpecialityId);
                stmt.execute();
                stmt.close();
            } else {
                stmt.close();
                throw new ConsultWithPrivilegesException("O paciente não possui um encaminhamento");
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao validar a consulta", e);
        }
    }

    synchronized private void addConsult(Consult consult) throws DAOException, DuplicateException, ConsultWithPrivilegesException {
        if (checkForDuplicatedConsult(consult)) {
            throw new DuplicateException("A consulta ja existe com este medico");
        }
        if (checkForSpecialityPrivConsult(consult)) {
            validateConsultWithPriv(consult);
        }

        String sql = "insert into consult values (?,?,?,?,?,?,?,?)";

        int consultId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.consult);
        MedicWorkScheduling medicWorkScheduling = new MedicDAO(connection).getMedicWorkScheduling(consult.getMedicWorkAddress());

        Timestamp currentDateTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
        Timestamp consultForDateTime = getAvailableDateForConsult(medicWorkScheduling);

        consult.setConsultId(consultId);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, consultId);
            stmt.setObject(2, currentDateTime);
            stmt.setObject(3, consultForDateTime);
            stmt.setBoolean(4, consult.isConsultConsulted());
            stmt.setInt(5, consult.getMedicSpeciality().getSpeciality().getSpecialityId());
            stmt.setInt(6, consult.getMedicSpeciality().getMedicProfile().getId());
            stmt.setInt(7, consult.getMedicWorkAddress().getMedicWorkAddressId());
            stmt.setInt(8, consult.getPatientProfile().getId());
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.consult, consultId);
            if (e instanceof SQLIntegrityConstraintViolationException) {
                throw new DAOException("Informações fornecidas incoerentes", e);
            } else {
                throw new DAOException("Falha ao adicionar a consulta", e);
            }
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

    public Consult getNextConsult(MedicWorkAddress medicWorkAddress) throws DAOException {
        Consult consult = null;
        String sql = "select consultId, patientUser_fk, patientName, patientProfileBirthDate, patientProfileWeight, "
                + "patientProfileHeight, patientProfileBloodType, fileId, fileLength from consult "
                + "join patientConsult on consult.consultId=patientConsult.consult_fk "
                + "join patient on patientConsult.patientProfile_fk=patient.patientUser_fk "
                + "join patientProfile on patientConsult.patientProfile_fk=patientProfile.patient_fk "
                + "left join patientProfileFile on patientProfile.patient_fk=patientProfileFile.patientProfile_fk "
                + "join file on patientProfileFile.file_fk=file.fileId "
                + "where medicWorkAddress_fk=? and consultForDate<? order by consultForDate";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, medicWorkAddress.getMedicWorkAddressId());
            Calendar currentDateCalendar = Calendar.getInstance();
            currentDateCalendar.add(Calendar.DAY_OF_MONTH, 5);
            stmt.setObject(2, new Timestamp(currentDateCalendar.getTimeInMillis()));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                consult = new Consult();
                consult.setConsultId(rs.getInt("consultId"));
                PatientProfile patientProfile = new PatientProfile();
                patientProfile.setId(rs.getInt("patientUser_fk"));
                patientProfile.setPatientName(rs.getString("patientName"));
                patientProfile.setPatientProfileBirthDate(rs.getDate("patientProfileBirthDate"));
                patientProfile.setPatientProfileHeight(rs.getFloat("patientProfileHeight"));
                patientProfile.setPatientProfileWeight(rs.getFloat("patientProfileWeight"));
                patientProfile.setPatientProfileBloodType(rs.getString("patientProfileBloodType"));
                consult.setPatientProfile(patientProfile);
                try {                    
                    File file = new File();
                    file.setFileId(rs.getInt("fileId"));
                    file.setFileLength(rs.getInt("fileLength"));
                    patientProfile.setFile(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao recuperar a consulta", e);
        }
        return consult;
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
                consult.setConsultCreationDate((Timestamp) rs.getObject("consultCreationDate", Timestamp.class));
                consult.setConsultForDate((Timestamp) rs.getObject("consultForDate", Timestamp.class));
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
                    speciality.setSpecialityPriv(rs.getBoolean("specialityPriv"));
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

    public List<Consult> getAllConsultList(Patient patient) throws DAOException {
        List<Consult> consultList = null;
        String sql = "select consultId, consultForDate, medicName, specialityName, clinicName, medicWorkAddressComplement, consultConsulted from consult "
                + "join speciality on consult.medicSpeciality_speciality_fk=speciality.specialityId "
                + "join medic on consult.medicSpeciality_medicProfile_fk=medic.medicUser_fk "
                + "join medicWorkAddress on consult.medicWorkAddress_fk=medicWorkAddress.medicWorkAddressId "
                + "join clinic on medicWorkAddress.clinicProfile_fk=clinic.clinicId "
                + "where patientProfile_fk=? order by consultForDate";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getId());
            ResultSet rs = stmt.executeQuery();
            consultList = new ArrayList<>();
            while (rs.next()) {
                Consult consult = new Consult();
                consult.setConsultId(rs.getInt("consultId"));
                consult.setConsultForDate((Timestamp) rs.getObject("consultForDate", Timestamp.class));
                consult.setConsultConsulted(rs.getBoolean("consultConsulted"));

                MedicProfile medicProfile = new MedicProfile();
                medicProfile.setMedicName(rs.getString("medicName"));
                Speciality speciality = new Speciality();
                speciality.setSpecialityName(rs.getString("specialityName"));
                consult.setMedicSpeciality(new MedicSpeciality(medicProfile, speciality));

                MedicWorkAddress medicWorkAddress = new MedicWorkAddress();
                medicWorkAddress.setMedicWorkAddressComplement(rs.getString("medicWorkAddressComplement"));

                ClinicProfile clinicProfile = new ClinicProfile();
                clinicProfile.setClinicName(rs.getString("clinicName"));
                medicWorkAddress.setClinicProfile(clinicProfile);
                consult.setMedicWorkAddress(medicWorkAddress);

                consultList.add(consult);
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao adquirir a lista de consulta do paciente", e);
        }
        return consultList;
    }

    public List<Consult> getConsultList(Patient patient) throws DAOException {
        List<Consult> consultList = null;
        String sql = "select consultId, consultForDate, medicName, specialityName, clinicName, medicWorkAddressComplement, fileId, fileLength "
                + "from patientConsult "               
                + "join consult on patientConsult.consult_fk=consult.consultId "
                + "join speciality on consult.medicSpeciality_speciality_fk=speciality.specialityId "
                + "join medic on consult.medicSpeciality_medicProfile_fk=medic.medicUser_fk "
                + "join medicWorkAddress on consult.medicWorkAddress_fk=medicWorkAddress.medicWorkAddressId "
                + "join clinic on medicWorkAddress.clinicProfile_fk=clinic.clinicId "
                + "left join medicProfileFile on medic.medicUser_fk=medicProfileFile.medicProfile_fk "
                + " join file on medicProfileFile.file_fk=file.fileId "
                + "where patientConsult.patientProfile_fk=? order by consultForDate";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getId());
            ResultSet rs = stmt.executeQuery();
            consultList = new ArrayList<>();
            while (rs.next()) {
                Consult consult = new Consult();
                consult.setConsultId(rs.getInt("consultId"));
                consult.setConsultForDate((Timestamp) rs.getObject("consultForDate", Timestamp.class));

                MedicProfile medicProfile = new MedicProfile();
                medicProfile.setMedicName(rs.getString("medicName"));
                Speciality speciality = new Speciality();
                speciality.setSpecialityName(rs.getString("specialityName"));
                
                try {
                    File file = new File();
                    file.setFileId(rs.getInt("fileId"));
                    file.setFileLength(rs.getInt("fileLength"));
                    medicProfile.setFile(file);
                } catch (Exception e) {}
                
                consult.setMedicSpeciality(new MedicSpeciality(medicProfile, speciality));

                MedicWorkAddress medicWorkAddress = new MedicWorkAddress();
                medicWorkAddress.setMedicWorkAddressComplement(rs.getString("medicWorkAddressComplement"));

                ClinicProfile clinicProfile = new ClinicProfile();
                clinicProfile.setClinicName(rs.getString("clinicName"));
                medicWorkAddress.setClinicProfile(clinicProfile);
                consult.setMedicWorkAddress(medicWorkAddress);                                

                

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

    private void addPatientConsult(Consult consult) throws DAOException {
        String sql = "insert into patientConsult values (?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, consult.getConsultId());
            stmt.setInt(2, consult.getPatientProfile().getId());
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

    private void addMedicConsult(Consult consult) throws DAOException {
        String sql = "insert into medicConsult values (?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, consult.getConsultId());
            stmt.setInt(2, consult.getMedicSpeciality().getMedicProfile().getId());
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

    public int getPatientId(Consult consult) throws DAOException {
        String sql = "select patientProfile_fk from patientConsult where consult_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, consult.getConsultId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int patientId = rs.getInt("patientProfile_fk");
                stmt.close();
                return patientId;
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao recuperar o id do paciente", e);
        }
        return -1;
    }

}
