package com.example.slidingpicturepuzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.TableLayout;

public class GameBoard {
	private GamePiece mBoard[][];
	private boolean mIsComplete;
	private int mBoardMaxHeight;
	private int mBoardMaxWidth;
	private Bitmap mBoardImage;
	
	public GameBoard(TableLayout board,int size, Drawable img){
		mIsComplete = false;
		setBoardDim(board, img);
		
		
	}
	
	private void setBoardDim(TableLayout board, Drawable img){
		mBoardMaxHeight = board.getHeight();
		mBoardMaxWidth = board.getWidth();
		mBoardImage = ((BitmapDrawable) img).getBitmap();
		int imgHeight = mBoardImage.getHeight();
		int imgWidth = mBoardImage.getWidth();
		if(imgHeight > imgWidth){
			
		}
		else if(imgHeight < imgWidth){
			
		}
		else{
			
		}
	}

}
