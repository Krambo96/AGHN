package com.troypurvis.hn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class TrendingActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    final String TAG = "TrendingActivity";
    List<NewsPost> data = new ArrayList<>();
    TrendingRecyclerViewAdapter adapter;
    List<List<String>> words;
    HashMap<Integer, List<NewsPost>> hs;
    HashMap<String, Integer> map;

    String[] banned = new String[]{"to", "the", "a", "of", "hn:", "hn", "and", "using", "with", "for"};
    HashSet<String> bannedSet = new HashSet<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);

        for(String s : banned)
            bannedSet.add(s);

        words = new ArrayList<>();
        hs = new HashMap<>();
        for(int i = 0; i < 6; i++){
            words.add(new ArrayList<String>());
            hs.put(i, new ArrayList<NewsPost>());
        }

        final Bundle extras = getIntent().getExtras();

        int size = extras.getInt("size");

        long time = System.currentTimeMillis() / 1000;

        Log.d(TAG, "onCreate: size: " + size);
        for(int i = 0; i < size; i++){
            NewsPost p = (NewsPost)getIntent().getParcelableExtra(Integer.toString(i));

            long pTime = Long.parseLong(p.time);

            int numDays = (int) Math.abs(pTime - time) / 60 / 60 / 24;
            Log.d(TAG, "onCreate: new post");
            if(numDays <= 7) {
                data.add(p);
                Log.d(TAG, "onCreate: Post is recent! : " + numDays);
                hs.get(numDays).add(p);
            }else{
                Log.d(TAG, "onCreate: too old to add");
            }
        }

        //for each day in hs, get top 5 most upvoted posts
        for(int i = 0; i < 6; i++){
            /*
            PriorityQueue<NewsPost> pq = new PriorityQueue<>(new Comparator<NewsPost>() {
                @Override
                public int compare(NewsPost o1, NewsPost o2) {
                    int a = Integer.parseInt(o1.score), b = Integer.parseInt(o2.score);

                    return b - a;
                }
            });

            for(NewsPost n : hs.get(i)){
                pq.add(n);
            }

            for(int j = 0; j < 5; j++){
                NewsPost n = pq.poll();

                if(n != null)
                    words.get(i).add(n.title);
            }
            */

            Collections.sort(hs.get(i), new Comparator<NewsPost>() {
                @Override
                public int compare(NewsPost o1, NewsPost o2) {
                    int a = Integer.parseInt(o1.score), b = Integer.parseInt(o2.score);

                    return b - a;
                }
            });
            List<NewsPost> list = hs.get(i);
            for(int j = 0; j < 5; j++){
                if(list.size() > j){
                    words.get(i).add(list.get(j).title);
                }
            }
        }

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());


        int dayOfWeek = 7 - (c.get(Calendar.DAY_OF_WEEK) - 1);

        Log.d(TAG, "onCreate: day of week: " + dayOfWeek);
        RecyclerView recyclerView = findViewById(R.id.trendingrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TrendingRecyclerViewAdapter(this, words);
        adapter.setDayOfWeek(dayOfWeek);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "onItemClick: entered");

        List<NewsPost> list = hs.get(position);


    }
}
