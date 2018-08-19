/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.controllers;

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

    @RequestMapping("entrar")
    public String login() {
        return "login/login";
    }

    @PostMapping("autentica")
    public String autentica(String userName, String userPassword, HttpSession session) {
        System.out.println("Tentativa de logon > Usuario: " + userName + " - Senha: " + userPassword);

        return "patient/main";
    }

}
