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
public class ResultSet {

    private final int READING_KEY = 0;
    private final int READING_ATTR_NAME = 1;
    private final int READING_ATTR_VALUE = 2;
    private final int READING_OBJ_NAME = 3;
    private final int READING_LIST_NAME = 4;

    private int readingStatus = READING_KEY;

    private final Object finalResult;

    private int lastIndex = -1;

    private int codLv = 0;
    private int objLv = 0;
    private int listLv = 0;
    private int attrLv = 0;

    public ResultSet() {
        this.finalResult = null;
    }    
    

    public ResultSet(String rawResult) {
        //System.out.println(rawResult);
        this.finalResult = extractResult(0, rawResult);
    }

    public Result getResult() {
        return (Result) finalResult;
    }

    public String insertResult(Object finalResult) {
        StringBuilder rawResult = new StringBuilder();
        if (finalResult != null) {
            if (finalResult instanceof Result) {
                Result result = ((Result) finalResult);
                switch (result.getResultType()) {
                    case LIST:
                        rawResult.append("(");
                            rawResult.append(result.getAttrName());
                            rawResult.append(":");
                            rawResult.append(insertResult(result.getAttrValue()));
                            if (rawResult.charAt(rawResult.length()-1) == ',')
                                rawResult.deleteCharAt(rawResult.length()-1);
                            rawResult.append(")");
                        break;
                    case ATTRIBUTE:
                        rawResult.append("{");
                        rawResult.append(result.getAttrName());
                        rawResult.append(":");
                        rawResult.append(result.getAttrValue());
                        rawResult.append("},");
                        break;
                    case OBJECT:
                        rawResult.append("[");
                        rawResult.append(result.getAttrName());
                        rawResult.append(":");
                        rawResult.append(insertResult(result.getAttrValue()));
                        if (rawResult.charAt(rawResult.length()-1) == ',')
                                rawResult.deleteCharAt(rawResult.length()-1);
                        rawResult.append("],");
                        break;
                }
            } else if (finalResult instanceof List) {
                for (Result result : (List<Result>) finalResult) {
                    rawResult.append(insertResult(result));
                }                
            }
        } else {
            return "";
        }
        return rawResult.toString();
    }

    private Object extractResult(int indexBegin, String rawResult) {
        int attrCount = 0;
        int objCount = 0;
        StringBuilder name = new StringBuilder();
        StringBuilder value = new StringBuilder();
        Result result = new Result();
        List<Result> resultList = new ArrayList<>();        
        for (int i = indexBegin; i < rawResult.length(); i++) {
            lastIndex = i;
            char c = rawResult.charAt(i);            
            switch (c) {
                case '<':
                    codLv++;
                    break;
                case '>':
                    codLv--;
                    if (codLv < 0) {
                        throw new Error("Falha na leitura da ResultSet");
                    }
                    break;
                case '[':
                    if (listLv > 0) {
                        if (objCount == 0) {
                            resultList = new ArrayList<>();
                        }
                        objCount++;
                    }
                    objLv++;
                    readingStatus = READING_OBJ_NAME;
                    break;
                case ']':
                    objLv--;
                    if (objLv < 0) {
                        throw new Error("Falha na leitura da ResultSet");
                    }
                    if (resultList.isEmpty()) // AQUI FOI MODIFICADO
                    {
                        return null;
                    } else {
                        return resultList;
                    }
                case '(':                    
                    listLv++;
                    readingStatus = READING_LIST_NAME;
                    break;
                case ')':                    
                    listLv--;
                    if (listLv < 0) {
                        throw new Error("Falha na leitura da ResultSet");
                    }
                    //if (resultList.isEmpty()) // AQUI FOI MODIFICADO
                    //  return null;
                    //else
                    return resultList;
                case '{':
                    readingStatus = READING_ATTR_NAME;
                    if (attrCount == 0) {
                        resultList = new ArrayList<>();
                    }
                    attrCount++;
                    attrLv++;
                    break;
                case '}':
                    if (readingStatus != READING_ATTR_NAME) {
                        result.setAttrValue(value.toString());
                        value = new StringBuilder();
                        resultList.add(result);
                        readingStatus = READING_KEY;
                    }
                    attrLv--;
                    if (attrLv < 0) {
                        throw new Error("Falha na leitura da ResultSet");
                    }
                    break;
                case ':':
                    switch (readingStatus) {
                        case READING_OBJ_NAME: {
                            readingStatus = READING_KEY;
                            result = new Result();
                            result.setResultType(Result.ResultType.OBJECT);
                            result.setAttrName(name.toString());
                            name = new StringBuilder();
                            Object obj = extractResult(i + 1, rawResult);
                            result.setAttrValue(obj);
                            if (listLv > 0) {
                                if (obj != null) {
                                    resultList.add(result);
                                }
                            }
                            i = lastIndex + 1;
                            break;
                        }
                        case READING_ATTR_NAME:
                            readingStatus = READING_ATTR_VALUE;
                            result = new Result();
                            result.setResultType(Result.ResultType.ATTRIBUTE);
                            result.setAttrName(name.toString());
                            name = new StringBuilder();
                            break;
                        case READING_LIST_NAME:
                            readingStatus = READING_KEY;
                            result = new Result();
                            result.setResultType(Result.ResultType.LIST);
                            result.setAttrName(name.toString());
                            name = new StringBuilder();
                            Object obj = extractResult(i + 1, rawResult);                            
                            result.setAttrValue(obj);
                            if (listLv > 0) {
                                //if (obj != null)
                                resultList.add(result);
                            }
                            i = lastIndex + 1;
                            break;
                    }
                    break;
                case ',':
                    break;
                default:
                    switch (readingStatus) {
                        case READING_LIST_NAME:
                        case READING_OBJ_NAME:
                        case READING_ATTR_NAME:
                            name.append(c);
                            break;
                        case READING_ATTR_VALUE:
                            value.append(c);
                            break;
                    }
                    break;
            }
            //lastIndex = i;
        }
        if (listLv != 0 && objLv != 0 && attrLv != 0 && codLv != 0) {
            result = null;
            throw new Error("Falha na leitura da ResultSet");
        }
        return result;
    }

