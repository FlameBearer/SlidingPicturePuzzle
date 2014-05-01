package com.example.slidingpicturepuzzle;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.TableLayout;

public class GameBoard {
	private GamePiece mBoard[][];
	private boolean mIsComplete;
	private int mBoardMaxHeight;
	private int mBoardMaxWidth;
	private Point mSize;
	private Bitmap mBoardImage;
	
	public GameBoard(TableLayout board, Point dim, int size, Drawable img){
		mIsComplete = false;
		mBoardMaxHeight = dim.y;
		mBoardMaxWidth = dim.x;
		mBoardImage = ((BitmapDrawable) img).getBitmap();
		
		setBoardDim(board);
		
		
	}
	
	private void setBoardDim(TableLayout board){
		
		int imgHeight = mBoardImage.getHeight();
		int imgWidth = mBoardImage.getWidth();
		int scaleWidth, scaleHeight;
		Log.d("CMD", "H: " + mBoardMaxHeight + " W: " + mBoardMaxHeight);
		if(imgHeight > imgWidth){
			scaleHeight = mBoardMaxHeight;
			
			if(imgHeight > scaleHeight){
				scaleWidth = imgWidth - (imgHeight - scaleHeight);
			}
			else{
				scaleWidth = imgWidth + (scaleHeight - imgHeight);
			}
			
			if(scaleWidth > mBoardMaxWidth){
				scaleHeight = scaleHeight - (scaleWidth - mBoardMaxWidth);
				scaleWidth = mBoardMaxWidth;
				
			}
			
		}
		else if(imgWidth > imgHeight){
			scaleWidth = mBoardMaxWidth;
			
			if(imgWidth > scaleWidth){
				scaleHeight = imgHeight - (imgWidth - scaleWidth);
			}
			else{
				scaleHeight = imgHeight + (scaleWidth - imgWidth);
			}
			
			if(scaleHeight > mBoardMaxHeight){
				scaleWidth = scaleWidth - (scaleHeight - mBoardMaxHeight);
				scaleHeight = mBoardMaxHeight;
				
			}
			
			
		}
		else{
			
			if(mBoardMaxHeight < mBoardMaxWidth){
				scaleHeight = mBoardMaxHeight;
				scaleWidth = mBoardMaxHeight;
			}
			
			else{
				scaleHeight = mBoardMaxWidth;
				scaleWidth = mBoardMaxWidth;
			}
			
		}
	
		Log.d("CMD", "ScaleWidth: " + scaleWidth + " ScaleHeight: " + scaleHeight);
		mSize = new Point(scaleWidth, scaleHeight);

		
	}

}
