/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.utils;

import com.br.headred.sma.models.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.Calendar;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class StorageService {
    
    public com.br.headred.sma.models.File store(MultipartFile multipartFile, String subfolder, User user) {
        FileOutputStream fos = null;
        com.br.headred.sma.models.File fileSaved;
        try {
            byte[] bytes = multipartFile.getBytes();
            String path = "E:/APP/SMC/" + user.getUserName() + "/" + subfolder;
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("ARQUIVO NÃO EXISTE");
                file.mkdirs();
            } else {
                System.out.println("ARQUIVO EXISTE");
            }
            path = path + "/" + multipartFile.getOriginalFilename();
            file = new File(path);            
            fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
            fileSaved = new com.br.headred.sma.models.File();
            fileSaved.setFileName(multipartFile.getOriginalFilename());
            fileSaved.setFilePath(path);
            fileSaved.setFileUploadDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                }
            }
        }
        return fileSaved;
    }

    public byte[] load(com.br.headred.sma.models.File fileSaved) {
        File file = new File(fileSaved.getFilePath());
        byte[] bytes = new byte[(int)file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
    
}
