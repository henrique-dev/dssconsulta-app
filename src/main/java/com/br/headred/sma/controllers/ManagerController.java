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
import com.br.headred.sma.models.MedicSpeciality;
import com.br.headred.sma.models.MedicWorkAddress;
import com.br.headred.sma.models.MedicWorkScheduling;
import com.br.headred.sma.models.Speciality;
import com.br.headred.sma.utils.ObjectList;
import com.br.headred.sma.utils.Result;
import com.br.headred.sma.utils.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
    public String cadastrarMedico(String userName, String userPassword, String medicName, String medicCrm, String medicWorkInfo, Model model) {
        String message = "";
        try (Connection con = new ConnectionFactory().getConnection()) {
            MedicProfile medicProfile = new MedicProfile();
            medicProfile.setMedicName(medicName);
            medicProfile.setMedicCrm(medicCrm);
            medicProfile.setUserName(userName);
            medicProfile.setUserPassword(userPassword);
            new MedicDAO(con).addMedic(medicProfile);
            
            List<MedicSpeciality> medicSpecialityList = new ArrayList<>();
            List<MedicWorkAddress> medicWorkAddressList = new ArrayList<>();
            List<MedicWorkScheduling> medicWorkSchedulingList = new ArrayList<>();

            Result r = new ResultSet(medicWorkInfo).getResult();
            List<Result> lr = (List<Result>) r.getAttrValue();
            
            for (Result result : lr) {
                ObjectList obj = new ObjectList((List<Result>) result.getAttrValue());
                
                MedicSpeciality medicSpeciality = new MedicSpeciality(medicProfile, new Speciality(obj.getInt("specialityId")));
                MedicWorkAddress medicWorkAddress = new MedicWorkAddress(medicSpeciality, new ClinicProfile(obj.getInt("clinicId")), obj.getString("medicWorkInfo"));
                MedicWorkScheduling medicWorkScheduling = new MedicWorkScheduling(
                        obj.getInt("medicSchedPerDay"), 
                        new Date(Calendar.getInstance().getTimeInMillis()), 0, obj.getString("medicSchedInfo"), obj.getString("medicSchedDayOfWeek"));
                
                medicSpecialityList.add(medicSpeciality);
                medicWorkAddressList.add(medicWorkAddress);
                medicWorkSchedulingList.add(medicWorkScheduling);
            }
            
            new MedicDAO(con).addMedicSpecialityList(medicSpecialityList);
            new MedicDAO(con).addMedicWorkAddressList(medicWorkAddressList);
            
            for (int i=0; i<medicWorkSchedulingList.size(); i++) {
                medicWorkSchedulingList.get(i).setMedicWorkSchedulingId(medicWorkAddressList.get(i).getMedicWorkAddressId());
            }
            
            new MedicDAO(con).addMedicWorkSchedulingList(medicWorkSchedulingList);

            /*
            MedicSpeciality medicSpeciality = new MedicSpeciality();
            medicSpeciality.setMedicProfile(medicProfile);
            medicSpeciality.setSpeciality(
                    new Speciality());*/
            message = "Registro inserido com sucesso!";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
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
