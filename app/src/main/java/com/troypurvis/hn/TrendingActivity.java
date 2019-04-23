package com.troypurvis.hn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
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

        for(int i = 0; i < size; i++){
            NewsPost p = (NewsPost)getIntent().getParcelableExtra(Integer.toString(i));
            Log.d(TAG, "onCreate: " + p.toString());
            data.add(p);
        }

    }

}
