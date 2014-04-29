package com.example.slidingpicturepuzzle;

import android.graphics.drawable.Drawable;

public class GamePiece {
	private Drawable mImg;
	private final int mNumber;
	
	public GamePiece(Drawable img, int number){
		mImg = img;
		mNumber = number; 
	}
	
	public int getNumber(){
		return mNumber;
	}

}
