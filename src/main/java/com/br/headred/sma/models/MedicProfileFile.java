/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.models;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class MedicProfileFile {
    
    private MedicProfile medicProfile;
    private File file;

    public MedicProfile getMedicProfile() {
        return medicProfile;
    }

    public void setMedicProfile(MedicProfile medicProfile) {
        this.medicProfile = medicProfile;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }        
    
}
