package com.example.satokota.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import net.arnx.jsonic.JSON;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginAsyncHttpRequest extends AsyncTask<Param, Void, String> {
//    private Camera2BasicFragment mActivity;
//
//    public LoginAsyncHttpRequest(Camera2BasicFragment activity) {
//        this.mActivity = activity;
//    }
    private login mActivity;

    public LoginAsyncHttpRequest(login activity) {
        this.mActivity = activity;
    }

    @Override
    protected String doInBackground(Param... params) {
        Param param = params[0];
        HttpURLConnection connection = null;

        School user = new School(0, "","","","","","","","","","","","");
        Qualification qualification = Qualification.getInstance();
        Schedule schedule = Schedule.getInstance();
        String number = "";
        //StringBuilder sb = new StringBuilder();
        try {
            // 画像をjpeg形式でstreamに保存
            ByteArrayOutputStream jpg = new ByteArrayOutputStream();
            param.bmp.compress(Bitmap.CompressFormat.JPEG, 100, jpg);

            URL url = new URL(param.uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30000);//接続タイムアウトを設定する。
            connection.setReadTimeout(30000);//レスポンスデータ読み取りタイムアウトを設定する。
            connection.setRequestMethod("POST");//HTTPのメソッドをPOSTに設定する。
            //ヘッダーを設定する
            connection.setRequestProperty("User-Agent", "Android");
            connection.setRequestProperty("Content-Type","application/octet-stream");
            connection.setDoInput(true);//リクエストのボディ送信を許可する
            connection.setDoOutput(true);//レスポンスのボディ受信を許可する
            connection.setUseCaches(false);//キャッシュを使用しない
            connection.connect();

            // データを投げる
            OutputStream out = new BufferedOutputStream(connection.getOutputStream());
//            out.write(param.bytes);
            out.write(jpg.toByteArray());
            out.flush();

            // データを受け取る
            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            //HashMap<String, Object> jsonMap = new HashMap<>();
            String json = reader.toString();
            //School school = new School();
            //user = JSON.decode(json, School.class);
            //number = user.number;

            String line = "";
//            String[] line = new String[12];
//            System.out.println(reader.readLine());
//            if(reader.readLine() == null) {
//                System.out.println("nullです");
//            }
//            int cnt = 0;
            while ((line = reader.readLine()) != null) {
//                JSONObject jsonObject = new JSONObject(line);
//                String jsonString = jsonObject.toString();
                    System.out.println(line);
                    if(line.equals("login_failed")){
                        number = line;
                        break;
                    }
                    String line_modified = line.replace("{", "");
                    line_modified = line_modified.replace("}", "");
                    line_modified = line_modified.replace("[", "");
                    line_modified = line_modified.replace("]", "");
                    line_modified = line_modified.replace("\"", "");
                    System.out.println(line_modified);
                    String[] data_string = new String[12];
                    data_string = line_modified.split(",");
                    ArrayList<String> data = new ArrayList<String>(Arrays.asList(data_string));
                    data.remove("0");

//                String json_string = "{";
//                json_string += "\"" + data.get(0).substring(0, data.get(0).indexOf(":")) + "\":" +  data.get(0).substring(data.get(0).indexOf(":")+1) + ",";
//                String[] ele_name = {"level", "name", "kana", "number", "address", "home_address", "birthday", "sex", "phone_number", "home_number", "mail_address", "teacher"};
                    user.level = Integer.parseInt(data.get(0).substring(data.get(0).indexOf(":") + 1));
                    user.school_id = data.get(1).substring(data.get(1).indexOf(":") + 1);
                    user.name = data.get(2).substring(data.get(2).indexOf(":") + 1);
                    user.kana = data.get(3).substring(data.get(3).indexOf(":") + 1);
                    user.number = data.get(4).substring(data.get(4).indexOf(":") + 1);
                    user.address = data.get(5).substring(data.get(5).indexOf(":") + 1);
                    user.home_address = data.get(6).substring(data.get(6).indexOf(":") + 1);
                    user.birthday = data.get(7).substring(data.get(7).indexOf(":") + 1);
                    user.sex = data.get(8).substring(data.get(8).indexOf(":") + 1);
                    user.phone_number = data.get(9).substring(data.get(9).indexOf(":") + 1);
                    user.home_number = data.get(10).substring(data.get(10).indexOf(":") + 1);
                    user.mail_address = data.get(11).substring(data.get(11).indexOf(":") + 1);
                    String teacher_tmp = data.get(12).substring(data.get(12).indexOf(":") + 1);
                    ArrayList<String> data_schedule = new ArrayList<String>();
                    if(teacher_tmp.contains("pass_date:")) {
                        user.teacher = teacher_tmp.substring(0, teacher_tmp.indexOf("pass_date:"));
                        teacher_tmp = teacher_tmp.replace(user.teacher, "");
                        ArrayList<String> data_sikaku = new ArrayList<String>();
                        data_sikaku.add(teacher_tmp);
                        for (int i = 13; i < data.size(); i++){
                            data_sikaku.add(data.get(i));
                        }

                        for (int i = 0; i < data_sikaku.size(); i += 2) {
                            qualification.pass_date.add(data_sikaku.get(i).substring(data_sikaku.get(i).indexOf(":") + 1));
                            if(data_sikaku.get(i + 1).contains("start_date:")) {
                                qualification.qualification_name.add(data_sikaku.get(i + 1).substring(data_sikaku.get(i + 1).indexOf(":") + 1, data_sikaku.get(i + 1).indexOf("start_date:"))); //":"以下の文字列のみ取り出す
                                String schedule_tmp = data_sikaku.get(i + 1).replace("qualification_name:" + qualification.qualification_name.get(qualification.qualification_name.size() - 1), "");
                                data_schedule.add(schedule_tmp);
                                //data_schedule.add(data_sikaku.get(i + 1).substring(data_sikaku.get(i + 1).indexOf("start_date:")));
                                for (int j = i + 2; j < data_sikaku.size(); j++){
                                    data_schedule.add(data_sikaku.get(j));
                                }
                                break;
                                //schedule_string_dual = data.get(i + 1).substring(data.get(i + 1).indexOf("company"));
                            }else{
                                qualification.qualification_name.add(data_sikaku.get(i + 1).substring(data_sikaku.get(i + 1).indexOf(":") + 1));
                            }
                        }
                    }else if(teacher_tmp.contains("start_date:")){
                        user.teacher = teacher_tmp.substring(0, teacher_tmp.indexOf("start_date:"));
                        teacher_tmp = teacher_tmp.replace(user.teacher, "");
                        data_schedule.add(teacher_tmp);
                        for (int i = 13; i < data.size(); i++){
                            data_schedule.add(data.get(i));
                        }
                    }else{
                        user.teacher = teacher_tmp;
                    }

                    if(data_schedule.size() > 2){
                        ArrayList<String> start = new ArrayList<String>();
                        ArrayList<String> end = new ArrayList<String>();
                        ArrayList<String > company = new ArrayList<String>();
                        Schedule schedule_array = new Schedule(start, end, company);
                        for(int i = 0; i < data_schedule.size(); i += 3){
                            schedule.start_date.add(data_schedule.get(i).substring(data_schedule.get(i).indexOf(":") + 1));
                            schedule.end_date.add(data_schedule.get(i + 1).substring(data_schedule.get(i + 1).indexOf(":") + 1));
                            schedule.company.add(data_schedule.get(i + 2).substring(data_schedule.get(i + 2).indexOf(":") + 1));
                        }
                    }
//                    System.out.println(user.teacher);
//                    System.out.println(qualification.pass_date.get(qualification.pass_date.size() - 1));
                    //System.out.println(schedule.start_date.get(schedule.start_date.size() - 1));

//                    String cutpoint = teacher_tmp.substring(user.teacher.length(), user.teacher.length() + 1);
//                    //String index_name = teacher_tmp.substring(teacher_tmp.indexOf(cutpoint)+1, teacher_tmp.indexOf(cutpoint)+2);
//                    System.out.println("要素名" + cutpoint);
//                    //String quasche = teacher_tmp.substring(teacher_tmp.indexOf("p") + 1);
//                    //String index_name = data.get(13).substring(0, data.get(13).indexOf(":"));
//                    String schedule_string_dual = "";
//                    switch (cutpoint) {
//                        case "p":
//                            System.out.println("teacher_tmp" + teacher_tmp);
//                            String sikaku_string = teacher_tmp.substring(teacher_tmp.indexOf(cutpoint));
//                            data_string = sikaku_string.split(",");
//                            System.out.println(sikaku_string.split(","));
//                            data = new ArrayList<String>(Arrays.asList(data_string));
//                            for (int i = 0; i < data.size(); i += 2) {
//                                qualification.pass_date.add(data.get(i).substring(data.get(i).indexOf(":") + 1));
//                                if(data.get(i + 1).substring(data.get(i + 1).indexOf(":") + 1).contains("company:")) {
//                                    qualification.qualification_name.add(data.get(i + 1).substring(data.indexOf(":") + 1, data.indexOf("company"))); //":"以下の文字列のみ取り出す
//                                    schedule_string_dual = data.get(i + 1).substring(data.get(i + 1).indexOf("company"));
//                                    break;
//                                }else{
//                                    qualification.qualification_name.add(data.get(i + 1).substring(data.get(i + 1).indexOf(":") + 1));
//                                }
//                            }
//                            System.out.println("date: " + qualification.pass_date.get(qualification.pass_date.size() - 1));
//                            System.out.println("name: " + qualification.qualification_name.get(qualification.qualification_name.size() - 1));
//
//                            break;
//                        case "c":
//                            String schedule_string = teacher_tmp.substring(teacher_tmp.indexOf(cutpoint));
//                            data_string = schedule_string.split(",");
//                            data = new ArrayList<String>(Arrays.asList(data_string));
//                            for (int i = 0; i < data.size(); i += 2) {
//                                schedule.start_date.add(data.get(i).substring(data.get(i).indexOf(":") + 1));
//                                schedule.end_date.add(data.get(i + 1).substring(data.get(i + 1).indexOf(":") + 1));
//                                schedule.company.add(data.get(i + 2).substring(data.get(i + 2).indexOf(":") + 1));
//                            }
//                            System.out.println("start_date: " + schedule.start_date.get(schedule.start_date.size() - 1));
//                            System.out.println("end_date: " + schedule.end_date.get(schedule.end_date.size() - 1));
//                            System.out.println("company: " + schedule.company.get(schedule.company.size() - 1));
//                    }
//
//                    if(!(schedule_string_dual.equals(""))){
//                        data_string = schedule_string_dual.split(",");
//                        data = new ArrayList<String>(Arrays.asList(data_string));
//                        for (int i = 0; i < data.size(); i += 3) {
//                            schedule.start_date.add(data.get(i).substring(data.get(i).indexOf(":") + 1));
//                            qualification.qualification_name.add(data.get(i + 1).substring(data.get(i + 1).indexOf(":") + 1));
//                        }
//                    }

                    //sikaku.date = data.get(11).substring(data.get(11).indexOf(":") + 1);
//                    for (String ele : data) {
//                        System.out.println(ele);
//                    user. = ele.substring(ele.indexOf(":") + 1);
//                    json_string += "\"" + ele.substring(0, ele.indexOf(":")) + "\":\"" +  ele.substring(ele.indexOf(":")+1) + "\",";
//                    }
//                int last = json_string.lastIndexOf(",");
//                json_string = json_string.substring(0, last);
//                json_string += "}";
//                System.out.println(json_string);
//                user = JSON.decode("{\"name\":");
//                user = JSON.decode(json_string, School.class);
//                JSONArray jsonArray = JSONArray.from
                    number = user.number;
                    //cnt++;
                    //sb.append(line);
//                }else if(cnt == 1){
//                    System.out.println(line);
//                    String line_modified = line.replace("{", "");
//                    line_modified = line_modified.replace("}", "");
//                    line_modified = line_modified.replace("\"", "");
//                    System.out.println(line_modified);
//                    String[] data_string = new String[12];
//                    data_string = line_modified.split(",");
//                    ArrayList<String> data = new ArrayList<String>(Arrays.asList(data_string));
//                    data.remove("0");
//
////                String json_string = "{";
////                json_string += "\"" + data.get(0).substring(0, data.get(0).indexOf(":")) + "\":" +  data.get(0).substring(data.get(0).indexOf(":")+1) + ",";
////                String[] ele_name = {"level", "name", "kana", "number", "address", "home_address", "birthday", "sex", "phone_number", "home_number", "mail_address", "teacher"};
//                    for(int i = 0; i < data.size(); i += 2) {
//                        qualification.pass_date.add(data.get(i).substring(data.get(i).indexOf(":") + 1));
//                        qualification.qualification_name.add(data.get(i+1).substring(data.get(i+1).indexOf(":") + 1));
//                    }
//                    System.out.println("date: " + qualification.pass_date.get(qualification.pass_date.size()-1));
//                    System.out.println("name: " + qualification.qualification_name.get(qualification.qualification_name.size()-1));
//                    cnt++;
//                }else{
//                    //System.out.println(line);
//                    String line_modified = line.replace("{", "");
//                    line_modified = line_modified.replace("}", "");
//                    line_modified = line_modified.replace("\"", "");
//                    //System.out.println(line_modified);
//                    String[] data_string = new String[12];
//                    data_string = line_modified.split(",");
//                    ArrayList<String> data = new ArrayList<String>(Arrays.asList(data_string));
//                    data.remove("0");
//
////                String json_string = "{";
////                json_string += "\"" + data.get(0).substring(0, data.get(0).indexOf(":")) + "\":" +  data.get(0).substring(data.get(0).indexOf(":")+1) + ",";
////                String[] ele_name = {"level", "name", "kana", "number", "address", "home_address", "birthday", "sex", "phone_number", "home_number", "mail_address", "teacher"};
//                    schedule.start_date = data.get(0).substring(data.get(0).indexOf(":") + 1);
//                    schedule.end_date = data.get(1).substring(data.get(1).indexOf(":") + 1);
//                    schedule.company = data.get(2).substring(data.get(2).indexOf(":") + 1);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            e.printStackTrace();
        } finally{
            connection.disconnect();
        }
        return number;
        //return sb.toString();
    }

    public void onPostExecute(String num) {
        //System.out.println(num);
        mActivity.setNumber(num);
        mActivity.confirmNumber(num);
        // 戻り値をViewにセット
//        TextView textView = (TextView) mActivity.findViewById(R.id.textview);
//        textView.setText(string);
    }
}

