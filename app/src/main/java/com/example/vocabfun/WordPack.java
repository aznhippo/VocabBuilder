package com.example.vocabfun;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;

//a class that contains words, part of speech, definitions, and correct/misses for each word
public class WordPack {
	
	String packName;
	Context context;
	String[] words, partOfSpeech, definitions, sentences;
	int numWords;
	
	int[] nouns, adjectives, verbs, adverbs;
	
	int nIndex, adjIndex, vIndex, advIndex;
	
	int[] correct, missed;
	
	public WordPack(String newPack, Context appContext){
		packName = newPack;
		context = appContext;
	}
	
	//reads and parses a word pack into words, part of speech, and definitions.
	public void createWordPack(){
		AssetManager am = context.getAssets();
		try {
			InputStream is = am.open(packName);
			String myString = readFully(is, "UTF-8");
			String[] lines = myString.split("\\r?\\n");
			numWords = lines.length;

			//create array of words, part of speech and definitions
			words = new String[numWords];
			partOfSpeech = new String[numWords];
			definitions = new String[numWords];
			
			//put each component in corresponding array
			for (int i = 0; i < numWords; i++){
				int spaceIndex = lines[i].indexOf(' ');
				words[i] = lines[i].substring(0, spaceIndex);
				int periodIndex = lines[i].indexOf('.');
				partOfSpeech[i] = lines[i].substring(spaceIndex+1, periodIndex);
				definitions[i] = lines[i].substring(periodIndex+1,lines[i].length());
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//converts inputstream to string
	//http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
	public String readFully(InputStream inputStream, String encoding) throws IOException {
	    return new String(readFully(inputStream), encoding);
	}    

	private byte[] readFully(InputStream inputStream) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024];
	    int length = 0;
	    while ((length = inputStream.read(buffer)) != -1) {
	        baos.write(buffer, 0, length);
	    }
	    return baos.toByteArray();
	}

	//make arrays of indices in the 'words' array for each part of speech
	public void makePOSArrays(){
		nouns = new int[numWords];
		adjectives = new int[numWords];
		verbs = new int[numWords];
		adverbs = new int[numWords];
		
		nIndex = 0;
		adjIndex = 0;
		vIndex = 0;
		advIndex = 0;
		
		for (int i = 0; i < numWords; i++){
			if (partOfSpeech[i].equals("n")){
				nouns[nIndex] = i;
				nIndex++;
			}
			else if (partOfSpeech[i].equals("adj")){
				adjectives[adjIndex] = i;
				adjIndex++;
			}
			else if (partOfSpeech[i].equals("v")){
				verbs[vIndex] = i;
				vIndex++;
			}
			else if (partOfSpeech[i].equals("adv")){
				adverbs[advIndex] = i;
				advIndex++;
			}			
		}
	}

	
	
	public String getWord(int wordID){
		return words[wordID];
	}
	
	public String getPOS(int wordID){
		return partOfSpeech[wordID];
	}
	
	public String getDef(int wordID){
		return definitions[wordID];
	}
	
	
	
	
	
	
	
}
