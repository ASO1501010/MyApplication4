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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class nittei extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nittei);
        LinearLayout cardLinear = (LinearLayout)this.findViewById(R.id.cardLinear);
        cardLinear.removeAllViews();

        final Schedule schedule = Schedule.getInstance();

        //カードのループ表示
        for(int i=0; i<schedule.start_date.size(); i++) {
            final String company_name = schedule.company.get(i);
            final String start_date = schedule.start_date.get(i);
            final String end_date = schedule.end_date.get(i);
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.activity_nittei_card, null);
            CardView cardView = (CardView) linearLayout.findViewById(R.id.cardView);
            TextView textBox = (TextView) linearLayout.findViewById(R.id.kaisyamei);
            textBox.setText(schedule.company.get(i));

            //日付の挿入
//            Calendar calendar = Calendar.getInstance();
//            calendar.get(Calendar.DATE);
//            calendar.get(Calendar.YEAR);
//            calendar.get(Calendar.MONTH);
//            CharSequence kaisi1  = android.text.format.DateFormat.format("yyyy/M/d", Calendar.getInstance());
            TextView kaisi2 = (TextView) linearLayout.findViewById(R.id.kaisi);
            kaisi2.setText(schedule.start_date.get(i));
//            CharSequence owari1  = android.text.format.DateFormat.format("yyyy/M/d", Calendar.getInstance());
            TextView owari2 = (TextView) linearLayout.findViewById(R.id.owari);
            owari2.setText(schedule.end_date.get(i));
//            DateFormat df = new SimpleDateFormat("M/d");

            // コマンド引数でDateオブジェクトを作成
//            Date d = null;
//            try {
//                d = df.parse(schedule.start_date.get(i));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }

            // Calendarオブジェクトを作成し、上記で作成したDateオブジェクトの日時を設定
            Calendar cal = Calendar.getInstance();
             int year = Integer.parseInt(schedule.start_date.get(i).substring(0, 3));
             int month = Integer.parseInt(schedule.start_date.get(i).substring(5, 6));
             int day = Integer.parseInt(schedule.start_date.get(i).substring(8, 9));

             cal.set(year, month, day);

//            cal.setTime(d);
            String dateText = schedule.start_date.get(i);
            dateText = dateText.substring(5);
            dateText= dateText.replace("-", "/");

//            CharSequence dateText  = android.text.format.DateFormat.format("M/d", calendar);
            TextView date = (TextView) linearLayout.findViewById(R.id.date);
            TextView week = (TextView) linearLayout.findViewById(R.id.week);
            date.setText(dateText);

            //曜日を日本語表記に変換
            switch (cal.get(Calendar.DAY_OF_WEEK)){

                case Calendar.SUNDAY:
                    week.setText("日曜日");
                    break;

                case Calendar.MONDAY:
                    week.setText("月曜日");
                    break;

                case Calendar.TUESDAY:
                    week.setText("火曜日");
                    break;

                case Calendar.WEDNESDAY:
                    week.setText("水曜日");
                    break;

                case Calendar.THURSDAY:
                    week.setText("木曜日");
                    break;

                case Calendar.FRIDAY:
                    week.setText("金曜日");
                    break;

                case Calendar.SATURDAY:
                    week.setText("土曜日");
                    break;

            }

            cardView.setTag(i);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DisplayMetrics metrics = getResources().getDisplayMetrics();
                    int dialogWidth = (int) (metrics.widthPixels * 1.0);
                    final Dialog mDialog = new Dialog(nittei.this);
                    mDialog.setContentView(R.layout.activity_calender_pop); //ここでレイアウトファイルを読み込む
                    mDialog.setTitle("Dialogのタイトル");
                    EditText kaisya = mDialog.findViewById(R.id.name);
                    kaisya.setText(company_name);
                    EditText kaishi = mDialog.findViewById(R.id.Kaisi);
                    kaishi.setText(start_date);
                    EditText owari = mDialog.findViewById(R.id.owari);
                    owari.setText(end_date);
                    WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
                    lp.width = dialogWidth;
                    mDialog.getWindow().setAttributes(lp);
                    mDialog.findViewById(R.id.back).setOnClickListener(
                            new View.OnClickListener() {
                                public void onClick(View v) {
                                    //OKを押したときの処理
                                    mDialog.dismiss();
                                }
                            }
                    );
                    //取得日
                    EditText edit = (EditText)mDialog.findViewById(R.id.name);
                    SpannableStringBuilder company = (SpannableStringBuilder)edit.getText();
                    edit.setText(company.toString());
                    mDialog.show();

                    edit = (EditText)mDialog.findViewById(R.id.Kaisi);
                    SpannableStringBuilder start = (SpannableStringBuilder)edit.getText();
                    edit.setText(start.toString());
                    mDialog.show();

                    edit = (EditText)mDialog.findViewById(R.id.owari);
                    SpannableStringBuilder end = (SpannableStringBuilder)edit.getText();
                    edit.setText(end.toString());
                    mDialog.show();
                }
            });
            cardLinear.addView(linearLayout,i);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int dialogWidth = (int) (metrics.widthPixels * 1.0);
                final Dialog mDialog = new Dialog(nittei.this);
                mDialog.setContentView(R.layout.activity_calender_pop); //ここでレイアウトファイルを読み込む
                mDialog.setTitle("Dialogのタイトル");
                WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
                lp.width = dialogWidth;
                mDialog.getWindow().setAttributes(lp);
                mDialog.findViewById(R.id.touroku).setOnClickListener(
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                //OKを押したときの処理
                                //会社名
                                EditText name = (EditText) mDialog.findViewById(R.id.name);
                                SpannableStringBuilder company = (SpannableStringBuilder) name.getText();
                                Schedule.company.add(company.toString());

                                //開始日
                                EditText kaisi = (EditText) mDialog.findViewById(R.id.Kaisi);
                                SpannableStringBuilder start = (SpannableStringBuilder) kaisi.getText();
                                Schedule.start_date.add(start.toString());

                                //資格名
                                EditText syuuryoubi = (EditText) mDialog.findViewById(R.id.owari);
                                SpannableStringBuilder end = (SpannableStringBuilder) syuuryoubi.getText();
                                Schedule.end_date.add(end.toString());

                                System.out.println("登録：" + company.toString());

                                new AddScheduleAsyncHttpRequest(nittei.this, Schedule.getInstance()).execute(new Param_schedule("https://face-login.herokuapp.com/Info/addSchedule"));

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
                mDialog.findViewById(R.id.back).setOnClickListener(
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                //戻るを押したときの処理
                                mDialog.dismiss();
                            }
                        }
                );
                mDialog.show();
            }
        });
    }
}