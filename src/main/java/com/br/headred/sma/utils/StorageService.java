/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.utils;

import com.br.headred.sma.exceptions.StorageException;
import com.br.headred.sma.models.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class StorageService {
    
    private static final String MASTER_PATH = "E:/APP/SMC/";       
    
    public com.br.headred.sma.models.File store(MultipartFile multipartFile, int type, User user) throws StorageException {
        if (type < 0 && type > 20)
            throw new StorageException("Falha ao armazenar o arquivo. O tipo não é valido");
        FileOutputStream fos = null;
        com.br.headred.sma.models.File fileSaved;
        try {
            byte[] bytes = multipartFile.getBytes();
            String path = user.getId() + "/" + type;
            File file = new File(MASTER_PATH + path);
            if (!file.exists()) {
                System.out.println("ARQUIVO NÃO EXISTE");
                file.mkdirs();
            } else {
                System.out.println("ARQUIVO EXISTE");
            }            
            Date currentDate = Calendar.getInstance().getTime();
            String dateParsed = new SimpleDateFormat("dd-MM-yyyy-hh-mm-s-ms").format(currentDate);
            path = path + "/" + multipartFile.getOriginalFilename() + "-" + dateParsed;
            file = new File(MASTER_PATH + path);            
            fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
            fileSaved = new com.br.headred.sma.models.File();
            fileSaved.setFileName(multipartFile.getOriginalFilename());
            fileSaved.setFilePath(path);
            fileSaved.setFileUploadDate(new Timestamp(currentDate.getTime()));
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
    
    public void delete(com.br.headred.sma.models.File fileToDelete) throws SecurityException {
        File file = new File(MASTER_PATH + fileToDelete.getFilePath());
        file.delete();
    }

    public byte[] load(com.br.headred.sma.models.File fileSaved) {
        File file = new File(MASTER_PATH + fileSaved.getFilePath());
        byte[] bytes = new byte[(int)file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
    
}
