package com.troypurvis.hn;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetJsonData implements GetRawData.OnDownloadComplete {
    private static final String TAG = "GetJsonData";

    List<String> storyList = new ArrayList<>();
    private final OnDataAvailable mCallBack;


    interface OnDataAvailable{
        void onDataAvailable(List<String> data);
    }

    public GetJsonData(OnDataAvailable callback){
        mCallBack = callback;
    }

    void executeOnSameThread(){
        Log.d(TAG, "executeOnSameThread: starts");
        GetRawData getRawData = new GetRawData(this);
        getRawData.execute("https://hacker-news.firebaseio.com/v0/topstories.json");
        Log.d(TAG, "executeOnSameThread: ends");
    }

    @Override
    public void onDownloadComplete(String data) {
        Log.d(TAG, "onDownloadComplete: starts");
        try{
            JSONArray jsonArray = new JSONArray(data);

            for(int i = 0; i < jsonArray.length(); i++){
                storyList.add(jsonArray.get(i).toString());
            }
        }catch(Exception e){

        }

        if(mCallBack != null){
            mCallBack.onDataAvailable(storyList);
        }
        Log.d(TAG, "onDownloadComplete: ends");
    }


}
