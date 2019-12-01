package com.example.newsapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<NewsData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView_title;
        public TextView textView_name;

        public MyViewHolder(final View v) {
            super(v);
            textView_title = v.findViewById(R.id.TextView_small_title);
            textView_name = v.findViewById(R.id.TextView_small_name);

            textView_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vi) {
                    Intent intent = new Intent(vi.getContext(),ContentActivity.class);//new Intent(현재액티비티.this,전달할 액티비티.class)
                    intent.putExtra("content",textView_title.getText());
                    vi.getContext().startActivity(intent);
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<NewsData> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        NewsData news = mDataset.get(position);

        holder.textView_title.setText(news.getTitle());
        holder.textView_name.setText(news.getName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset ==null ? 0 : mDataset.size();
    }

    public  NewsData getNews(int position)
    {
        return mDataset!=null? mDataset.get(position):null;
    }
}