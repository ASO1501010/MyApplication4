package com.example.satokota.myapplication;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class EditUserInfoAsyncHttpRequest extends AsyncTask<Param_user, Void, String> {
    private Activity mActivity;
    private School user;

    public EditUserInfoAsyncHttpRequest(Activity activity, School userInfo) {
        mActivity = activity;
        user = userInfo;
    }

    @Override
    protected String doInBackground(Param_user... params) {
        Param_user param = params[0];
        HttpURLConnection connection = null;
        StringBuilder sb = new StringBuilder();
        try {
            String jsonText = "";

            School user = School.getInstance();
            HashMap<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("school_id", user.school_id);
            jsonMap.put("name", user.name);
            jsonMap.put("kana", user.kana);
            jsonMap.put("number", user.number);
            jsonMap.put("address", user.address);
            jsonMap.put("home_address", user.home_address);
            jsonMap.put("birthday", user.birthday);
            jsonMap.put("sex", user.sex);
            jsonMap.put("phone_number", user.phone_number);
            jsonMap.put("home_number", user.home_number);
            jsonMap.put("mail_address", user.mail_address);
            jsonMap.put("teacher", user.teacher);
            if(jsonMap.size() > 0) {
                JSONObject json = new JSONObject(jsonMap);
                jsonText = json.toString();
            }

//            JSONObject json = new JSONObject();
//            json.put("number", number);
//            json.put("face", jpg.toByteArray());


            URL url = new URL(param.uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30000);//接続タイムアウトを設定する。
            connection.setReadTimeout(30000);//レスポンスデータ読み取りタイムアウトを設定する。
            connection.setRequestMethod("POST");//HTTPのメソッドをPOSTに設定する。
            //ヘッダーを設定する
            connection.setRequestProperty("User-Agent", "Android");
            connection.setRequestProperty("Content-Type","application/json");
//            connection.setRequestProperty("Content-Type","application/octet-stream");
            connection.setDoInput(true);//リクエストのボディ送信を許可する
            connection.setDoOutput(true);//レスポンスのボディ受信を許可する
            connection.setUseCaches(false);//キャッシュを使用しない
            connection.connect();

            // データを投げる
            OutputStream out = new BufferedOutputStream(connection.getOutputStream());
            out.write(jsonText.getBytes("UTF-8"));
//            out.write(number.getBytes("UTF-8"));
//            out.write(jpg.toByteArray());
            out.flush();

            // データを受け取る
            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null)
                sb.append(line);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            connection.disconnect();
        }
        return sb.toString();
    }

//    public void onPostExecute(String string) {
//        // 戻り値をViewにセット
//        TextView textView = (TextView) mActivity.findViewById(R.id.textview);
//        textView.setText(string);
//    }
}
