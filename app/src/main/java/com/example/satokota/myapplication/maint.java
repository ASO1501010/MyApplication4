package com.example.satokota.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class maint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

//        sikakuボタンで資格画面へ
        View sikakuButton = findViewById(R.id.sikaku);
        sikakuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), sikaku.class);
                startActivity(intent);
            }
        });

//        backボタンで前の画面へ
        View backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

//        karendaボタンでカレンダー表示
        View karendaButton = findViewById(R.id.karenda);
        karendaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), calender.class);
                startActivity(intent);
            }
        });

//        rirekiボタンで履歴表示画面へ
        View rirekibutton = findViewById(R.id.rireki);
        rirekibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), rireki.class);
                startActivity(intent);
            }
        });

//        logoutボタンでログアウトのダイアログを出す
        View logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(maint.this)
                        .setTitle("警告")
                        .setMessage("ログアウトしますか？")
                        .setPositiveButton("はい", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // OKボタン押した時の動作
                                Intent intent = new Intent(getApplication(), title.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("いいえ", null)
                        .show();
            }
        });

    }
}
