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
public class Medic extends User {
    
    private String medicName;
    private String medicCrm;   
    private MedicProfile medicProfile;
    
    public Medic() {
        super();
    }
    
    public Medic(int medicId) {
        super(medicId);
    }

    public String getMedicName() {
        return medicName;
    }

    public void setMedicName(String medicName) {
        this.medicName = medicName;
    }

    public String getMedicCrm() {
        return medicCrm;
    }

    public void setMedicCrm(String medicCrm) {
        this.medicCrm = medicCrm;
    }            

    public MedicProfile getMedicProfile() {
        return medicProfile;
    }

    public void setMedicProfile(MedicProfile medicProfile) {
        this.medicProfile = medicProfile;
    }        
            
}
