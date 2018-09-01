/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.headred.sma.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Session id: " + request.getSession().getId());
        String uri = request.getRequestURI();
        if (request.getSession().getAttribute("user") != null) {
            System.out.println("USUARIO NÃO NULO");
            return true;
        }
        else if (uri.contains("resources") || uri.contains("Teste2") || uri.contains("Autenticar")
                || uri.contains("Entrar") || uri.contains("Principal")) {
            System.out.println("ENCAMINHANDO");            
            return true;            
        } else {
            System.out.println("ACESSO NÃO AUTORIZADO");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        //response.sendRedirect("Principal");
        return false;
    }

}
