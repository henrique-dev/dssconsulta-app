/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.dao;

import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.models.File;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class FileDAO extends BasicDAO {

    public FileDAO(Connection connection) {
        super(connection);
    }

    private void addFile(File file) throws DAOException {
        String sql = "insert into file values (?,?,?,?,?)";
        int fileId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.file);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            file.setFileId(fileId);
            stmt.setInt(1, fileId);
            stmt.setString(2, file.getFileName());
            stmt.setString(3, file.getFilePath());
            stmt.setObject(4, file.getFileUploadDate());
            stmt.setInt(5, file.getFileLength());
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.file, fileId);
            throw new DAOException("Falha ao armazenar as informações do arquivo", e);
        }
    }

    /*
    public File getFile(User user, int fileId, int type) throws DAOException {
        File file = null;
        String sql;
        if (type == File.TYPE_PROFILE_PHOTO_PATIENT) {
            sql = "select fileName, filePath from patientProfileFile join "
                    + "file on patientProfileFile.file_fk=file.fileId where patientProfile_fk=?";
        } else if (type == File.TYPE_PROFILE_PHOTO_MEDIC) {
            sql = "select fileName, filePath from medicProfileFile join "
                    + "file on medicProfileFile.file_fk=file.fileId where medicProfile_fk=?";
        } else if (user instanceof Patient) {
            sql = "select fileName, filePath, patientAccountFileType from patientAccountFile join "
                    + "file on patientAccountFile.file_fk=file.fileId where patientProfile_fk=?";
        } else
            throw new DAOException("Não autorizado");
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, user.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                file = new File();
                file.setFileId(fileId);                    
                file.setFileName(rs.getString("fileName"));
                file.setFilePath(rs.getString("filePath"));
                System.out.println(file.getFilePath());
                if (type != File.TYPE_PROFILE_PHOTO_MEDIC && type != File.TYPE_PROFILE_PHOTO_PATIENT)
                    file.setType(rs.getInt("patientAccountFileType"));
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao adquirir informações do arquivo", e);
        }        
        return file;
    }*/
    
    public List<File> getPatientFileList(Patient patient) throws DAOException {
        List<File> fileList = null;
        String sql = "select fileId, fileLength, patientAccountFileType from patientAccountFile "
                + "join file on patientAccountFile.file_fk=file.fileId where patientAccount_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getId());
            ResultSet rs = stmt.executeQuery();
            fileList = new ArrayList<>();
            while (rs.next()) {
                File file = new File();
                file.setFileId(rs.getInt("fileId"));
                file.setFileLength(rs.getInt("fileLength"));
                file.setType(rs.getInt("patientAccountFileType"));
                fileList.add(file);
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao adquirir a lista de arquivos do paciente", e);
        }         
        return fileList;
    }
    
    public File getPublicFile(int fileId) throws DAOException {
        File file = null;
        String sql = "select filePath from file where fileId=?";        
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, fileId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                file = new File();
                file.setFileId(fileId);                                    
                file.setFilePath(rs.getString("filePath"));                
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao adquirir informações do arquivo", e);
        }        
        return file;
    }

    public File getFileProfile(int id, String table) throws DAOException {
        File file = null;
        String sql = "select fileId, filePath from " + table + "ProfileFile "
                + "join file on " + table + "ProfileFile.file_fk=file.fileId where " + table + "Profile_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                file = new File();
                file.setFileId(rs.getInt("fileId"));
                file.setFilePath(rs.getString("filePath"));
            }
        } catch (SQLException e) {
            throw new DAOException("Falha ao verificar a existencia do arquivo", e);
        }
        return file;
    }

    public void addFileProfile(File file, User user, String table) throws DAOException {
        addFile(file);
        String sql = "insert into " + table + "ProfileFile values (?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, user.getId());
            stmt.setInt(2, file.getFileId());            
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar as informações do arquivo", e);
        }
    }

    public void updateFileProfile(File file) throws DAOException {
        String sql = "update file set fileName=?, filePath=?, fileUploadDate=?, fileLength=? where fileId=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setString(1, file.getFileName());
            stmt.setString(2, file.getFilePath());
            stmt.setObject(3, file.getFileUploadDate());
            stmt.setInt(4, file.getFileLength());
            stmt.setInt(5, file.getFileId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao atualizar as informações do arquivo", e);
        }
    }

    public void addFilePatientAccount(File file, User user) throws DAOException {
        addFile(file);
        String sql = "insert into patientAccountFile values (?,?,?)";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, user.getId());
            stmt.setInt(2, file.getFileId());
            stmt.setInt(3, file.getType());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar as informações do arquivo", e);
        }
    }

}
