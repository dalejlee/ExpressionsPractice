package com.example.julietoh.expressionpractice;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by julietoh on 11/17/18.
 */

public class QuestionsLibrary {
    private String[] mCorrectAnswer = {
            "anger", "happy", "sad", "surprise", "happy", "surprise", "sad",
        "happy", "anger", "sad", "surprise", "sad", "surprise", "happy",
        "surprise", "sad", "anger", "happy", "sad", "happy", "surprise",
        "sad", "anger", "surprise", "happy", "surprise", "sad", "happy",
        "anger", "sad", "sad", "happy", "surprise", "surprise", "happy",
        "happy", "surprise", "sad", "happy", "anger", "surprise", "sad",
        "surprise", "sad", "happy", "anger", "sad", "happy", "surprise",
        "happy", "happy", "sad", "anger", "surprise", "sad", "surprise",
        "sad", "surprise", "anger", "happy", "happy", "sad", "surprise" };

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