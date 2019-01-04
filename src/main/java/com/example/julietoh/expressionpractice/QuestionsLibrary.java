package com.example.julietoh.expressionpractice;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by julietoh on 11/17/18.
 */

public class QuestionsLibrary {

    private String[] mCorrectAnswer = {
            "anger", "happy", "sad",
            "happy", "surprise", "sad",
            "sad", "anger", "happy",
            "anger", "happy", "surprise", "sad",
            "anger", "sad", "happy", "surprise",
            "happy", "sad",
            "surprise" };

    private TypedArray mImageLibrary;

    public QuestionsLibrary(Context context) {
        super();
        mImageLibrary = context.getResources().obtainTypedArray(R.array.QuestionImages);
    }

    /**
     * Returns the resource id of the image question
     */
    public int getQuestion(int questionNumber) {
        return mImageLibrary.getResourceId(questionNumber, 0);
    }

    /**
     * Returns the correct answer to the question
     */
    public String getCorrectAnswer(int questionNumber) {
        return mCorrectAnswer[questionNumber];
    }
}