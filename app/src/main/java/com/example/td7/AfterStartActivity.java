package com.example.td7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class AfterStartActivity extends AppCompatActivity {

    private EditText weight, height;
    RadioGroup RG;
    private RadioButton male, female;
    private double result = -1;
    private Spinner age;
    private static final int REQ_CODE = 456;
    private Button calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_start);
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.height);
        Button bmi = (Button) findViewById(R.id.bmiResult);
        calories = (Button) findViewById(R.id.calories);
        Button back = (Button) findViewById(R.id.backMainActivity);
        RG = (RadioGroup) findViewById(R.id.gender);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        age = (Spinner) findViewById(R.id.age);

        bmi.setOnClickListener(v -> {
            if (height.getText().toString().isEmpty() || weight.getText().toString().isEmpty())
                Toast.makeText(AfterStartActivity.this,
                        "Both weight & height are required...",
                        Toast.LENGTH_LONG).show();
            else {
                double h = Double.parseDouble(height.getText().toString()) / 100;
                double w = Double.parseDouble(weight.getText().toString());
                double bmiResult = w / (h * h);
                Toast.makeText(AfterStartActivity.this,
                        "Your bmi is " + bmiResult,
                        Toast.LENGTH_LONG).show();
            }
        });

        calories.setOnClickListener(v -> {
            if (height.getText().toString().isEmpty() || weight.getText().toString().isEmpty())
                Toast.makeText(AfterStartActivity.this,
                        "Both weight & height are required...",
                        Toast.LENGTH_LONG).show();
            else {
                if (!(male.isChecked()) && !(female.isChecked()))
                    Toast.makeText(AfterStartActivity.this,
                            "Gender required!!",
                            Toast.LENGTH_LONG).show();
                else {
                    if (age.getSelectedItem().toString().equalsIgnoreCase("None"))
                        Toast.makeText(AfterStartActivity.this,
                                "Age required!!",
                                Toast.LENGTH_LONG).show();
                    else {
                        double p = toPounds(Integer.parseInt(weight.getText().toString()));
                        double i = toInches(Integer.parseInt(height.getText().toString()));
                        if (male.isChecked())
                            result = 665 + (6.3 * p) + (12.9 * i) - (6.8 * 24);
                        else {
                            if (age.getSelectedItem().toString().equalsIgnoreCase("under 24"))
                                result = 655 + (4.3 * p) + (4.7 * i) - (4.7 * 24);
                            else
                                result = 455 + (4.3 * p) + (4.7 * i) - (4.7 * 24);
                        }
                        Intent intent = new Intent(AfterStartActivity.this, ResultActivity.class);
                        intent.putExtra("Calories", (int) result);
                        startActivityForResult(intent, REQ_CODE);
                    }
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void returnBack(View view){
        Intent intent = getIntent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public double toPounds(int wgt) {
        return (wgt * 2.2);
    }

    public double toInches(int hgt) {
        return (hgt * 0.393701);
    }
}
