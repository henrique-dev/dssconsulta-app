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
public class MedicConsult {
    
    private long medicAgendaId;
    private Consult consult;
    private MedicProfile medicProfile;

    public long getMedicAgendaId() {
        return medicAgendaId;
    }

    public void setMedicAgendaId(long medicAgendaId) {
        this.medicAgendaId = medicAgendaId;
    }    

    public MedicProfile getMedicProfile() {
        return medicProfile;
    }

    public void setMedicProfile(MedicProfile medicProfile) {
        this.medicProfile = medicProfile;
    }

    public Consult getConsult() {
        return consult;
    }

    public void setConsult(Consult consult) {
        this.consult = consult;
    }        
    
}
