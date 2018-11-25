package com.example.julietoh.expressionpractice;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by julietoh on 11/17/18.
 */

public class QuestionsLibrary {
    private String[] mCorrectAnswer = {"happy", "sad", "anger", "anger", "anger"};
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

//    public String getItemName(int questionNumber) {
//        int imageid = mImageLibrary.getResourceId(questionNumber, 0);
//        return this.getResources().getResourceName(imageid);
//    }

    /**
     * Returns the correct answer to the question
     */
    public String getCorrectAnswer(int questionNumber) {
        return mCorrectAnswer[questionNumber];
    }
}