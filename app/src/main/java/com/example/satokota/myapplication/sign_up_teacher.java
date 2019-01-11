package com.example.satokota.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.regex.Pattern;

public class sign_up_teacher extends AppCompatActivity {
    private TextInputLayout textname;
    private TextInputLayout textnamesub;
    private TextInputLayout textteacher_number;
    private TextInputLayout textgakka;
    private TextInputLayout textgakunen;
    private TextInputLayout textmailaddress;
    private TextInputLayout textpassword;

    private String nameInput, namesubInput, teacher_numberInput, gakkaInput, gakunenInput, mailaddressInput;

    ImageView error1 , error2 , error3 ,error4,error5,error6,error7;
    public static final Pattern EMAIL_ADDRESS =Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@"+
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+
                    "("+
                    "\\."+
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+
                    ")+"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_teacher);

        error1 = findViewById(R.id.ei1);
        findViewById(R.id.ei1).setVisibility(View.INVISIBLE);
        error2 = findViewById(R.id.ei2);
        findViewById(R.id.ei2).setVisibility(View.INVISIBLE);
        error3 = findViewById(R.id.ei3);
        findViewById(R.id.ei3).setVisibility(View.INVISIBLE);
        error4 = findViewById(R.id.ei4);
        findViewById(R.id.ei4).setVisibility(View.INVISIBLE);
        error5 = findViewById(R.id.ei5);
        findViewById(R.id.ei5).setVisibility(View.INVISIBLE);
        error6 = findViewById(R.id.ei6);
        findViewById(R.id.ei6).setVisibility(View.INVISIBLE);

        //        sikakuボタンで資格画面へ
//        View nnButton = findViewById(R.id.touroku);
//        nnButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), touroku.class);
//                startActivity(intent);
//            }
//        });
    }

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
    private  boolean validateteacher_number() {
        textteacher_number = findViewById(R.id.layout_teacher_number);
        teacher_numberInput = textteacher_number.getEditText().getText().toString().trim();
        if (teacher_numberInput.isEmpty()) {
            textteacher_number.setError("必須項目です");
            error3.setVisibility(View.VISIBLE);
            return false;

        }else if(teacher_numberInput.length() > 7){
            textteacher_number.setError("長すぎます");
            error3.setVisibility(View.VISIBLE);
            return false;
        }
        else if( teacher_numberInput.matches("[a-zA-Z]")){
            textteacher_number.setError("数字のみです");
            error3.setVisibility(View.VISIBLE);
            return false;
        }
        else{
            textteacher_number.setError(null);
            error3.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    private  boolean validategakka() {
        textgakka = findViewById(R.id.layout_gakka);

        gakkaInput = textgakka.getEditText().getText().toString().trim();
        if (gakkaInput.isEmpty()) {
            textgakka.setError("必須項目です");
            error4.setVisibility(View.VISIBLE);
            return false;

        }
        else{
            textgakka.setError(null);
            error4.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    private  boolean validategakunen() {
        textgakunen = findViewById(R.id.layout_gakunen);

        gakunenInput = textgakunen.getEditText().getText().toString().trim();
        if (gakunenInput.isEmpty()) {
            textgakunen.setError("必須項目です");
            error5.setVisibility(View.VISIBLE);
            return false;

        }
        else if(gakunenInput.length() >1){
            textgakunen.setError("長すぎます");
            error5.setVisibility(View.VISIBLE);
            return false;
        }
        else if( !gakunenInput.matches("[0-9]")){
            textgakunen.setError("数字のみです");
            error5.setVisibility(View.VISIBLE);
            return false;
        }
        else{
            textgakunen.setError(null);
            error5.setVisibility(View.INVISIBLE);
            return true;
        }
    }
    private  boolean validateemail() {
        textmailaddress = findViewById(R.id.layout_mailaddress);
        mailaddressInput = textmailaddress.getEditText().getText().toString().trim();
        if (mailaddressInput.isEmpty()) {
            textmailaddress.setError("必須項目です");
            error6.setVisibility(View.VISIBLE);
            return false;

        }
        else if(!EMAIL_ADDRESS.matcher(mailaddressInput).matches()){
            textmailaddress.setError("形式が違います");
            error6.setVisibility(View.VISIBLE);
            return false;
        }
        else{
            textmailaddress.setError(null);
            error6.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    public void confilmInput(View v){
        if(!validatename() |!validatenamesub()| !validateteacher_number()|
                !validategakka() |!validategakunen() |!validateemail()){

            return;
        } else if (validatename() |validatenamesub()| validateteacher_number()|
                validategakka() |validategakunen() |validateemail()){
            new AlertDialog.Builder(sign_up_teacher.this)
                    .setTitle("入力した情報に誤りはありませんか？")
                    .setPositiveButton(
                            "はい",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    RadioGroup radioGroup = findViewById(R.id.sex);
                                    int checkedId = radioGroup.getCheckedRadioButtonId();
                                    String sex = (String)((RadioButton)findViewById(checkedId)).getText();

                                    School teacher = new School(0, "", nameInput, namesubInput, teacher_numberInput, "", "",
                                            "", sex, "", "", mailaddressInput, "");
                                    Intent intent = new Intent(getApplication(), touroku.class);
                                    intent.putExtra("USER", teacher);
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
