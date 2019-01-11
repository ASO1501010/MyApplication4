package com.example.satokota.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class kizaru extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kizaru);

        ((Button) findViewById(R.id.back))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
//        String hiduke = intent.getStringExtra("date");
        String year = String.valueOf(intent.getIntExtra("year", 0));
        System.out.println(intent.getStringExtra("year"));
        System.out.println(year);
        String month = String.valueOf(intent.getIntExtra("month", 0));
        if(month.length() == 1){
            month = "0" + month;
        }
        String day = String.valueOf(intent.getIntExtra("day", 0));
        if(day.length() == 1){
            day = "0" + day;
        }
        String hiduke = year + "年" + month + "月" + day +"日";

        String date = year + "-" + month + "-" + day;
        System.out.println(date);
        int index = Schedule.start_date.indexOf(date);
        System.out.println(index);
        if(index != -1) {
            System.out.println(Schedule.company.get(index));
            TextView txvEvent = (TextView) findViewById(R.id.textView3);
            txvEvent.setText(Schedule.company.get(index));
        }

        TextView txvHiduke = (TextView)findViewById(R.id.hiduke);
        txvHiduke.setText(hiduke);
    }
}
