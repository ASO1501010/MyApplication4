package com.example.satokota.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Qualification implements Serializable {
    static ArrayList<String> pass_date = new ArrayList<>();
    static ArrayList<String> qualification_name = new ArrayList<>();

    public Qualification(ArrayList<String> inPass_date, ArrayList<String> inQualication){
        //int ele_cnt = pass_date.size();
        pass_date = inPass_date;
        qualification_name = inQualication;
//        pass_date = inPass_date;
//        qualification_name = inQualication;
    }

//    public Qualification(String inPass_date, String inQualication){
//        //int ele_cnt = pass_date.size();
//        pass_date.add(inPass_date);
//        qualification_name.add(inQualication);
//        pass_date = inPass_date;
//        qualification_name = inQualication;
//    }

    public static void setQualification(String inPass_date, String inQualication){
        pass_date.add(inPass_date);
        qualification_name.add(inQualication);
    }

    public static Qualification getInstance(){
        //Qualification qualification = new Qualification(pass_date, qualification_name);
        Qualification qualification = new Qualification(pass_date, qualification_name);
        return qualification;
    }
}
