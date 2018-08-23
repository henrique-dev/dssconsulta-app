/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.controllers;

import com.br.headred.sma.dao.ConsultDAO;
import com.br.headred.sma.dao.MedicDAO;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.Consult;
import com.br.headred.sma.models.MedicProfile;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.Speciality;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
@Controller
public class PatientController {   
    
    @RequestMapping("Paciente")
    public String mainPatient() {
        return "patient/main";
    }   
    
    @RequestMapping("Paciente/MinhaAgenda")
    //@PostMapping("PrincipalPaciente/MinhaAgenda")
    public String minhaAgenda(int patientId, Model model) {
        try {
            List<Consult> consultList = new ConsultDAO(new ConnectionFactory().getConnection()).getConsultList(new Patient(patientId));
            model.addAttribute(consultList);
            return "patient/agenda";
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return "redirect:PrincipalPaciente";
    }
    
    @RequestMapping("Paciente/MinhaAgenda/MinhaConsulta")
    public String minhaConsulta(int consultId, Model model) {
        try {
            Consult consult = new ConsultDAO(new ConnectionFactory().getConnection()).getConsult(consultId);
            model.addAttribute(consult);
            return "patient/agenda-consulta";
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return "redirect:PrincipalPaciente";
    }
    
    @RequestMapping("Paciente/ListarEspecialidades")
    public String listarEspecialidades(Model model) {
        try {
            List<Speciality> specialityList = new MedicDAO(new ConnectionFactory().getConnection()).getSpecialityList();
            model.addAttribute(specialityList);            
            return "patient/speciality-list";
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return "redirect:PrincipalPaciente";
    }
    
    @RequestMapping("Paciente/ListarMedicos")
    public String listarMedicos(int specialityId, Model model) {
        try {
            List<MedicProfile> medicProfileList = new MedicDAO(new ConnectionFactory().getConnection()).getMedicProfileList(specialityId);
            model.addAttribute(medicProfileList); 
            return "patient/medic-list";
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return "redirect:PrincipalPaciente";
    }
    
}
