package com.example.julietoh.expressionpractice;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by julietoh on 11/17/18.
 */

public class QuestionsLibrary {
    private String[] mCorrectAnswer = {
            "anger", "happy", "sad", "surprise",
        "happy", "anger", "surprise", "sad",
            "sad", "anger", "happy", "surprise",
        "anger", "happy", "surprise", "sad",
        "anger", "sad", "happy", "surprise",
        "happy", "surprise", "anger", "sad",
        "surprise", "sad!", "anger", "happy",
        "happy", "sad", "anger", "surprise",
            "anger", "happy", "sad", "surprise" };

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