package com.example.slidingpicturepuzzle;

import android.graphics.drawable.Drawable;

public class GamePiece {
	private Drawable mImg;
	private final int mNumber;
	private int mMobility;
	public static final int NONE = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	
	public GamePiece(Drawable img, int number){
		mImg = img;
		mNumber = number;
		mMobility = NONE;
	}
	
	public int getNumber(){
		return mNumber;
	}

}
