package com.example.satokota.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Base64;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class AddQualificationAsyncHttpRequest extends AsyncTask<Param_qualification, Void, String> {
    private Activity mActivity;
    private Qualification qualification;

    public AddQualificationAsyncHttpRequest(Activity activity, Qualification inQualification) {
        mActivity = activity;
        qualification = inQualification;
    }

    @Override
    protected String doInBackground(Param_qualification... params) {
        Param_qualification param = params[0];
        HttpURLConnection connection = null;
        StringBuilder sb = new StringBuilder();
        try {
            String jsonText = "";

            School user = School.getInstance();
            HashMap<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("school_id", user.school_id);
            jsonMap.put("qualification_name", qualification.qualification_name.get(qualification.qualification_name.size()-1));
            jsonMap.put("pass_date", qualification.pass_date.get(qualification.qualification_name.size()-1));
//            jsonMap.put("face", jpg.toByteArray());
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