package com.example.satokota.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.regex.Pattern;

public class sign_up_student extends AppCompatActivity {
    private TextInputLayout textname;
    private TextInputLayout textnamesub;
    private TextInputLayout textstudentsnumber;
    private TextInputLayout textaddress;
    private TextInputLayout textaddress_return;
    private TextInputLayout textphone_number;
    private TextInputLayout texthome_number;
    private TextInputLayout textbirthday;
    private TextInputLayout textteacher;
    private TextInputLayout textmailaddress;

    private String nameInput, namesubInput, studentsnumberInput, addressInput, address_returnInput,
                    birthdayInput, phone_numberInput, home_numberInput, mailaddressInput, teacherInput;

    private StringBuilder sb;

    ImageView error1 , error2 , error3 ,error4,error5,error6,error7,error8,error9,error10;
    public static final Pattern EMAIL_ADDRESS =Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@"+
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+
                    "("+
                    "\\."+
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+
                    ")+"
    );
    public static final Pattern BIRTHDAY =Pattern.compile(
            "^(\\d{4})"+"(0[1-9]|1[0-2])"+"(0[1-9]|[12][0-9]|3[01])$"
    );
    public static final Pattern NUMBER =Pattern.compile(
            "^[0-9]{2,4}"+"[0-9]{2,4}"+"[0-9]{3,4}$"
    );
    public static final Pattern PHONE_NUMBER =Pattern.compile(
            "^(070|080|090)"+"\\d{4}"+"\\d{4}"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_student);

        error1 = findViewById(R.id.icon1);
        findViewById(R.id.icon1).setVisibility(View.INVISIBLE);
        error2 = findViewById(R.id.icon2);
        findViewById(R.id.icon2).setVisibility(View.INVISIBLE);
        error3 = findViewById(R.id.icon3);
        findViewById(R.id.icon3).setVisibility(View.INVISIBLE);
        error4 = findViewById(R.id.icon4);
        findViewById(R.id.icon4).setVisibility(View.INVISIBLE);
        error5 = findViewById(R.id.icon5);
        findViewById(R.id.icon5).setVisibility(View.INVISIBLE);
        error6 = findViewById(R.id.icon6);
        findViewById(R.id.icon6).setVisibility(View.INVISIBLE);
        error7 = findViewById(R.id.icon7);
        findViewById(R.id.icon7).setVisibility(View.INVISIBLE);
        error8 = findViewById(R.id.icon8);
        findViewById(R.id.icon8).setVisibility(View.INVISIBLE);
        error9 = findViewById(R.id.icon9);
        findViewById(R.id.icon9).setVisibility(View.INVISIBLE);
        error10 = findViewById(R.id.icon10);
        findViewById(R.id.icon10).setVisibility(View.INVISIBLE);

    }

        //        sikakuボタンで資格画面へ
