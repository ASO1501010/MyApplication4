package com.example.satokota.myapplication;

import java.io.Serializable;

public class School implements Serializable {
    static int level;
    static String school_id;
    static String name;
    static String kana;
    static String number;
    static String address;
    static String home_address;
    static String birthday;
    static String sex;
    static String phone_number;
    static String home_number;
    static String mail_address;
    static String teacher;

    public School(int inLevel, String inSchool_id, String inName, String inKana, String inNumber, String inAddress, String inHomeAddress,
                  String inBirthday, String inSex, String inPhoneNumber, String inHomeNumber, String inMailAddress, String inTeacher){
        level = inLevel;
        school_id = inSchool_id;
        name = inName;
        kana = inKana;
        number = inNumber;
        address = inAddress;
        home_address = inHomeAddress;
        birthday = inBirthday;
        sex = inSex;
        phone_number = inPhoneNumber;
        home_number = inHomeNumber;
        mail_address = inMailAddress;
        teacher = inTeacher;

    }

    public static School getInstance(){
        School user = new School(level, school_id, name, kana, number, address, home_address, birthday, sex, phone_number, home_number, mail_address, teacher);
        return user;
    }
}
