/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.headred.sma.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {        
        String uri = request.getRequestURI();             
        if (request.getSession().getAttribute("user") != null) {
            return true;
        }
        else if (uri.contains("resources") || uri.contains("Autenticar") || uri.contains("Registrar")
                || uri.contains("Entrar") || uri.contains("Principal")) {            
            return true;            
        } else {          
            System.out.println("Acesso não autorizado");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            //return true;
        }        
        return true;
    }

}
