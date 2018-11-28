package com.example.julietoh.expressionpractice;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by julietoh on 11/1/18.
 */

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView resultTextView = findViewById(R.id.result_text);
        resultTextView.setText("Correct! \n You expressed joy");
        CountDownTimer CountDownTimer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                // do nothing
            }
            public void onFinish() {
                finish();
            }
        }.start();
    }

}
