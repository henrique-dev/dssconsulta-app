/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class CalendarioTeste {

    public static void main(String[] args) {

        String daysOfWeek = "0000001";

        Date date = new Date(Calendar.getInstance().getTimeInMillis());        

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);                        
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        int counter = 0;        
        for (int i = calendar.get(Calendar.DAY_OF_WEEK); i <= daysOfWeek.length(); i++) {            
            if (daysOfWeek.charAt(i-1) == '0') {
                counter++;
            } else {                                
                break;
            }            
            if (i == daysOfWeek.length())
                i = 0;
        }
        
        calendar.add(Calendar.DAY_OF_MONTH, counter);        
    }

}
