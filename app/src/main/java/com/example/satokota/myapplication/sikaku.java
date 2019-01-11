package com.example.satokota.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.SpannableStringBuilder;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class sikaku extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sikaku);
        LinearLayout cardLinear = (LinearLayout)this.findViewById(R.id.cardLinear);
        cardLinear.removeAllViews();

        final Qualification sikaku = Qualification.getInstance();

        for(int i=0; i<sikaku.qualification_name.size(); i++) {
            final String get_date = sikaku.pass_date.get(i);
            final String sikaku_name = sikaku.qualification_name.get(i);
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.activity_sikaku_card, null);
            CardView cardView = (CardView) linearLayout.findViewById(R.id.cardView);
            TextView textBox = (TextView) linearLayout.findViewById(R.id.siakumei);
            textBox.setText(sikaku.qualification_name.get(i));

//            本来は取得年月を表示するが現在の日付を今は取得して当てはめる
//            Calendar calendar = Calendar.getInstance();

//            calendar.get(Calendar.YEAR);
//            calendar.get(Calendar.MONTH);
//            CharSequence dateText  = android.text.format.DateFormat.format("yyyy-MM-dd", Calendar.getInstance());
            TextView syutokubi = (TextView) linearLayout.findViewById(R.id.syutokubi);
            syutokubi.setText(sikaku.pass_date.get(i));
            cardLinear.addView(linearLayout, i);

//            資格カードをタップした時の動作　ダイアログの内容はlayout/mydialog.xmlに記載
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DisplayMetrics metrics = getResources().getDisplayMetrics();
                    int dialogWidth = (int) (metrics.widthPixels * 0.8);
                    final Dialog mDialog = new Dialog(sikaku.this);
                    mDialog.setContentView(R.layout.mydialog); //ここでレイアウトファイルを読み込む
                    mDialog.setTitle("Dialogのタイトル");
                    EditText pass_date = mDialog.findViewById(R.id.syutokubi);
                    pass_date.setText(get_date);
                    EditText name = mDialog.findViewById(R.id.sikakumei);
                    name.setText(sikaku_name);

                    WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
                    lp.width = dialogWidth;
                    mDialog.getWindow().setAttributes(lp);
                    mDialog.findViewById(R.id.okbutton).setOnClickListener(
                            new View.OnClickListener() {
                                public void onClick(View v) {
                                    //OKを押したときの処理
                                    mDialog.dismiss();
                                }
                            }
                    );
                    mDialog.show();
                }
            });
        }

//        fabをタップした時の動作　ダイアログの内容はlayout/mydialog.xmlに記載
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int dialogWidth = (int) (metrics.widthPixels * 0.8);
                final Dialog mDialog = new Dialog(sikaku.this);
                mDialog.setContentView(R.layout.mydialog); //ここでレイアウトファイルを読み込む
                mDialog.setTitle("Dialogのタイトル");
                WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
                lp.width = dialogWidth;
                mDialog.getWindow().setAttributes(lp);
                mDialog.findViewById(R.id.okbutton).setOnClickListener(
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                //OKを押したときの処理
                                //取得日
                                EditText edit = (EditText)mDialog.findViewById(R.id.syutokubi);
                                SpannableStringBuilder syutokubi = (SpannableStringBuilder)edit.getText();
                                Qualification.pass_date.add(syutokubi.toString());

                                //資格名
                                EditText sikaku = (EditText)mDialog.findViewById(R.id.sikakumei);
                                SpannableStringBuilder sikakumei = (SpannableStringBuilder)sikaku.getText();
                                Qualification.qualification_name.add(sikakumei.toString());

                                new AddQualificationAsyncHttpRequest(sikaku.this, Qualification.getInstance()).execute(new Param_qualification("https://face-login.herokuapp.com/Info/addQualification"));

                                mDialog.dismiss();

                                Intent intent = getIntent();
                                overridePendingTransition(0, 0);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                finish();

                                overridePendingTransition(0, 0);
                                startActivity(intent);
                            }
                        }
                );
                mDialog.show();
            }
        });
    }
}
