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
import com.br.headred.sma.models.Medic;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.PatientProfile;
import com.br.headred.sma.models.User;
import com.br.headred.sma.utils.ResponseCode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String autenticar(String userName, String userPassword, HttpSession session, Model model, HttpServletResponse response) {
        String msg = "";
        try (Connection connection = new ConnectionFactory().getConnection()) {
            if (userName.toLowerCase().contains(".")) {
                User user = new Medic();
                user.setUserName(userName);
                user.setUserPassword(userPassword);
            } else {
                User user = new LoginDAO(connection).login(new Patient(userName, userPassword));
                if (user != null) {
                    session.setAttribute("user", user);
                    model.addAttribute("patient", user);
                    response.setStatus(HttpServletResponse.SC_OK);                    
                    return "patient/main-data";
                }
            }
            response.setStatus( ResponseCode.SC_LOGIN_ERROR );
            msg = "Usuário ou senha incorretos";
        } catch (DAOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            msg = e.getMessage();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        model.addAttribute("message", msg);
        return "message";
    }

    @RequestMapping("Teste")
    public String teste(String userName, String userPassword, HttpSession session) {
        System.out.println(session.getId());
        System.out.println(session.getAttribute("usuario"));
        System.out.println("Recuperando usuario desta sessão: " + session.getId());
        return "patient/main";
    }

    @RequestMapping("Teste2")
    public String teste2(String userName, String userPassword, HttpSession session) {
        session.invalidate();
        return "patient/main";
    }

}
