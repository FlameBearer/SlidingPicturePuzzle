package com.example.slidingpicturepuzzle;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class GameBoard {
	private GamePiece mBoard[][];
	private boolean mIsComplete;
	private int mBoardMaxHeight;
	private int mBoardMaxWidth;
	private Point mSize;
	private Bitmap mBoardImage;
	private Context mContext;
	private TableLayout mTable;
	
	public GameBoard(TableLayout board, Point dim, int size, Drawable img){
		mIsComplete = false;
		mBoardMaxHeight = dim.y;
		mBoardMaxWidth = dim.x;
		mBoardImage = ((BitmapDrawable) img).getBitmap();
		mContext = board.getContext();
		mTable = board;
		mBoard = new GamePiece[size][size];
		
		setBoardDim();
		if(mSize.x > 0 && mSize.y > 0){
			createImages(size);
			setTable(size);
		}
		
		
	}
	
	private void createImages(int size) {
		if(mSize.x > 0 && mSize.y > 0){
			mBoardImage = Bitmap.createScaledBitmap(mBoardImage, mSize.x, mSize.y, false);
			int pieceWidth = mSize.x / size;
			int pieceHeight = mSize.y / size;
			Bitmap img;
			for(int i = 0; i < size; i++){
				for(int j = 0; j < size; j++){
					img = Bitmap.createBitmap(mBoardImage, pieceWidth * i, pieceHeight * j, pieceWidth, pieceHeight);
					mBoard[i][j] = new GamePiece(img, 0);
					
				}
			}
		}
		
	}

	private void setBoardDim(){
		
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
	
	private void setTable(int size){
		
		int width = mSize.x / size;
		int height = mSize.y / size;
		LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		p.setMargins(1, 1, 1, 1);
		
		for(int i = 0; i < size; i++){
			
			TableRow tr = new TableRow(mContext);
			
			for(int j = 0; j < size; j++){
				
				ImageView cell = new ImageView(mContext);
				cell.setImageBitmap(mBoard[j][i].getImage());
				cell.setLayoutParams(p);
				tr.addView(cell, width, height);
				
			}
			
			mTable.addView(tr);
		}
	}

}




