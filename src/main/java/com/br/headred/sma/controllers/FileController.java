/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.controllers;

import com.br.headred.sma.dao.FileDAO;
import com.br.headred.sma.exceptions.DAOException;
import com.br.headred.sma.exceptions.StorageException;
import com.br.headred.sma.jdbc.ConnectionFactory;
import com.br.headred.sma.models.File;
import com.br.headred.sma.models.Manager;
import com.br.headred.sma.models.Medic;
import com.br.headred.sma.models.Patient;
import com.br.headred.sma.models.User;
import com.br.headred.sma.utils.StorageService;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
@MultipartConfig
@Controller
public class FileController {
    
    @RequestMapping("Image/{imageId}")
    @ResponseBody
    public byte[] image(@PathVariable int imageId) {
        System.out.println(imageId);
        File file = new File();
        file.setFilePath("E:/APP/imagem.jpg");
        byte[] bytes = new StorageService().load(file);
        return bytes;
    }
    
    @RequestMapping("EnviarImagem")
    public String enviarImagem(MultipartFile file, int type, Model model, HttpSession session) {        
        String msg = "error";
        try (Connection con = new ConnectionFactory().getConnection()) {
            //User user = (User) session.getAttribute("user");
            User user = new Patient();
            user.setId(0);            
            if (user instanceof Patient) {
                StorageService storageService = new StorageService();
                if (type == File.TYPE_PROFILE_PHOTO) {
                    File fileExist = new FileDAO(con).getFileProfile(user.getId(), "patient");
                    if (fileExist != null) {
                        storageService.delete(fileExist);
                        File savedFile = storageService.store(file, type, user);
                        new FileDAO(con).updateFileProfile(savedFile, user, "patient");
                        msg = "success";
                    } else {
                        File savedFile = storageService.store(file, type, user);
                        new FileDAO(con).addFileProfile(savedFile, user, "patient");
                        msg = "success";
                    }
                }                                
            } else if (user instanceof Medic) {
                
            } else if (user instanceof Manager){
                
            } else {
                
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        } catch (StorageException e) {
            msg = "error-saving-ondisk";
            e.printStackTrace();
        } catch (DAOException e) {
            msg = "error-saving-ondb";
        }
        model.addAttribute("message", msg);
        return "message"; 
    }
    
    
    
}
