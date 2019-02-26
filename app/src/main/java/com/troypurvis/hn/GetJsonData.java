package com.troypurvis.hn;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetJsonData implements GetRawData.OnDownloadComplete {
    private static final String TAG = "GetJsonData";

    List<String> storyList = new ArrayList<>();
    List<NewsPost> news = new ArrayList<>();
    private final OnDataAvailable mCallBack;


    interface OnDataAvailable{
        void onDataAvailable(List<NewsPost> data);
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
    public void onDownloadComplete(List<JSONObject> data) {
        Log.d(TAG, "onDownloadComplete: starts");

        try {
            for (JSONObject j : data) {
                // String author, String id, String score, String time, String title, String type, String[] kids
                String author = j.getString("by");
                String id = j.getString("id");
                String score = j.getString("score");
                String time = j.getString("time");
                String title = j.getString("title");
                String type = j.getString("type");
                //TODO: Kids

                NewsPost n = new NewsPost(author, id, score, time, title, type);
                news.add(n);
                Log.d(TAG, "onDownloadComplete: New newspost item: " + n.toString());
            }
        }catch(Exception e){
            Log.e(TAG, "onDownloadComplete parsing news objects: failed");
        }

        if(mCallBack != null){
            mCallBack.onDataAvailable(news);
        }
        Log.d(TAG, "onDownloadComplete: ends");
    }


}
