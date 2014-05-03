package com.example.slidingpicturepuzzle;

import android.graphics.Bitmap;

public class GamePiece {
	private Bitmap mImg;
	private final int mNumber;
	private int mMobility;
	public static final int NONE = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	
	public GamePiece(Bitmap img, int number){
		mImg = img;
		mNumber = number;
		mMobility = NONE;
	}
	
	public int getNumber(){
		return mNumber;
	}
	public Bitmap getImage(){
		return mImg;
	}

}
