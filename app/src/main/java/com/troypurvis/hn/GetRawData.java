package com.troypurvis.hn;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetRawData extends AsyncTask<String, Void, String> {
    private static final String TAG = "GetRawData";

    private final OnDownloadComplete mCallBack;

    List<JSONObject> list = new ArrayList<>();

    interface OnDownloadComplete{
        void onDownloadComplete(List<JSONObject> data);
    }

    public GetRawData(OnDownloadComplete callback){
        mCallBack = callback;
    }


    void runInSameThread(String s){
        onPostExecute(doInBackground(s));
    }

    protected void onPostExecute(String s){
        if(mCallBack != null){
            Log.d(TAG, "onPostExecute getrawdata: starts");
            ParseNews pn = new ParseNews(mCallBack);
            try{
                JSONArray jsonArray = new JSONArray(s);
                //n = number of posts fetched
                int n = 400;
                String[] str = new String[n];
                for(int i = 0; i < n; i++){
                    str[i] = jsonArray.get(i).toString();
                }


                pn.executeOnSameThread(str);

            }catch(Exception e){

            }


            //mCallBack.onDownloadComplete(s);
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: starts");

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try{
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int response = connection.getResponseCode();
            StringBuilder result = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while(null != (line = reader.readLine())){
                result.append(line).append("\n");
            }

            return result.toString();

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
