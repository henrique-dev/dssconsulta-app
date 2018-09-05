/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.models;

import java.sql.Timestamp;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class File {
    
    private int fileId;
    private String fileName;
    private String filePath;
    private Timestamp fileUploadDate;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }        

    public Timestamp getFileUploadDate() {
        return fileUploadDate;
    }

    public void setFileUploadDate(Timestamp fileUploadDate) {
        this.fileUploadDate = fileUploadDate;
    }        
    
}
