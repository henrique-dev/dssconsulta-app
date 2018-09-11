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
        
    public final static int TYPE_PATIENT_1 = 1;
    public final static int TYPE_PATIENT_2 = 2;
    public final static int TYPE_PATIENT_3 = 3;
    public final static int TYPE_PATIENT_4 = 4;
    public final static int TYPE_PATIENT_5 = 5;
    public final static int TYPE_PATIENT_6 = 6;
    public final static int TYPE_PATIENT_7 = 7;
    public final static int TYPE_PATIENT_8 = 8;
    public final static int TYPE_PATIENT_9 = 9;
    public final static int TYPE_PATIENT_10 = 10;
    public final static int TYPE_MEDIC_1 = 11;
    public final static int TYPE_MEDIC_3 = 13;
    public final static int TYPE_MEDIC_4 = 14;
    public final static int TYPE_MEDIC_5 = 15;
    public final static int TYPE_MEDIC_6 = 16;
    public final static int TYPE_MEDIC_7 = 17;
    public final static int TYPE_MEDIC_8 = 18;
    public final static int TYPE_MEDIC_9 = 19;
    public final static int TYPE_MEDIC_10 = 20;
    public final static int TYPE_PROFILE_PHOTO_PATIENT = 21;
    public final static int TYPE_PROFILE_PHOTO_MEDIC = 22;
    
    private int fileId;
    private String fileName;
    private String filePath;
    private Timestamp fileUploadDate;
    private int fileLength;
    private int type;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }        

    public int getFileLength() {
        return fileLength;
    }

    public void setFileLength(int fileLength) {
        this.fileLength = fileLength;
    }            
    
}
