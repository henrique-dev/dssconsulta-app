/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.dao;

import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.models.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class FileDAO extends BasicDAO {
    
    public FileDAO(Connection connection) {
        super(connection);
    }
    
    public void addFile(File file) throws DAOException {
        String sql = "insert into file values (?,?,?,?)";
        int fileId = new SystemDAO(super.connection).getNextId(SystemDAO.Table.file);
        try (PreparedStatement stmt = super.connection.prepareStatement(sql)) {
            stmt.setInt(1, file.getFileId());
            stmt.setString(2, file.getFileName());
            stmt.setString(3, file.getFilePath());
            stmt.setObject(4, file.getFileUploadDate());
            stmt.execute();
        } catch (SQLException e) {
            new SystemDAO(super.connection).releaseId(SystemDAO.Table.file, fileId);
            throw new DAOException("Falha ao armazenar o arquivo", e);
        }
    }
    
}
