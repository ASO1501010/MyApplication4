package com.example.satokota.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class sub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        //        生徒名表示
        TextView txvHiduke = (TextView)findViewById(R.id.name);
        txvHiduke.setText(School.name);

//        sikakuボタンで資格画面へ
        Button sikakuButton = findViewById(R.id.sikaku);
        sikakuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), sikaku.class);
                startActivity(intent);
            }
        });

//        backボタンで前の画面へ
//        Button backButton = findViewById(R.id.back);
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), MainActivity.class);
//                startActivity(intent);
//            }
//        });

//        karendaボタンでカレンダー表示
        Button karendaButton = findViewById(R.id.carenda);
        karendaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), calender.class);
                startActivity(intent);
            }
        });

//        rirekiボタンで履歴表示画面へ
        Button rirekibutton = findViewById(R.id.rireki);
        rirekibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), rireki.class);
                startActivity(intent);
            }
        });

//        logoutボタンでログアウトのダイアログを出す
        Button logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(sub.this)
                        .setTitle("警告")
                        .setMessage("ログアウトしますか？")
                        .setPositiveButton("はい", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // OKボタン押した時の動作
                                Qualification.pass_date.clear();
                                Qualification.qualification_name.clear();
//                                Schedule.start_date.clear();
//                                Schedule.end_date.clear();
//                                Schedule.company.clear();

                                Intent intent = new Intent(getApplication(), title.class);
                                startActivity(intent);

                            }
                        })
                        .setNegativeButton("いいえ", null)
                        .show();
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(sub.this)
                    .setTitle("警告")
                    .setMessage("ログアウトしますか？")
                    .setPositiveButton("はい", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // OKボタン押した時の動作
                            Qualification.pass_date.clear();
                            Qualification.qualification_name.clear();
                            Schedule.start_date.clear();
                            Schedule.end_date.clear();
                            Schedule.company.clear();

                            Intent intent = new Intent(getApplication(), title.class);
                            startActivity(intent);

                        }
                    })
                    .setNegativeButton("いいえ", null)
                    .show();
        }
        return false;
    }
}
