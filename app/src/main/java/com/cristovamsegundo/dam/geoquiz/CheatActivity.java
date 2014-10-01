package com.cristovamsegundo.dam.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends ActionBarActivity {
	
	public static final String EXTRA_ANSWER_IS_TRUE = "com.cristovamsegundo.dam.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = "com.cristovamsegundo.dam.geoquiz.extra_answer_shown";
	private boolean mAnswerIsTrue;
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	
	public void setAnswerShownResult(boolean isAnswerShown){
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat); 
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		
		mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
		
		mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
		
		// Before cheating 
		setAnswerShownResult(false);
		mShowAnswer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mAnswerIsTrue){
                	mAnswerTextView.setText(R.string.true_button);
                } else{
                	mAnswerTextView.setText(R.string.false_button);
                }
                // After pressing, the user cheated
                setAnswerShownResult(true);
            }
        });
	}
}
