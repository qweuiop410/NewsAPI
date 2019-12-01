package com.example.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private RequestQueue queue;

    public TextView textView_main;
    public TextView textView_mainName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        queue = Volley.newRequestQueue(this);

        textView_main = findViewById(R.id.TextView_mainTitle);
        textView_mainName= findViewById(R.id.TextView_mainName);
        getNews();
    }

    public  void getNews()
    {
        String url = "https://newsapi.org/v2/top-headlines?country=kr&apiKey=서비스키";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //response -> NewsData Class 분류
                            final List<NewsData> news = new ArrayList<NewsData>();

                            JSONObject jsonObj = new JSONObject(response);
                            final JSONArray arrayArticles = jsonObj.getJSONArray("articles");
                            JSONObject obj =  arrayArticles.getJSONObject(0);
                            Log.d(" GET NEWS    : ", obj.toString());

                            //메인 이미지, 제목
                            if(!obj.getString("urlToImage").equals("null")) {
                                Log.d("     URI     : ",obj.getString("urlToImage"));
                                Uri uri = Uri.parse(obj.getString("urlToImage"));
                                SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.ImageView_mainImg);
                                draweeView.setImageURI(uri);
                            }
                            else
                                Log.d("     ELSE     : ",obj.getString("urlToImage"));

                            textView_main.setText(obj.getString("title"));
                            textView_mainName.setText(obj.getJSONObject("source").getString("name"));

                            //하단 뉴스 제목, 출처
                            for(int i = 1 , j= arrayArticles.length() ; i < j ; i++)
                            {
                                obj =  arrayArticles.getJSONObject(i);

                                NewsData newsData = new NewsData();
                                newsData.setTitle( obj.getString("title"));
                                newsData.setName( obj.getJSONObject("source").getString("name"));
                                news.add(newsData);
                            }

                            mAdapter = new MyAdapter(news);
                            recyclerView.setAdapter(mAdapter);
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();

                            Log.d("News",e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}