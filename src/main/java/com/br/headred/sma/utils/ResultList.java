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

    private final List<ObjectList> resultList;
    private int index = -1;

    public ResultList(Result result) {
        resultList = new ArrayList<>();
        if (result.getAttrValue() instanceof List) {
            switch (result.getResultType()) {
                case LIST:                    
                    List<Result> lr = (List<Result>) result.getAttrValue();                    
                    for (Result r : lr) {
                        resultList.add(new ObjectList((List<Result>)r.getAttrValue()));
                    }
                    break;
                case OBJECT:                    
                    resultList.add(new ObjectList((List<Result>)result.getAttrValue()));
                    break;
                case ATTRIBUTE:
                    throw new Error("Result invalida");                    
            }
        }
    }
        
    public ResultList(List<Result> results) {
        resultList = new ArrayList<>();
        for (Result r : results) {
            resultList.add(new ObjectList((List<Result>) r.getAttrValue()));
        }
    }
    
    public boolean next() {
        index++;
        if (index + 1 > resultList.size()) {
            return false;
        }
        return true;
    }

    public int size() {
        return this.resultList.size();
    }

    public boolean isEmpty() {
        return this.resultList.isEmpty();
    }
    
    public int getInt(String attrName) {
        return resultList.get(index).getInt(attrName);
    }

    public String getString(String attrName) {
        return resultList.get(index).getString(attrName);
    }

    public Object getObject(String attrName) {
        return resultList.get(index).getObject(attrName);
    }

    public List<Result> getResultList(String attrName) {
        return resultList.get(index).getResultList(attrName);
    }
    
    public Result getResult(String attrName) {
        return resultList.get(index).getResult(attrName);
    }

}