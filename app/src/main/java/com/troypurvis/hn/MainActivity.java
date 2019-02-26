package com.troypurvis.hn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GetJsonData.OnDataAvailable {
    private static final String TAG = "MainActivity";
    List<String> topStories = null;
    List<NewsPost> ts = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetJsonData getJsonData = new GetJsonData(this);
        getJsonData.executeOnSameThread();
    }

    @Override
    public void onDataAvailable(List<NewsPost> data) {

        ts = data;

        //for each post id in topStories, create a new NewsPost item from it (get all information
        //associated w/ the post)
        construct();

    }

    public void construct(){

    }
}
