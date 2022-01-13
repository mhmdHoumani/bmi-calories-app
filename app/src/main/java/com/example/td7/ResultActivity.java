package com.example.td7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private Intent intent;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView result = (TextView) findViewById(R.id.result);
        intent = getIntent();
        int calories = intent.getIntExtra("Calories", -1);
        result.setText("You need to eat "+calories+" calories per day");
    }

    public void returnBack(View view){
        setResult(RESULT_OK, intent);
        finish();
    }
}