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
	private int mBoardSize;
	private float mImageRatio;
	private Point mBoardDim;
	private Point mPieceSize;
	private Bitmap mBoardImage;
	private Context mContext;

	
	public GameBoard(int size){
		mBoardSize = size;
//		mIsComplete = false;
//		mBoardMaxHeight = dim.y;
//		mBoardMaxWidth = dim.x;
//		mBoardImage = ((BitmapDrawable) img).getBitmap();
//		mBoardSize = size;
//		mBoard = null;
//		mContext = context;
//		setBoardDim();
//		if(mBoardDim.x > 0 && mBoardDim.y > 0){
//			mPieceSize.x = mBoardDim.x / size;
//			mPieceSize.y = mBoardDim.y / size;
//			mBoard = new GamePiece[mBoardSize][mBoardSize];		
//			createGameBoard();
//		}
		
		
	}
	
	public void setPieceSize(int width, int height){
		mPieceSize.x = width;
		mPieceSize.y = height;
	}
	
	public GamePiece getPieceAt(int row, int col){
		return mBoard[row][col];
	}
	
	public int getSize(){
		return mBoardSize;
	}
	
	public void setImageRatio(Bitmap img){
		mImageRatio = (float) img.getWidth() / img.getHeight();
	}
	
	public float getImageRatio(){
		return mImageRatio;
	}
	
	public boolean isBoardCreated(){
		if(mBoard == null)
			return false;
		else
			return true;
	}
	private void createPiece(GamePiece piece) {
		
		Drawable image;
		mBoardImage = Bitmap.createScaledBitmap(mBoardImage, mBoardDim.x, mBoardDim.y, false);
		Bitmap img;
		img = Bitmap.createBitmap(mBoardImage, mPieceSize.x * piece.getRow(), mPieceSize.y * piece.getCol(), mPieceSize.x, mPieceSize.y);
		image = new BitmapDrawable(mContext.getResources(), img);
		piece.setImage(image);
		
	}
	
	private void createGameBoard(){
		for(int i = 0; i < mBoardSize; i++){
			for(int j = 0; j < mBoardSize; j++){
				mBoard[i][j] = new GamePiece((i*mBoardSize) + j);
				mBoard[i][j].setPosition(i,j);
				createPiece(mBoard[i][j]);
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
		
		mBoardDim = new Point(scaleWidth, scaleHeight);

		
	}
	
//	private void setTable(int size){
//		
//		int width = mSize.x / size;
//		int height = mSize.y / size;
//		LayoutParams p = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//		p.setMargins(1, 1, 1, 1);
//		
//		for(int i = 0; i < size; i++){
//			
//			TableRow tr = new TableRow(mContext);
//			
//			for(int j = 0; j < size; j++){
//				
//				ImageView cell = new ImageView(mContext);
//				cell.setImageBitmap(mBoard[j][i].getImage());
//				cell.setLayoutParams(p);
//				tr.addView(cell, width, height);
//				
//			}
//			
//			mTable.addView(tr);
//		}
//	}

}




