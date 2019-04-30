package com.troypurvis.hn;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TrendingRecyclerViewAdapter extends RecyclerView.Adapter<TrendingRecyclerViewAdapter.ViewHolder> {
    final String TAG = "TrendingRecyclerViewAdapter";
    List<List<String>> mData;
    LayoutInflater mInflater;
    MyRecyclerViewAdapter.ItemClickListener mClickListener;

    TrendingRecyclerViewAdapter(Context context, List<List<String>> data){
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.content_trending, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        List<String> list = mData.get(i);

        for(int j = 0; j < list.size(); j++){
            viewHolder.text[j].setText(list.get(j));
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    List<String> getItem(int idx){
        return mData.get(idx);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView[] text;
        ImageView iv;

        ViewHolder(View itemView){
            super(itemView);

            text = new TextView[5];

            text[0] = itemView.findViewById(R.id.tv1);
            text[1] = itemView.findViewById(R.id.tv2);
            text[2] = itemView.findViewById(R.id.tv3);
            text[3] = itemView.findViewById(R.id.tv4);
            text[4] = itemView.findViewById(R.id.tv5);
            iv = itemView.findViewById(R.id.pic);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
