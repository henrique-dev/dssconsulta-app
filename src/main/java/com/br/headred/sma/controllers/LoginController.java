/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.controllers;

import com.br.headred.sma.dao.LoginDAO;
import com.br.headred.sma.dao.MedicDAO;
import com.br.headred.sma.dao.PatientDAO;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.Manager;
import com.br.headred.sma.models.Medic;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.PatientProfile;
import com.br.headred.sma.models.User;
import com.br.headred.sma.utils.ResponseCode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
@Controller
public class LoginController {

    @RequestMapping("Entrar")
    public String login() {
        return "login/login";
    }

    @RequestMapping("NovoCadastro")
    public String registrar() {
        return "login/register";
    }

    @RequestMapping("Registrar")
    public String registrando(String patientName, String patientCpf, String patientEmail, String patientGenre,
            long patientBirthDate, float patientHeight, float patientWeight, String patientBloodType, String patientTelephone, String patientPassword, Model model) {
        try (Connection con = new ConnectionFactory().getConnection()){
            
            System.out.println(patientCpf);
            
            PatientProfile p = new PatientProfile();
            p.setPatientName(patientName);
            p.setPatientProfileEmail(patientEmail);
            p.setPatientProfileGenre(patientGenre);
            p.setPatientProfileBirthDate(new Date(patientBirthDate));
            p.setPatientProfileHeight(patientHeight);
            p.setPatientProfileBloodType(patientBloodType);
            p.setPatientProfileTelephone(patientTelephone);
            p.setPatientProfileWeight(patientWeight);
            p.setPatientCpf(patientCpf);
            p.setUserName(patientCpf);
            p.setUserPassword(patientPassword);
            new PatientDAO(con).addPatient(p);
            model.addAttribute("message", "success");
            return "patient/message";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "login/login";
    }

    @PostMapping("Autenticar")
    public String autenticar(String userName, String userPassword, HttpSession session, Model model, HttpServletResponse response) {        
        String msg = "";        
        try (Connection connection = new ConnectionFactory().getConnection()) {
            if (userName.toLowerCase().contains(".")) {
                User user = new LoginDAO(connection).login(new Medic(userName.toLowerCase(), userPassword));
                if (user != null) {
                    session.setAttribute("user", user);
                    model.addAttribute("medic", user);
                    response.setStatus(HttpServletResponse.SC_OK);                    
                    return "medic/main-data";
                }                    
            } else if (userName.toLowerCase().startsWith("@")) {
                User user = new LoginDAO(connection).login(new Manager(userName.toLowerCase(), userPassword));
                if (user != null) {
                    session.setAttribute("user", user);                    
                    response.setStatus(HttpServletResponse.SC_OK);
                    msg = "success";
                    model.addAttribute("message", msg);                    
                    return "manager/message";
                }
            } else {
                User user = new LoginDAO(connection).login(new Patient(userName.toLowerCase(), userPassword));
                if (user != null) {
                    session.setAttribute("user", user);
                    model.addAttribute("patient", user);
                    response.setStatus(HttpServletResponse.SC_OK);                    
                    return "patient/main-data";
                }
            }
            response.setStatus( HttpServletResponse.SC_FORBIDDEN );
            msg = "Usuário ou senha incorretos";
        } catch (DAOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            msg = e.getMessage();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
        model.addAttribute("message", msg);
        return "manager/message";
    }

    @RequestMapping("Teste")
    public String teste(MultipartFile file, HttpSession session) {
        
        
        
        
        return "patient/main";
    }

    @RequestMapping("Teste2")
    public String teste2(String userName, String userPassword, HttpSession session) {
        session.invalidate();
        return "patient/main";
    }

}
