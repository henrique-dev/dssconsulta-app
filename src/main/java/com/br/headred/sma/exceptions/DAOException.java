/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.exceptions;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class DAOException extends RuntimeException{
    
    public DAOException(Exception e) {
        super(e);
    }
    
}
