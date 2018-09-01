/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.controllers;

import com.br.headred.sma.dao.ClinicDAO;
import com.br.headred.sma.dao.ConsultDAO;
import com.br.headred.sma.dao.MedicDAO;
import com.br.headred.sma.dao.PatientDAO;
import com.br.headred.sma.exceptions.ConsultWithPrivilegesException;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.exceptions.DuplicateException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.ClinicProfile;
import com.br.headred.sma.models.Consult;
import com.br.headred.sma.models.Evaluation;
import com.br.headred.sma.models.MedicProfile;
import com.br.headred.sma.models.MedicSpeciality;
import com.br.headred.sma.models.MedicWorkAddress;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.PatientProfile;
import com.br.headred.sma.models.Speciality;
import com.br.headred.sma.models.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
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
    
    @PostMapping("Paciente/MeuPerfil")
    public String meuPerfil(HttpSession session, Model model) {        
        try (Connection con = new ConnectionFactory().getConnection()) {
            int patientId = ((User)session.getAttribute("user")).getId();
            System.out.println(patientId);
            PatientProfile patientProfile = new PatientDAO(con).getPatientProfile(patientId); 
            model.addAttribute(patientProfile);            
            return "patient/profile-patient-data";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:../Paciente"; 
    }

    @RequestMapping("Paciente/MinhaAgenda")
    //@PostMapping("PrincipalPaciente/MinhaAgenda")
    public String minhaAgenda(Model model, HttpSession session) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            int patientId = ((User)session.getAttribute("user")).getId();
            List<Consult> consultList = new ConsultDAO(con).getConsultList(new Patient(patientId));
            model.addAttribute(consultList);
            return "patient/agenda-data";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:../Paciente";
    }

    @RequestMapping("Paciente/MinhaAgenda/MinhaConsulta")
    public String minhaConsulta(int consultId, Model model) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            Consult consult = new ConsultDAO(con).getConsult(consultId);
            model.addAttribute(consult);
            return "patient/agenda-consult";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:../MinhaAgenda";
    }

    @RequestMapping("Paciente/ListarEspecialidades")
    public String listarEspecialidades(Model model) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            List<Speciality> specialityList = new MedicDAO(con).getSpecialityList();
            model.addAttribute(specialityList);
            return "patient/list-speciality-data";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:../Paciente";
    }

    @RequestMapping("Paciente/ListarMedicos")
    public String listarMedicos(int keyForSearch, Model model) {        
        try (Connection con = new ConnectionFactory().getConnection()) {            
            List<MedicProfile> medicProfileList = new MedicDAO(con).getMedicProfileList(keyForSearch);
            model.addAttribute(medicProfileList);            
            return "patient/list-medic-data";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:../Paciente";
    }

    @RequestMapping("Paciente/PerfilMedico")
    public String perfilMedico(int medicId, Model model) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            MedicProfile medicProfile = new MedicDAO(con).getMedicProfile(medicId);
            model.addAttribute(medicProfile);
            return "patient/profile-medic-data";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:../Paciente";
    }

    @RequestMapping("Paciente/PerfilUnidadeSaude")
    public String perfilClinica(int clinicId, Model model) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            ClinicProfile clinicProfile = new ClinicDAO(con).getClinicProfile(clinicId);
            model.addAttribute(clinicProfile);
            return "patient/profile-clinic";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:../Paciente";
    }

    @RequestMapping("Paciente/AgendarConsulta")
    public String agendarConsulta(int patientId, int medicId, int specialityId, int medicWorkAddressId, Model model) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            Consult consult = new Consult();
            consult.setMedicSpeciality(new MedicSpeciality(new MedicProfile(medicId), new Speciality(specialityId)));
            consult.setMedicWorkAddress(new MedicWorkAddress(medicWorkAddressId));
            consult.setPatient(new Patient(patientId));
            new ConsultDAO(con).addConsultForAll(consult);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        } catch (DuplicateException e) {
            System.err.println(e.getMessage());
        } catch (ConsultWithPrivilegesException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:../Paciente";
    }

    @RequestMapping("Paciente/AvaliarMedico")
    public String avaliarMedico(int patientId, int medicId, String evaluationDescName, String evaluationDescInfo, int evaluationScore) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            Evaluation evaluation = new Evaluation();
            evaluation.setPatientProfile(new PatientProfile(patientId));
            evaluation.setMedicProfile(new MedicProfile(medicId));
            evaluation.setEvaluationDescName(evaluationDescName);
            evaluation.setEvaluationDescInfo(evaluationDescInfo);
            evaluation.setEvaluationScore(evaluationScore);
            new PatientDAO(con).addPatientEvaluation(evaluation);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:../Paciente";
    }

    @RequestMapping("Paciente/MinhasAvaliacoes")
    public String minhasAvaliacoes(int patientId, Model model) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            List<Evaluation> evaluationList = new PatientDAO(con).getEvaluationList(new Patient(patientId));
            model.addAttribute(evaluationList);
            return "patient/list-evaluation";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "redirect:../Paciente";
    }
    
    @RequestMapping("Paciente/EnviarArquivo")
    public String enviarArquivo() {
        return "redirect:../Paciente";
    }

}
