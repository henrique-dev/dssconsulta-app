/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.controllers;

import com.br.headred.sma.dao.ClinicDAO;
import com.br.headred.sma.dao.ConsultDAO;
import com.br.headred.sma.dao.MedicDAO;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.ClinicProfile;
import com.br.headred.sma.models.Consult;
import com.br.headred.sma.models.MedicProfile;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.Speciality;
import java.sql.Connection;
import java.sql.SQLException;
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
        try (Connection con = new ConnectionFactory().getConnection()) {
            List<Consult> consultList = new ConsultDAO(con).getConsultList(new Patient(patientId));
            model.addAttribute(consultList);
            return "patient/agenda";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:PrincipalPaciente";
    }
    
    @RequestMapping("Paciente/MinhaAgenda/MinhaConsulta")
    public String minhaConsulta(int consultId, Model model) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            Consult consult = new ConsultDAO(con).getConsult(consultId);
            model.addAttribute(consult);
            return "patient/agenda-consulta";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:PrincipalPaciente";
    }
    
    @RequestMapping("Paciente/ListarEspecialidades")
    public String listarEspecialidades(Model model) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            List<Speciality> specialityList = new MedicDAO(con).getSpecialityList();
            model.addAttribute(specialityList);            
            return "patient/speciality-list";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:PrincipalPaciente";
    }
    
    @RequestMapping("Paciente/ListarMedicos")
    public String listarMedicos(int specialityId, Model model) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            List<MedicProfile> medicProfileList = new MedicDAO(con).getMedicProfileList(specialityId);
            model.addAttribute(medicProfileList); 
            return "patient/medic-list";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:PrincipalPaciente";
    }
    
    @RequestMapping("Paciente/PerfilMedico")
    public String perfilMedico(int medicId, Model model) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            MedicProfile medicProfile = new MedicDAO(con).getMedicProfile(medicId);
            model.addAttribute(medicProfile);
            return "patient/medic-profile";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:PrincipalPaciente";
    }
    
    @RequestMapping("Paciente/PerfilClinica")
    public String perfilClinica(int clinicId, Model model) {
        try (Connection con = new ConnectionFactory().getConnection()){            
            ClinicProfile clinicProfile = new ClinicDAO(con).getClinicProfile(clinicId);
            model.addAttribute(clinicProfile);
            return "patient/clinic-profile";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:PrincipalPaciente";
    }
    
}
