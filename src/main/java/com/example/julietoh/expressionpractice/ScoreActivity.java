package com.example.julietoh.expressionpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by julietoh on 11/1/18.
 */

public class ScoreActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        int score = getIntent().getIntExtra("SCORE", 0);
        TextView resultTextView = findViewById(R.id.score_text);
        Button home_button = findViewById(R.id.home_button);
        final Intent intent = new Intent(this, StartActivity.class);
        home_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        resultTextView.setText(score + "/20");
    }
}
