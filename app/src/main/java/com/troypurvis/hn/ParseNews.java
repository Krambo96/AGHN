package com.troypurvis.hn;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParseNews extends AsyncTask<String, Void, List<JSONObject>> {
    private final String TAG = "ParseNews";
    private final GetRawData.OnDownloadComplete mCallBack;
    private final String prefix = "https://hacker-news.firebaseio.com/v0/item/";

    ParseNews(GetRawData.OnDownloadComplete callBack){
        this.mCallBack = callBack;
    }

    @Override
    protected void onPostExecute(List<JSONObject> jsonObjects) {

        if(mCallBack != null && jsonObjects != null){
            mCallBack.onDownloadComplete(jsonObjects);
        }
    }

    @Override
    protected List<JSONObject> doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        List<JSONObject> list = new ArrayList<>();

        try{
            for(int i = 0; i < strings.length; i++){
                String uri = prefix + strings[i] + ".json";
                URL url = new URL(uri);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int response = connection.getResponseCode();

                StringBuilder str = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while(null != (line = reader.readLine())){
                    str.append(line).append("\n");
                }

                String temp = str.toString();

                JSONObject obj = new JSONObject(temp);
                list.add(obj);
            }

            return list;

        }catch(Exception e){
            Log.e(TAG, "doInBackground: failed");
        }finally {
            if(connection != null){
                connection.disconnect();
            }

            if(reader != null){
                try{
                    reader.close();
                }catch(Exception e){

                }
            }
        }

        return null;
    }
}