//        View nnButton = findViewById(R.id.completebutton);
//        nnButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int level = 0;
//                EditText input = (EditText)findViewById(R.id.name);
//                SpannableStringBuilder sp = (SpannableStringBuilder)input.getText();
//                String name = sp.toString();
//                input = (EditText)findViewById(R.id.namesub);
//                sp = (SpannableStringBuilder)input.getText();
//                String kana = sp.toString();
//                input = (EditText)findViewById(R.id.studentsnumber);
//                sp = (SpannableStringBuilder)input.getText();
//                String number = sp.toString();
//                input = (EditText)findViewById(R.id.address);
//                sp = (SpannableStringBuilder)input.getText();
//                String address = sp.toString();
//                input = (EditText)findViewById(R.id.address_return);
//                sp = (SpannableStringBuilder)input.getText();
//                String address_return = sp.toString();
//                input = (EditText)findViewById(R.id.birthday);
//                sp = (SpannableStringBuilder)input.getText();
//                String birthday = sp.toString();
//                Spinner input_sex = (Spinner)findViewById(R.id.sex);
//                String sex = (String)input_sex.getSelectedItem();
//                input = (EditText)findViewById(R.id.phone_number);
//                sp = (SpannableStringBuilder)input.getText();
//                String phone_number = sp.toString();
//                input = (EditText)findViewById(R.id.home_number);
//                sp = (SpannableStringBuilder)input.getText();
//                String home_number = sp.toString();
//                input = (EditText)findViewById(R.id.mailaddress);
//                sp = (SpannableStringBuilder)input.getText();
//                String mailaddress = sp.toString();
//                input = (EditText)findViewById(R.id.teacher);
//                sp = (SpannableStringBuilder)input.getText();
//                String teacher = sp.toString();
//
//                School student = new School(level, "", name, kana, number, address, address_return,
//                        birthday, sex, phone_number, home_number, mailaddress, teacher);
//
//                Intent intent = new Intent(getApplication(), touroku.class);
//                intent.putExtra("STUDENT", student);
//                startActivity(intent);
//            }
//        });

    private  boolean validatename() {
        textname = findViewById(R.id.layout_name);

        nameInput = textname.getEditText().getText().toString().trim();
        if (nameInput.isEmpty()) {
            textname.setError("必須項目です");
            error1.setVisibility(View.VISIBLE);
            return false;

        }else{
            textname.setError(null);
            error1.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    private  boolean validatenamesub() {
        textnamesub = findViewById(R.id.layout_namesub);

        namesubInput = textnamesub.getEditText().getText().toString().trim();
        if (namesubInput.isEmpty()) {
            textnamesub.setError("必須項目です");
            error2.setVisibility(View.VISIBLE);
            return false;

        }else{
            textnamesub.setError(null);
            error2.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    private  boolean validatestudentsnumber() {
        textstudentsnumber = findViewById(R.id.layout_studentsnumber);
        studentsnumberInput = textstudentsnumber.getEditText().getText().toString().trim();
        if (studentsnumberInput.isEmpty()) {
            textstudentsnumber.setError("必須項目です");
            error3.setVisibility(View.VISIBLE);
            return false;

        }else if(studentsnumberInput.length() >= 1 && studentsnumberInput.length() < 7){
            textstudentsnumber.setError("短すぎます");
            error3.setVisibility(View.VISIBLE);
            return false;
        }
        else if(studentsnumberInput.length() > 7){
            textstudentsnumber.setError("長すぎます");
            error3.setVisibility(View.VISIBLE);
            return false;
        }
        else if( studentsnumberInput.matches("[a-zA-z]")){
            textstudentsnumber.setError("数字のみです");
            error3.setVisibility(View.VISIBLE);
            return false;
        }
        else{
            textstudentsnumber.setError(null);
            error3.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    private  boolean validateaddress() {
        textaddress = findViewById(R.id.layout_address);

        addressInput = textaddress.getEditText().getText().toString().trim();
        if (addressInput.isEmpty()) {
            textaddress.setError("必須項目です");
            error4.setVisibility(View.VISIBLE);
            return false;

        }
        else{
            textaddress.setError(null);
            error4.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    private  boolean validateaddress_return() {
        textaddress_return = findViewById(R.id.layout_address_return);

        address_returnInput = textaddress_return.getEditText().getText().toString().trim();
        if (address_returnInput.isEmpty()) {
            textaddress_return.setError("必須項目です");
            error5.setVisibility(View.VISIBLE);
            return false;

        }
        else{
            textaddress_return.setError(null);
            error5.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    private  boolean validatebirthday() {
        textbirthday = findViewById(R.id.layout_birthday);

        birthdayInput = textbirthday.getEditText().getText().toString().trim();
        sb = new StringBuilder(birthdayInput);
        sb.insert(4, "-");
        sb.insert(7, "-");
        if (birthdayInput.isEmpty()) {
            textbirthday.setError("必須項目です");
            error6.setVisibility(View.VISIBLE);
            return false;

        }
        else if(!BIRTHDAY.matcher(birthdayInput).matches()){
            textbirthday.setError("形式が違います");
            error6.setVisibility(View.VISIBLE);
            return false;
        }
        else{
            textbirthday.setError(null);
            error6.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    private  boolean validatephone_number() {
        textphone_number = findViewById(R.id.layout_phone_number);

        phone_numberInput = textphone_number.getEditText().getText().toString().trim();
        if (phone_numberInput.isEmpty()) {
            textphone_number.setError("必須項目です");
            error7.setVisibility(View.VISIBLE);
            return false;

        }
        else if(!PHONE_NUMBER.matcher(phone_numberInput).matches()){
            textphone_number.setError("形式が違います");
            error7.setVisibility(View.VISIBLE);
            return false;
        }
        else{
            textphone_number.setError(null);
            error7.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    private  boolean validatehome_number() {
        texthome_number = findViewById(R.id.layout_home_number);

        home_numberInput = texthome_number.getEditText().getText().toString().trim();
        if (home_numberInput.isEmpty()) {
            texthome_number.setError("必須項目です");
            error8.setVisibility(View.VISIBLE);
            return false;

        }
        else if(!NUMBER.matcher(home_numberInput).matches()) {
            texthome_number.setError("形式が違います");
            error8.setVisibility(View.VISIBLE);
            return false;
        }else{
            texthome_number.setError(null);
            error8.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    private  boolean validateemail() {
        textmailaddress = findViewById(R.id.layout_mailaddress);
        mailaddressInput = textmailaddress.getEditText().getText().toString().trim();
        if (mailaddressInput.isEmpty()) {
            textmailaddress.setError("必須項目です");
            error9.setVisibility(View.VISIBLE);
            return false;

        }
        else if(!EMAIL_ADDRESS.matcher(mailaddressInput).matches()){
            textmailaddress.setError("形式が違います");
            error9.setVisibility(View.VISIBLE);
            return false;
        }
        else{
            textmailaddress.setError(null);
            error9.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    private  boolean validateteacher() {
        textteacher = findViewById(R.id.layout_teacher);

        teacherInput = textteacher.getEditText().getText().toString().trim();
        if (teacherInput.isEmpty()) {
            textteacher.setError("必須項目です");
            error10.setVisibility(View.VISIBLE);
            return false;

        }
        else{
            textteacher.setError(null);
            error10.setVisibility(View.INVISIBLE);
            return true;
        }
    }


    public void completeInput(View v){
        if(!validatename() |!validatenamesub()| !validatestudentsnumber()|
                !validatebirthday() |!validatephone_number() |!validateemail() | !validatehome_number() |
                !validateaddress() | !validateaddress_return() | !validateteacher()) {

            return;
        }else if (validatename() |validatenamesub()| validatestudentsnumber()|
                    validateaddress() |validateaddress_return() |validateemail() |
                    validatephone_number() | validatehome_number() | validatebirthday()
                    | validateteacher()){
                new AlertDialog.Builder(sign_up_student.this)
                        .setTitle("入力した情報に誤りはありませんか？")
                        .setPositiveButton(
                                "はい",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        RadioGroup radioGroup = findViewById(R.id.sex);
                                        int checkedId = radioGroup.getCheckedRadioButtonId();
                                        String sex = (String)((RadioButton)findViewById(checkedId)).getText();

                                        birthdayInput = sb.toString();

                                        School student = new School(0, "", nameInput, namesubInput, studentsnumberInput, addressInput, address_returnInput,
                                                birthdayInput, sex, phone_numberInput, home_numberInput, mailaddressInput, teacherInput);
                                        Intent intent = new Intent(getApplication(), touroku.class);
                                        intent.putExtra("USER", student);
                                        startActivity(intent);
                                    }
                                })
                        .setNegativeButton(
                                "いいえ",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                        .show();
                return;
        }
    }
}
