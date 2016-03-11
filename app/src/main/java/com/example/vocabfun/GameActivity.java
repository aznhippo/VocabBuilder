package com.example.vocabfun;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends Activity{
	
	WordPack pack;
	int correctAnswer;
	int score = 0;
	int streak = 0;
	int currentWord = 0;
	boolean firstTry = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		pack = new WordPack("pack1.txt", getApplicationContext());
		pack.createWordPack();
		pack.makePOSArrays();
		
		newWord();
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	}
	 
	//set the new word to test
	//set UI
	public void newWord(){
		//set 'continue' button to invisible
		View b = findViewById(R.id.button5);
		b.setVisibility(View.INVISIBLE);
		//change button colors back to blue
		changeButtonColors(true);
		
		firstTry = true;
		
		int wordNum = randomInt(0, pack.numWords);
		currentWord = wordNum;
		//set the word being tested
		TextView tv1 = (TextView) this.findViewById(R.id.word);
		tv1.setText(pack.getWord(wordNum));
		//set the part of speech
		TextView tv2 = (TextView) this.findViewById(R.id.POS);
		tv2.setText(pack.getPOS(wordNum));
		setDefinitions(wordNum);	
	}
	 
	public void setDefinitions(int correctID){
		int[] wordID = {0,0,0,0};
		
		//select 4 wordID's of the same part of speech as the word being tested
		// making sure no 2 are the same
		String POS = pack.getPOS(correctID);
		if (POS.equals("n")){
			for (int i = 0; i < 4; i++){
				int rand = randomInt(0, pack.nIndex-1);
				while (rand == wordID[0] || rand == wordID[1] || rand == wordID[2])
					rand = randomInt(0, pack.nIndex-1);
				int wordNum = pack.nouns[rand];
				wordID[i] = wordNum;
			}
		}
		else if (POS.equals("adj")){
			for (int i = 0; i < 4; i++){
				int rand = randomInt(0, pack.adjIndex-1);
				while (rand == wordID[0] || rand == wordID[1] || rand == wordID[2])
					rand = randomInt(0, pack.adjIndex-1);
				int wordNum = pack.adjectives[rand];
				wordID[i] = wordNum;
			}
		}
		else if (POS.equals("v")){
			for (int i = 0; i < 4; i++){
				int rand = randomInt(0, pack.vIndex-1);
				while (rand == wordID[0] || rand == wordID[1] || rand == wordID[2])
					rand = randomInt(0, pack.vIndex-1);
				int wordNum = pack.verbs[rand];
				wordID[i] = wordNum;
			}
		}
		else if (POS.equals("adv")){
			for (int i = 0; i < 4; i++){
				int rand = randomInt(0, pack.advIndex-1);
				while (rand == wordID[0] || rand == wordID[1] || rand == wordID[2])
					rand = randomInt(0, pack.advIndex-1);
				int wordNum = pack.adverbs[rand];
				wordID[i] = wordNum;
			}
		}
		
		int correct = randomInt(0,3);
		wordID[correct] = correctID;
		correctAnswer = correct+1;
		
		//set the 4 possible definitions
		Button b1 = (Button)findViewById(R.id.button1);
		b1.setText(pack.getDef(wordID[0]));
		Button b2 = (Button)findViewById(R.id.button2);
		b2.setText(pack.getDef(wordID[1]));
		Button b3 = (Button)findViewById(R.id.button3);
		b3.setText(pack.getDef(wordID[2]));
		Button b4 = (Button)findViewById(R.id.button4);
		b4.setText(pack.getDef(wordID[3]));
	}

	
	//create buttons for each choice
	public void choice1(View v){
		if (correctAnswer == 1)
			rightAnswer();
		else
			wrongAnswer();
		firstTry = false;
		changeButtonColors(false);
	}
	public void choice2(View v){
		if (correctAnswer == 2)
			rightAnswer();
		else
			wrongAnswer();
		firstTry = false;
		changeButtonColors(false);
	}
	public void choice3(View v){
		if (correctAnswer == 3)
			rightAnswer();
		else
			wrongAnswer();
		firstTry = false;
		changeButtonColors(false);
	}
	public void choice4(View v){
		if (correctAnswer == 4)
			rightAnswer();
		else
			wrongAnswer();
		firstTry = false;
		changeButtonColors(false);
	}
	
	
	public void continueGame(View v){
		newWord();
	}
	
	//when the correct answer has been selected, increment score
	public void rightAnswer(){
		if (firstTry){
			score++;
			streak++;
		}

		TextView tv = (TextView) this.findViewById(R.id.score);
		tv.setText("Score: " + Integer.toString(score));
		
		TextView tv2 = (TextView) this.findViewById(R.id.streak);
		tv2.setText("Streak: " + Integer.toString(streak));
		
		View b = findViewById(R.id.button5);
		b.setVisibility(View.VISIBLE);
	}
	
	//incorrect answer, load new word
	public void wrongAnswer(){
		if (firstTry){
			streak = 0;
		}
		
		TextView tv = (TextView) this.findViewById(R.id.streak);
		tv.setText("Streak: " + Integer.toString(streak));
		
		View b = findViewById(R.id.button5);
		b.setVisibility(View.VISIBLE);
	}
	
	//change button colors for right/wrong
	//button design: http://ogrelab.ikratko.com/custom-color-buttons-for-android/
	public void changeButtonColors(Boolean newWord){
		Button b1 = (Button)findViewById(R.id.button1);
		Button b2 = (Button)findViewById(R.id.button2);
		Button b3 = (Button)findViewById(R.id.button3);
		Button b4 = (Button)findViewById(R.id.button4);
		if (newWord) {
			b1.setBackgroundResource(R.drawable.custom_button_lblue);
			b2.setBackgroundResource(R.drawable.custom_button_lblue);
			b3.setBackgroundResource(R.drawable.custom_button_lblue);
			b4.setBackgroundResource(R.drawable.custom_button_lblue);
		}
		else{
			b1.setBackgroundResource(R.drawable.custom_button_red);
			b2.setBackgroundResource(R.drawable.custom_button_red);
			b3.setBackgroundResource(R.drawable.custom_button_red);
			b4.setBackgroundResource(R.drawable.custom_button_red);
			
			switch (correctAnswer){
				case 1: b1.setBackgroundResource(R.drawable.custom_button_green);
						break;
				case 2: b2.setBackgroundResource(R.drawable.custom_button_green);
						break;
				case 3: b3.setBackgroundResource(R.drawable.custom_button_green);
						break;
				case 4: b4.setBackgroundResource(R.drawable.custom_button_green);
						break;
			}
		}
		

	}
	
	//http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
	public static int randomInt(int min, int max){
		Random rand = new Random();
		int randomNum = rand.nextInt((max-min) + 1) + min;
		return randomNum;
	}
}
