package com.troypurvis.hn;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRawData extends AsyncTask<String, Void, String> {
    private static final String TAG = "GetRawData";

    private final OnDownloadComplete mCallBack;

    interface OnDownloadComplete{
        void onDownloadComplete(String data);
    }

    public GetRawData(OnDownloadComplete callback){
        mCallBack = callback;
    }

    protected void onPostExecute(String s){
        if(mCallBack != null){
            mCallBack.onDownloadComplete(s);
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
