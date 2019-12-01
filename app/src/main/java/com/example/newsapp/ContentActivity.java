package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {

    private TextView textView_Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        textView_Content = findViewById(R.id.TextView_content);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String content =  bundle.getString("content");
        textView_Content.setText(content);
    }
}
