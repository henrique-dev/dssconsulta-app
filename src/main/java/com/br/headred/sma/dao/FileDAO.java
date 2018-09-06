/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.dao;

import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.models.File;
import com.br.headred.sma.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class FileDAO extends BasicDAO {

    public FileDAO(Connection connection) {
        super(connection);
    }    

    private void addFile(File file) throws DAOException {
        String sql = "insert into file values (?,?,?,?)";
        int fileId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.file);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            file.setFileId(fileId);
            stmt.setInt(1, fileId);
            stmt.setString(2, file.getFileName());
            stmt.setString(3, file.getFilePath());
            stmt.setObject(4, file.getFileUploadDate());
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.file, fileId);
            throw new DAOException("Falha ao armazenar as informações do arquivo", e);
        }
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
            stmt.setInt(1, file.getFileId());
            stmt.setInt(2, user.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao adicionar as informações do arquivo", e);
        }
    }

    public void updateFileProfile(File file, User user, String table) throws DAOException {
        String sql = "update " + table + "ProfileFile set file_fk=? where " + table + "Profile_fk=?";
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, file.getFileId());
            stmt.setInt(2, user.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new DAOException("Falha ao atualizar as informações do arquivo", e);
        }
    }

    private void addFileToPatientAccount(File file, User user) {
            
    }    

}
