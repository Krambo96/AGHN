package com.troypurvis.hn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TrendingActivity extends AppCompatActivity {
    final String TAG = "TrendingActivity";
    List<NewsPost> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);

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
            }else{
                Log.d(TAG, "onCreate: too old to add");
            }
        }

    }

}
