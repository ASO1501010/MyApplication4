package com.example.satokota.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class rireki_edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rireki_edit);

        //        名前
        EditText name = (EditText) findViewById(R.id.name);
        name.setText(School.name);

        //        ふりがな
        EditText namesub = (EditText)findViewById(R.id.namesub);
        namesub.setText(School.kana);

        //        学籍番号
        EditText studentsnumber = (EditText)findViewById(R.id.studentsnumber);
        studentsnumber.setText(School.number);

        //        住所
        EditText addres = (EditText)findViewById(R.id.address);
        addres.setText(School.address);

        //        連絡先
        EditText address_return = (EditText)findViewById(R.id.address_return);
        address_return.setText(School.home_address);

        //        生年月日
        EditText birthday = (EditText)findViewById(R.id.birthday);
        birthday.setText(School.birthday);


        //        携帯番号
        EditText phone_number = (EditText)findViewById(R.id.phone_number);
        phone_number.setText(School.phone_number);

        //        自宅番号
        EditText home_number = (EditText)findViewById(R.id.home_number);
        home_number.setText(School.home_number);

        //        メールアドレス
        EditText mailaddress = (EditText)findViewById(R.id.mailaddress);
        mailaddress.setText(School.mail_address);

        //        担任
        EditText teacher = (EditText)findViewById(R.id.teacher);
        teacher.setText(School.teacher);

        //        backボタンで前の画面へ
        View completebutton = findViewById(R.id.completebutton);
        completebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText)findViewById(R.id.name);
                SpannableStringBuilder sp = (SpannableStringBuilder)input.getText();
                String name = sp.toString();
                School.name = name;
                input = (EditText)findViewById(R.id.namesub);
                sp = (SpannableStringBuilder)input.getText();
                String kana = sp.toString();
                School.kana = kana;
                input = (EditText)findViewById(R.id.studentsnumber);
                sp = (SpannableStringBuilder)input.getText();
                String number = sp.toString();
                School.number = number;
                input = (EditText)findViewById(R.id.address);
                sp = (SpannableStringBuilder)input.getText();
                String address = sp.toString();
                School.address = address;
                input = (EditText)findViewById(R.id.address_return);
                sp = (SpannableStringBuilder)input.getText();
                String address_return = sp.toString();
                School.home_address = address_return;
                input = (EditText)findViewById(R.id.birthday);
                sp = (SpannableStringBuilder)input.getText();
                String birthday = sp.toString();
                School.birthday = birthday;
                Spinner input_sex = (Spinner)findViewById(R.id.sex);
                String sex = (String)input_sex.getSelectedItem();
                School.sex = sex;
                input = (EditText)findViewById(R.id.phone_number);
                sp = (SpannableStringBuilder)input.getText();
                String phone_number = sp.toString();
                School.phone_number = phone_number;
                input = (EditText)findViewById(R.id.home_number);
                sp = (SpannableStringBuilder)input.getText();
                String home_number = sp.toString();
                School.home_number = home_number;
                input = (EditText)findViewById(R.id.mailaddress);
                sp = (SpannableStringBuilder)input.getText();
                String mailaddress = sp.toString();
                School.mail_address = mailaddress;
                input = (EditText)findViewById(R.id.teacher);
                sp = (SpannableStringBuilder)input.getText();
                String teacher = sp.toString();
                School.teacher = teacher;

                new EditUserInfoAsyncHttpRequest(rireki_edit.this, School.getInstance()).execute(new Param_user("https://face-login.herokuapp.com/Info/editUserInfo"));

                Intent intent = new Intent(getApplication(), rireki.class);
                startActivity(intent);
            }
        });
    }
}
