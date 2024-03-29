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
public class ObjectList {

    private final List<Result> objectList;    

    public ObjectList() {
        objectList = new ArrayList<>();
    }

    public ObjectList(List<Result> objectList) {
        this.objectList = objectList;
    }    

    public int size() {
        return this.objectList.size();
    }        
    
    public int getInt(String attrName) {
        for (Result r : objectList) {
            if (attrName.equals(r.getAttrName()))
                return Integer.parseInt((String)r.getAttrValue());
        }
        throw new Error("Atributo não encontrado");
    }

    public String getString(String attrName) {
        for (Result r : objectList) {
            if (attrName.equals(r.getAttrName()))
                return (String)r.getAttrValue();            
        }
        throw new Error("Atributo não encontrado");
    }
    
    public Object getObject(String attrName) {
        for (Result r : objectList) {
            if (attrName.equals(r.getAttrName()))
                return r.getAttrValue();            
        }
        throw new Error("Atributo não encontrado");
    }
    
    @Deprecated
    public ObjectList getObjectList(String attrName) {
        for (Result r : objectList) {                                              
            if (attrName.equals(r.getAttrName()))
                return new ObjectList((List<Result>)r.getAttrValue());
        }
        throw new Error("Atributo não encontrado");
    }
    
    public List<Result> getResultList(String attrName) {
        for (Result r : objectList) {                        
            if (attrName.equals(r.getAttrName()))
                return (List<Result>)r.getAttrValue();
        }
        throw new Error("Atributo não encontrado");
    }
    
    @Deprecated
    public Result getResult(String attrName) {
        for (Result r : objectList) {                        
            if (attrName.equals(r.getAttrName()))
                return (Result)r.getAttrValue();
        }
        throw new Error("Atributo não encontrado");
    }
    

}
