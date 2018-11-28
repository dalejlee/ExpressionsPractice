package com.example.julietoh.expressionpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {
    private TextView textViewAnswer;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;
    private RadioButton rb6;
    private Button buttonConfirmNext;

//    private ColorStateList textColorDefaultRb;

    // variables for question
    private String question = "What is this emotion?";
    private int questionCounter;
    private int mQuestionNumber = 0;
    private int mScore = 0;
    private QuestionsLibrary mQuestionsLibrary;
    private int questionCountTotal;
    private Question currentQuestion;
    private ImageView questionImageView;
    private String imageName;
    private String mCorrectAnswer;

    private int score = 0;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        textViewAnswer = findViewById(R.id.textView3);
        textViewScore = findViewById(R.id.text_view_score);
//        textViewQuestionCount = findViewById(R.id.text_view_question_count);
//        textViewCountDown = findViewById(R.id.text_view_countdown);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);
        rb6 = findViewById(R.id.radioButton6);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);
        mQuestionsLibrary = new QuestionsLibrary(this);
        questionImageView = findViewById(R.id.emotion_image);
//        textColorDefaultRb = rb1.getTextColors();
//
//        question = "What is this emotion?"
//        QuizDbHelper dbHelper = new QuizDbHelper(this);
//        questionList = dbHelper.getAllQuestions();
//        questionCountTotal = questionList.size();
//        Collections.shuffle(questionList);

        showNextQuestion();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked() || rb5.isChecked() || rb6.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                    textViewAnswer.setText("");
                }
            }
        });
    }

    private void showNextQuestion() {
//        rb1.setTextColor(textColorDefaultRb);
//        rb2.setTextColor(textColorDefaultRb);
//        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

//        if (questionCounter < questionCountTotal) {
//            currentQuestion = questionList.get(questionCounter);
//            textViewQuestion.setText(currentQuestion.getQuestion());
//            rb1.setText(currentQuestion.getOption1());
//            rb2.setText(currentQuestion.getOption2());
//            rb3.setText(currentQuestion.getOption3());

        questionImageView.setBackgroundResource(mQuestionsLibrary.getQuestion(mQuestionNumber));
//        questionImageView.setBackgroundResource(R.drawable.anger_2);

        mCorrectAnswer = mQuestionsLibrary.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;
        questionCounter++;

        Toast.makeText(this, mCorrectAnswer,
                Toast.LENGTH_SHORT).show();




//            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
        answered = false;
        buttonConfirmNext.setText("Confirm");
//        } else {
//            finishQuiz();
    }

    private void checkAnswer() {
        answered = true;

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
        String answr = "";
        if (answerNr == 1) {
            answr = "anger";
        }
        if (answerNr == 2) {
            answr = "fearful";
        }
        if (answerNr == 3) {
            answr = "surprise";
        }
        if (answerNr == 4) {
            answr = "sad";
        }
        if (answerNr == 5) {
            answr = "disgust";
        }
        if (answerNr == 6) {
            answr = "happy";
        }

        if (answr.compareTo(mCorrectAnswer) == 0) {
            score++;
            String scr = "Score: " + score;
            textViewScore.setText(scr);
        }

        showSolution();
    }

    private void showSolution() {
//        rb1.setTextColor(Color.RED);
//        rb2.setTextColor(Color.RED);
//        rb3.setTextColor(Color.RED);

        String ans = "Answer is " + mCorrectAnswer;
        textViewAnswer.setText(ans);
//        switch (currentQuestion.getAnswerNr()) {

//            case 1:
//                rb1.setTextColor(Color.GREEN);
//                textViewAnswer.setText("Answer is happy");
//                break;
//            case 2:
//                rb2.setTextColor(Color.GREEN);
//                textViewAnswer.setText("Answer is sad");
//                break;
//            case 3:
//                rb3.setTextColor(Color.GREEN);
//                textViewAnswer.setText("Answer is angry");
//                break;
//        }

//        if (questionCounter < questionCountTotal) {
        buttonConfirmNext.setText("Next");
//        } else {
//            buttonConfirmNext.setText("Finish");
//        }
    }

    private void finishQuiz() {
        finish();
    }

}