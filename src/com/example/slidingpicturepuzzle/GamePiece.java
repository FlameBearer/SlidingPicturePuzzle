package com.example.slidingpicturepuzzle;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class GamePiece {
	private Drawable mImg;
	private int mNumber;
	private int [] mPosition;
	private int mMobility;
	public static final int NONE = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	
	public GamePiece(int number){
		mImg = null;
		mNumber = number;
		mPosition = new int[2];
		setPosition(-1, -1);
		setMobility(NONE);
	}
	
	public int getNumber(){
		return mNumber;
	}
	
	public Drawable getImage(){
		return mImg;
	}
	
	public void setImage(Drawable img){
		mImg = img;
	}
	
	public int[] getPosition(){
		return mPosition;
	}
	
	public int getRow(){
		return mPosition[0];
	}
	
	public int getCol(){
		return mPosition[1];
	}
	
	public void setPosition(int row, int col){
		mPosition[0] = row;
		mPosition[1] = col;
	}
	
	public int getMobility(){
		return mMobility;
	}

	public void setMobility(int direction){
		mMobility = direction;
	}
}
