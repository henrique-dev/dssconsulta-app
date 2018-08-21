/* Copyright (C) HeadRed, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Paulo Henrique Gona�alves Bacelar <henrique.phgb@gmail.com>, Agosto 2018
 */
package com.br.headred.sma.teste;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class TesteTodosAcimaRemove {

    public TesteTodosAcimaRemove() {
        new RemoveMedicWorkAddress();
        new RemoveMedicSpeciality();
        new RemoveSpeciality();
        new RemoveClinic();
        new RemoveMedic();
        new RemovePatient();
    }
    
    public static void main(String[] args) {
        new TesteTodosAcimaRemove();
    }
    
}
