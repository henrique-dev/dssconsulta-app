/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.controllers;

import com.br.headred.sma.dao.ClinicDAO;
import com.br.headred.sma.dao.MedicDAO;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.ClinicProfile;
import com.br.headred.sma.models.MedicProfile;
import com.br.headred.sma.models.Speciality;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.jsp.jstl.core.LoopTagStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
@Controller
public class ManagerController {

    @RequestMapping("Manager/CadastrarMedico")
    public String cadastrarMedico(String userName, String userPassword, String medicName, String medicCrm, Model model) {
        String message = "";
        try (Connection con = new ConnectionFactory().getConnection()) {
            MedicProfile medicProfile = new MedicProfile();
            medicProfile.setMedicName(medicName);
            medicProfile.setMedicCrm(medicCrm);
            medicProfile.setUserName(userName);
            medicProfile.setUserPassword(userPassword);
            new MedicDAO(con).addMedic(medicProfile);
            message = "Registro inserido com sucesso!";            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {            
            message = e.getMessage();
        }
        model.addAttribute("message", message);
        return "manager/message";
    }

    @RequestMapping("Manager/ListarClinicas")
    public String listarClinicas(Model model) {                        
        try (Connection con = new ConnectionFactory().getConnection()) {
            List<ClinicProfile> clinicProfileList = new ClinicDAO(con).getClinicProfileList();                            
            model.addAttribute("clinicProfileList", clinicProfileList);            
            
            return "manager/list-clinic";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();            
        }        
        return "manager/message";
    }
    
    @RequestMapping("Manager/ListarEspecialidades")
    public String listarEspecialidades(Model model) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            List<Speciality> specialityList = new MedicDAO(con).getSpecialityList();                            
            model.addAttribute("specialityList", specialityList);            
            
            return "manager/list-speciality";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }        
        return "manager/message";
    }

}
