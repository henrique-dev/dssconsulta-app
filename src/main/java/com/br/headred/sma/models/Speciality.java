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
public class Speciality {
    
    private int specialityId;
    private String specialityName;

    public Speciality() {
    }

    public Speciality(int specialityId) {
        this.specialityId = specialityId;
    }

    public Speciality(String specialityName) {
        this.specialityName = specialityName;
    }

    public Speciality(int specialityId, String specialityName) {
        this.specialityId = specialityId;
        this.specialityName = specialityName;
    }        

    public int getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(int specialityId) {
        this.specialityId = specialityId;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }        
    
}
