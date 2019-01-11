package com.example.satokota.myapplication;

import java.io.Serializable;
import java.util.ArrayList;

public class Schedule implements Serializable {
    static ArrayList<String> start_date;
    static ArrayList<String> end_date;
    static ArrayList<String> company;

    public Schedule(ArrayList<String> inStart_date, ArrayList<String> inEnd_date, ArrayList<String> inCompany){
        start_date = inStart_date;
        end_date = inEnd_date;
        company = inCompany;
    }

    public static void setSchedule(String inStart_date, String inEnd_date, String inCompany){
        start_date.add(inStart_date);
        end_date.add(inEnd_date);
        company.add(inCompany);
    }

    public static Schedule getInstance(){
        Schedule schedule = new Schedule(start_date, end_date, company);
        return schedule;
    }
}
