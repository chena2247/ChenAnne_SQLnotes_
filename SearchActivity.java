package com.example.chena2247.mycontactapp_p1_attempt3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        android.content.Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        android.widget.TextView textView = findViewById(R.id.textView4);
        textView.setText(message);
    }
}
