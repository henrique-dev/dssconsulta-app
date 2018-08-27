/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.headred.sma.utils;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class Result {
    
    public enum ResultType {LIST, OBJECT, ATTRIBUTE}
    
    private ResultType resultType;
    private String attrName;
    private Object attrValue;

    public Result() {
    }        

    public Result(ResultType resultType, String attrName, Object attrValue) {
        this.resultType = resultType;
        this.attrName = attrName;
        this.attrValue = attrValue;
    }        

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public Object getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(Object attrValue) {
        this.attrValue = attrValue;
    }        

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }        
    
}