    /*
    private Object extractResult(int indexBegin) {
        int objLv = 0;
        int brackLv = 0;
        int listLv = 0;
        StringBuilder attrName = new StringBuilder();
        StringBuilder attrValue = new StringBuilder();
        ObjectList objectList = new ObjectList();
        ResultList resultList = new ResultList();
        Result result = new Result();
        for (int i = indexBegin; i < rawResult.length(); i++) {
            char c = rawResult.charAt(i);
            switch (c) {
                case '(':
                    readingStatus = READING_ATTR_NAME;
                    attrName = new StringBuilder();
                    listLv++;
                    break;
                case ')':
                    return resultList;
                case '[':
                    if (objLv == 0) {
                        result = new Result();
                        attrName = new StringBuilder();
                        objectList = new ObjectList();
                    }
                    objLv++;
                    break;
                case ']':
                    if (objLv == 1) {
                        resultList.getList().add(objectList);
                        objectList = new ObjectList();
                    }
                    objLv--;
                    break;
                case '{':
                    if (brackLv == 0) {
                        readingStatus = READING_ATTR_NAME;
                    }
                    brackLv++;
                    break;
                case '}':
                    if (brackLv == 1) {
                        result.setAttrValue(attrValue.toString());
                        attrValue = new StringBuilder();
                        attrName = new StringBuilder();
                        objectList.getList().add(result);
                        result = new Result();
                        readingStatus = READING_KEY;
                    }
                    brackLv--;
                    break;
                case ':':
                    if (listLv == 1) {
                        objectList.getList().add(new Result(attrName.toString(), extractResult(i + 1)));
                        i = lastIndex + 1;
                        readingStatus = READING_KEY;
                        attrName = new StringBuilder();
                        listLv--;
                        continue;
                    }
                    readingStatus = READING_ATTR_VALUE;
                    result.setAttrName(attrName.toString());
                    attrName = new StringBuilder();
                    break;
                case ',':
                    break;
                default:
                    switch (readingStatus) {
                        case READING_ATTR_NAME:
                            attrName.append(c);
                            break;
                        case READING_ATTR_VALUE:
                            attrValue.append(c);
                            break;
                    }
                    break;
            }
            lastIndex = i;
        }
        System.out.println(resultList.getList().get(0));
        //return ((Result) resultList.getList().get(0).getList().get(0)).getAttrValue();
        return resultList.getList().get(0);
    }*/
}