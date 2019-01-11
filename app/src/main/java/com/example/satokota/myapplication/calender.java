package com.example.satokota.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.text.SpannableStringBuilder;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;

import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;


public class calender extends AppCompatActivity implements OnDateChangeListener{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        RelativeLayout relativeLayout = (RelativeLayout) inflater.inflate(R.layout.activity_calender, null);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(this);
        FloatingActionButton popupButton = (FloatingActionButton) findViewById(R.id.add);

        //ポップアップを表示するボタ

        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int dialogWidth = (int) (metrics.widthPixels * 1.0);
                final Dialog mDialog = new Dialog(calender.this);
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
                                ArrayList<String> comapny_name = new ArrayList<>();
                                EditText name = (EditText) mDialog.findViewById(R.id.name);
                                SpannableStringBuilder company = (SpannableStringBuilder) name.getText();
                                comapny_name.add(company.toString());
                                //Schedule.company.add(company.toString());

                                //開始日
                                ArrayList<String> start_date = new ArrayList<>();
                                EditText kaisi = (EditText)mDialog.findViewById(R.id.Kaisi);
                                SpannableStringBuilder start = (SpannableStringBuilder) kaisi.getText();
                                start_date.add(start.toString());
                                //Schedule.start_date.add(start.toString());

                                //資格名
                                ArrayList<String> end_date = new ArrayList<>();
                                EditText syuuryoubi = (EditText)mDialog.findViewById(R.id.owari);
                                SpannableStringBuilder end = (SpannableStringBuilder) syuuryoubi.getText();
                                end_date.add(end.toString());
                                //Schedule.end_date.add(end.toString());
                                if(Schedule.start_date == null) {
                                    Schedule schedule = new Schedule(start_date, end_date, comapny_name);
                                }else{
                                    Schedule.start_date.add(start.toString());
                                    Schedule.end_date.add(end.toString());
                                    Schedule.company.add(company.toString());
                                }

                                //System.out.println("登録：" + company.toString());

                                new AddScheduleAsyncHttpRequest(calender.this, Schedule.getInstance()).execute(new Param_schedule("https://face-login.herokuapp.com/Info/addSchedule"));

                                mDialog.dismiss();
                            }
                        }
                );
                mDialog.findViewById(R.id.back).setOnClickListener(
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

        //        karendaボタンでカレンダー表示
        View itiranButton = findViewById(R.id.itiran);
        itiranButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), nittei.class);
                startActivity(intent);
            }
        });
    }


    public void onSelectedDayChange(CalendarView view, int year, int month,
                                    int dayOfMonth) {
        Intent intent = new Intent(getApplication(), kizaru.class);
        intent.putExtra("year", year);
        intent.putExtra("month", month + 1);
        intent.putExtra("day", dayOfMonth);
//        intent.putExtra( "date", String.valueOf(year)+"年" +String.valueOf(month+1)+"月"
//                +String.valueOf(dayOfMonth)+"日" );
        startActivity(intent);
    }

}
