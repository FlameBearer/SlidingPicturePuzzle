package com.example.slidingpicturepuzzle;

import android.graphics.drawable.Drawable;

public class GameBoard {
	private GamePiece mBoard[][];
	private boolean mIsComplete;
	
	public GameBoard(int size, Drawable img){
		mIsComplete = false;
	}

}
