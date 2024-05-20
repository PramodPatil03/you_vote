package com.example.myapplication;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EpicTypeCheck{
    public boolean checkPattern(String epicNum){
        if(epicNum.length()>10){
            return false;
        }
        Pattern p = Pattern.compile("[A-Z][A-Z][A-Z][0-9][0-9][0-9][0-9][0-9][0-9][0-9]");
        Matcher m = p.matcher(epicNum);
        if(m.find()){
            return true;
        }
        else
            return false;
    }
}
