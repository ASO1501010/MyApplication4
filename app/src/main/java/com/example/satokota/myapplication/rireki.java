package com.example.satokota.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class rireki extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rireki);

        //        名前
        TextView name = (TextView)findViewById(R.id.name);
        name.setText(School.name);

//        ふりがな
        TextView namesub = (TextView)findViewById(R.id.namesub);
        namesub.setText(School.kana);

//        学籍番号
        TextView studentsnumber = (TextView)findViewById(R.id.studentsnumber);
        studentsnumber.setText(School.number);

//        住所
        TextView addres = (TextView)findViewById(R.id.address);
        addres.setText(School.address);

//        連絡先
        TextView address_return = (TextView)findViewById(R.id.address_return);
        address_return.setText(School.home_address);

//        生年月日
        TextView birthday = (TextView)findViewById(R.id.birthday);
        birthday.setText(School.birthday);

//        性別
        TextView sex = (TextView)findViewById(R.id.sex);
        sex.setText(School.sex);

//        携帯番号
        TextView phone_number = (TextView)findViewById(R.id.phone_number);
        phone_number.setText(School.phone_number);

//        自宅番号
        TextView home_number = (TextView)findViewById(R.id.home_number);
        home_number.setText(School.home_number);

//        メールアドレス
        TextView mailaddress = (TextView)findViewById(R.id.mailaddress);
        mailaddress.setText(School.mail_address);

//        担任
        TextView teacher = (TextView)findViewById(R.id.teacher);
        teacher.setText(School.teacher);

        //        erit_buttonボタンで編集画面へ
        View editButton = findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), rireki_edit.class);
                startActivity(intent);
            }
        });
    }
}
