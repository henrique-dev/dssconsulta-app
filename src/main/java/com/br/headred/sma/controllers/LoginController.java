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
import java.sql.Connection;
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

    @PostMapping("Autenticar")
    public String autentica(String userName, String userPassword, HttpSession session) {
        System.out.println("Tentativa de logon > Usuario: " + userName + " - Senha: " + userPassword);
        Connection connection = new ConnectionFactory().getConnection();
        if (!userName.toLowerCase().contains("ap")) {
            try {
                Patient patient = new PatientDAO(connection).login(new Patient(userName, userPassword));
                if (patient != null) {
                    System.err.println(patient.getUserSessionId());
                    return "redirect:PrincipalPaciente";                    
                }
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:Entrar";
    }

}
