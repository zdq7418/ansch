package com.lw.sch.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by ThundeRobot on 2017/5/4.
 */

public class StaticSource {
    public static final String SERVICE="http://192.168.0.105:8888/";
    public static final String LOGIN="login!antijiao.action";
    public static final String FINDALLCLASS="personnel!findAll.action";
    public static final String FINDALLPER="personnel!findAll.action";
    public static final String SAVEPERSONNEL="personnel!savePersonnel.action";
    public static final String OVERDUE="personnel!overdue.action";
    public static final String FINDBUYKEY="personnel!findBuyKey.action";
    public static final String UPDATEPERSONNEL="personnel!updatePersonnel.action";
    //
    public static final String FINDALLDE="depart!findAllDe.action";
    public static final String SAVEDE="depart!saveDe.action";
    public static final String SAVEORUPDE="depart!saveOrUpDe.action";
    public static final String DELETEDE="depart!deleteDe.action";
    public static final String FINDBYNAME="depart!findByName.action";
    //
    public static final String FINDALLCLA="clas!findAllCla.action";
    public static final String SAVECLA="clas!saveCla.action";
    public static final String SAVEORUPCLA="clas!saveOrUpCla.action";
    public static final String DELETECLA="clas!deleteCla.action";
    public static final String FINDCLABYKEY="clas!findClaByKey.action";
    //
    public static String fragmentname="";
    public static Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm:ss").create();
}
