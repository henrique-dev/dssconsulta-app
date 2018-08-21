/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.controllers;

import com.br.headred.sma.dao.ConsultDAO;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.Consult;
import com.br.headred.sma.models.Patient;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
@Controller
public class PatientController {
    
    @RequestMapping("PrincipalPaciente")
    public String mainPatient() {
        return "patient/main";
    }   
    
    @RequestMapping("MinhaAgenda")
    public String minhaAgenda(int patientId, String sessionId, Model model) {
        try {
            List<Consult> consultList = new ConsultDAO(new ConnectionFactory().getConnection()).getConsultList(new Patient(patientId));
            model.addAttribute(consultList);
            return "patient/agenda";
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return "redirect:PrincipalPaciente";
    }
    
}
