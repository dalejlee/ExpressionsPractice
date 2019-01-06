package com.example.julietoh.expressionpractice;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by julietoh on 11/17/18.
 */

public class QuestionsLibraryScenario {
    private String[] mCorrectAnswer = {
            "anger", "sad",
            "happy",
            "sad", "happy",
            "anger", "happy", "surprise",
            "happy",
            "happy", "surprise", "anger", "sad",
            "surprise", "sad",
            "surprise",
            "anger", "happy", "sad", "surprise" };

    private TypedArray mImageLibrary;

    public QuestionsLibraryScenario(Context context) {
        super();
        mImageLibrary = context.getResources().obtainTypedArray(R.array.QuestionImagesScenario);
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