/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.headred.sma.utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class ResultList {
    
    private List<ObjectList> resultList;

    public ResultList() {
        resultList = new ArrayList<>();
    }        

    public ResultList(List<ObjectList> resultList) {
        this.resultList = resultList;
    }

    public List<ObjectList> getList() {
        return resultList;
    }

    public void setList(List<ObjectList> resultList) {
        this.resultList = resultList;
    }        
    
}
