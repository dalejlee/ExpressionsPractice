package com.example.julietoh.expressionpractice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by julietoh on 11/28/18.
 */

public class StartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button buttonID = findViewById(R.id.button_identification);
        Button buttonIM= findViewById(R.id.button_imitation);
        final Intent intentQuiz = new Intent(this, StartScreen.class);
        final Intent intentMain = new Intent(this, MainActivity.class);
        buttonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentQuiz);
            }
        });
        buttonIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentMain);
            }
        });
    }
}
