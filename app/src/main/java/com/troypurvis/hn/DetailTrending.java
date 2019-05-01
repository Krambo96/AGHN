package com.troypurvis.hn;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DetailTrending extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    List<NewsPost> data;
    MyRecyclerViewAdapter adapter;
    String[] days = new String[]{"Sunday", "Saturday", "Friday", "Thursday", "Wednesday", "Tuesday", "Monday"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_trending);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getWindow().setStatusBarColor(Color.BLACK);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff8000")));

        data = new ArrayList<>();
        Bundle b = getIntent().getExtras();

        int size = b.getInt("size");
        int day = b.getInt("day");
        String title = "Top posts for " + days[day];
        setTitle(title);
        for(int i = 0; i < size; i++){
            NewsPost p = (NewsPost)getIntent().getParcelableExtra(Integer.toString(i));
            data.add(p);
        }

        RecyclerView recyclerView = findViewById(R.id.detailedrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        String url = adapter.getItem(position).url;
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
