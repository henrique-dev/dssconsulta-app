/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.controllers;

import com.br.headred.sma.dao.PatientDAO;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.PatientProfile;
import java.sql.Connection;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @RequestMapping("Cadastrar")
    public String registrando(String name, String cpf, String email, String genre, 
            Date birthDate, String height, String bloodType, String telephone, String password, String passwordRe) {        
        try {
            PatientProfile p = new PatientProfile();
            p.setPatientName(name);
            p.setPatientProfileEmail(email);
            p.setPatientProfileGenre(genre);
            p.setPatientProfileBirthDate(birthDate);
            p.setPatientProfileHeight(Float.parseFloat(height));
            p.setPatientProfileBloodType(bloodType);
            p.setPatientProfileTelephone(telephone);
            p.setPatientCpf(cpf);
            p.setUserName(cpf);
            p.setUserPassword(password);
            new PatientDAO(new ConnectionFactory().getConnection()).addPatient(p);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return "login/login";
    }

    @PostMapping("Autenticar")
    public String autenticar(String userName, String userPassword, HttpSession session) {
        System.out.println("Tentativa de logon > Usuario: " + userName + " - Senha: " + userPassword);
        Connection connection = new ConnectionFactory().getConnection();
        if (!userName.toLowerCase().contains("ap")) {
            try {
                Patient patient = new PatientDAO(connection).login(new Patient(userName, userPassword));
                if (patient != null) {
                    System.err.println(patient.getUserSessionId());
                    return "redirect:Paciente";                    
                }
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:Entrar";
    }

}
