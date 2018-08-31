/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.headred.sma.exceptions;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class UnauthorizedException extends Exception{
    
    public UnauthorizedException(Exception e) {
        super(e);
    }
    
    public UnauthorizedException(String message) {
        super(message);
    }
    
    public UnauthorizedException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}