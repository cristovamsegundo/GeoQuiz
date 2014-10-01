package com.cristovamsegundo.dam.geoquiz;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private TrueFalse[] mAnswerKey = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true)
    };
    private int mCurrentIndex = 0;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private boolean mIsCheater;
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
    	if(data == null){
    		return;
    	}
    	mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_quiz);
        
        mCheatButton = (Button)findViewById(R.id.cheat_button); 
        mCheatButton.setOnClickListener(new View.OnClickListener() { 
        	@Override
        	public void onClick(View v) { 
        		Intent i = new Intent(QuizActivity.this, CheatActivity.class);
        		boolean answerIsTrue = mAnswerKey[mCurrentIndex].isTrueQuestion();
        		i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        		startActivityForResult(i,0);
        	} 
        });
        
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        // Restore the saved instance
        if (savedInstanceState != null) { 
        	mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0); 
        }
        updateQuestion();
        
        // Question view widget
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mCurrentIndex = (mCurrentIndex + 1) % mAnswerKey.length;
                updateQuestion();
			}
		});
        
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        
        mPrevButton = (Button)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentIndex = mCurrentIndex <=0 ? 0 : (mCurrentIndex - 1);
                updateQuestion();
            }
        });

        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mAnswerKey.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mAnswerKey[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;
        if(mIsCheater){
        	messageResId = R.string.judgement_toast;
        }else{
	        if (userPressedTrue==answerIsTrue){
	            messageResId = R.string.correct_toast;
	        }else{
	            messageResId = R.string.incorrect_toast;
	        }
        }
	    Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
        
    }

    private void updateQuestion(){
        int question = mAnswerKey[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) { 
    	super.onSaveInstanceState(savedInstanceState); 
    	Log.i(TAG, "onSaveInstanceState"); 
    	savedInstanceState.putInt(KEY_INDEX, mCurrentIndex); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
