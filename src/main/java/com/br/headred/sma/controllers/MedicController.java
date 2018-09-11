/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.controllers;

import com.br.headred.sma.dao.ConsultDAO;
import com.br.headred.sma.dao.MedicDAO;
import com.br.headred.sma.dao.PatientDAO;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.AccountSpeciality;
import com.br.headred.sma.models.Consult;
import com.br.headred.sma.models.Medic;
import com.br.headred.sma.models.MedicProfile;
import com.br.headred.sma.models.MedicSpeciality;
import com.br.headred.sma.models.MedicWorkAddress;
import com.br.headred.sma.models.PatientAccount;
import com.br.headred.sma.models.Speciality;
import com.br.headred.sma.models.User;
import com.br.headred.sma.utils.ResultList;
import com.br.headred.sma.utils.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
@Controller
public class MedicController {
    
    @PostMapping("Medico/MeusEnderecos")
    public String meusEnderecos(Model model, HttpSession session) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            int medicId = ((User)session.getAttribute("user")).getId();
            List<MedicWorkAddress> medicWorkAddressList = new MedicDAO(con).getMedicWorkAddressList(new Medic(medicId));
            model.addAttribute("medicWorkAddressList", medicWorkAddressList);
            return "medic/work-address-list-data";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "medic/message";
    }
    
    @PostMapping("Medico/MeuPerfil")
    public String meuPerfil(Model model, HttpSession session) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            int medicId = ((User)session.getAttribute("user")).getId();
            MedicProfile medicProfile = new MedicDAO(con).getMedicProfile(medicId);
            model.addAttribute("medicProfile", medicProfile);
            return "medic/profile-medic-data";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "medic/message";
    }
    
    @PostMapping("Medico/MinhaConsulta")
    public String minhaConsulta(int medicWorkAddressId, Model model) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            Consult consult = new ConsultDAO(con).getNextConsult(new MedicWorkAddress(medicWorkAddressId));
            model.addAttribute("consult", consult);
            model.addAttribute("currentDate", Calendar.getInstance().getTime());
            return "medic/consult-data";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "medic/message";
            
    }
    
    @PostMapping("Medico/ListarEspecialidades")
    public String minhaConsulta(Model model) {
        try (Connection con = new ConnectionFactory().getConnection()) {
            List<Speciality> specialityList = new MedicDAO(con).getSpecialityListFromMedicSpeciality();
            model.addAttribute("specialityList", specialityList);            
            return "medic/list-speciality-data";
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "medic/message";            
    }
    
    @PostMapping("Medico/FinalizarConsulta")
    public String finalizarConsulta(int consultId, String specialityList, Model model, HttpSession session) {        
        
        try (Connection con = new ConnectionFactory().getConnection()) {
            int patientId = new ConsultDAO(con).getPatientId(new Consult(consultId));
            int medicId = ((User)session.getAttribute("user")).getId();
            new ConsultDAO(con).removeConsult(new Consult(consultId));
            if (!specialityList.equals("")) {
                ResultSet resultSet = new ResultSet(specialityList);
                ResultList resultList = new ResultList(resultSet.getResult());
                while (resultList.next()) {
                    AccountSpeciality accountSpeciality = new AccountSpeciality();
                    accountSpeciality.setPatientAccount(new PatientAccount(patientId));
                    accountSpeciality.setMedicSpeciality(new MedicSpeciality(
                            new MedicProfile(medicId), 
                            new Speciality(resultList.getInt("specialityId"))));
                    accountSpeciality.setPatientAccountSpecialityCreationDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
                    accountSpeciality.setPatientAccountSpecialityUsed(false);
                    try {
                        new ConsultDAO(con).addAccountSpeciality(accountSpeciality);
                    } catch (DAOException ex) {
                        ex.printStackTrace();
                        System.out.println("Falha ao adicionar o encaminhamento");
                    }                    
                }
            }                        
            model.addAttribute("message", "success");
            return "medic/message";
        } catch (DAOException e) {
            model.addAttribute("message", "fail");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Falha ao obter a conexão");
            e.printStackTrace();
        }
        return "medic/message";            
    }
    
    
}
