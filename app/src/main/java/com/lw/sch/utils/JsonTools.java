package com.lw.sch.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JsonTools {

    public static String createJsonString(Object value) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm:ss").create();
        String string = gson.toJson(value);
        return string;
    }
    
    
    public static <T> T jsonStringTo(String jsonString){
    	Gson gson = new Gson();
    	return gson.fromJson(jsonString,new TypeToken<T>(){}.getType());
    }

    public static boolean isInteger(String str)
    {
        try
        {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException ex){}
        return false;
    }



}
