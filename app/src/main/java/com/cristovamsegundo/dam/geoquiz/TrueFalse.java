package com.cristovamsegundo.dam.geoquiz;

/**
 * Created by Cristovam on 22/09/2014.
 */
public class TrueFalse {
    private int mQuestion;
    // This variable will hold a resource ID for a string

    private boolean mTrueQuestion;

    public TrueFalse(int question, boolean trueQuestion){
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean mTrueFalseQuestion) {
        this.mTrueQuestion = mTrueFalseQuestion;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

}
