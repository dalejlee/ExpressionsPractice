package com.example.julietoh.expressionpractice;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dalelee.
 */

public class IdentifyActivity extends AppCompatActivity {
    private TextView tvAnswer;
    private TextView tvScore;
    private RadioGroup rButtons;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private RadioButton button4;
//    private RadioButton button5;
//    private RadioButton button6;
    private Button nextButton;
//    private Button homeButton;

    // variables for question
    private int mQuestionNumber = 0;
    private QuestionsLibrary mQuestionsLibrary;
    private ImageView questionImageView;
    private String mCorrectAnswer;

    private int score = 0;
    private boolean attempted;

    // from initializing activity in IdentifyActivity.java from codinginflow.com
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        tvAnswer = findViewById(R.id.textView3);
        tvScore = findViewById(R.id.text_view_score);
        rButtons = findViewById(R.id.radio_group);
        button1 = findViewById(R.id.radioButton1);
        button2 = findViewById(R.id.radioButton2);
        button3 = findViewById(R.id.radioButton3);
        button4 = findViewById(R.id.radioButton4);
//        button5 = findViewById(R.id.radioButton5);  // removed disgust
//        button6 = findViewById(R.id.radioButton6);  // removed fearful
        nextButton = findViewById(R.id.button_confirm_next);
        mQuestionsLibrary = new QuestionsLibrary(this);
        questionImageView = findViewById(R.id.emotion_image);

//        homeButton = findViewById(R.id.home_button);
        // return to home screen TODO
//        homeButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                StartScreen();
//            }

        // start the matching quiz
        incrementPicture();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!attempted) {
                    if (button1.isChecked() || button2.isChecked() || button3.isChecked() || button4.isChecked()) {
                        cAnswer();
                    } else {
                        Toast.makeText(IdentifyActivity.this, "Pick your best guess!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    incrementPicture();
                    tvAnswer.setText("");
                }
            }
        });
    }

    private void incrementPicture() {
        rButtons.clearCheck();
        questionImageView.setBackgroundResource(mQuestionsLibrary.getQuestion(mQuestionNumber));

        mCorrectAnswer = mQuestionsLibrary.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;

        attempted = false;
        nextButton.setText("Check Answer");
    }

    private void cAnswer() {
        attempted = true;
        RadioButton rbChosen = findViewById(rButtons.getCheckedRadioButtonId());
        int answrNum = rButtons.indexOfChild(rbChosen);
        String answr = "";
        if (answrNum == 0) {
            answr = "anger";
        }
        if (answrNum == 1) {
            answr = "surprise";
        }
        if (answrNum == 2) {
            answr = "sad";
        }
        if (answrNum == 3) {
            answr = "happy";
        }
//        if (answrNum == 4) {
//            answr = "disgust";
//        }
//        if (answrNum == 5) {
//            answr = "happy";
//        }

        if (answr.compareTo(mCorrectAnswer) == 0) {
            score++;
            String scr = "Score: " + score + "/20";
            tvScore.setText(scr);

            String ans = "Good Job!";
            tvAnswer.setText(ans);
        }

        else {
            String scr = "Score: " + score + "/20";
            tvScore.setText(scr);

            String ans = "Try again! The answer is " + mCorrectAnswer;
            tvAnswer.setText(ans);
        }

        nextButton.setText("Next Question");
    }

}
