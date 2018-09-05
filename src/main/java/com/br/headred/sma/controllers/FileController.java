/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.controllers;

import com.br.headred.sma.dao.FileDAO;
import com.br.headred.sma.models.File;
import com.br.headred.sma.utils.StorageService;
import javax.servlet.annotation.MultipartConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @RequestMapping("MinhaImagem")
    public String minhaImagem() {
        return "teste"; 
    }
    
    @RequestMapping("EnviarImagem")
    public String minhaImagem(MultipartFile multipartFile) {
        //System.out.println(multipartFile.getOriginalFilename());
        return "teste"; 
    }
    
    
    
}
