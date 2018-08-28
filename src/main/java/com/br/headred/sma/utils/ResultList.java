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

    public ResultList() {
        resultList = new ArrayList<>();
    }

    public ResultList(Result result) {
        List<Result> lr = (List<Result>) result.getAttrValue();
        resultList = new ArrayList<>();
        for (Result r : lr) {
            resultList.add(new ObjectList((List<Result>) r.getAttrValue()));
        }
    }

    public ResultList(List<Result> results) {
        resultList = new ArrayList<>();
        for (Result r : results) {
            resultList.add(new ObjectList((List<Result>) r.getAttrValue()));
        }
    }
    
    public boolean isEmpty() {
        return this.resultList.isEmpty();
    }

    public boolean next() {
        index++;
        if (index + 1 > resultList.size()) {
            return false;
        }
        return true;
    }

    public ObjectList getObjectList() {
        return resultList.get(index);
    }

}
