package com.troypurvis.hn;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements GetJsonData.OnDataAvailable, MyRecyclerViewAdapter.ItemClickListener {
    private static final String TAG = "MainActivity";
    List<String> topStories = null;
    List<NewsPost> ts = null;
    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetJsonData getJsonData = new GetJsonData(this);
        //getJsonData.executeOnSameThread();
        getJsonData.execute("https://hacker-news.firebaseio.com/v0/topstories.json");
    }

    @Override
    public void onItemClick(View view, int position) {
        String url = adapter.getItem(position).url;

        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void onDataAvailable(List<NewsPost> data) {
        Log.d(TAG, "onDataAvailable: begins ----> " + data.size());

        /*
        List<NewsPost> list = new ArrayList<>();
        for(int i = 0; i < 25; i++){
            list.add(data.get(i));
        }
        */

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);



    }

}
