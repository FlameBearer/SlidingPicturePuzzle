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
	private Point mPieceSize;
	private Bitmap mBoardImage;
	private Context mContext;

	
	public GameBoard(int size){
		mBoardSize = size;
		mBoard = new GamePiece[mBoardSize][mBoardSize];
		
	}
	
	public void setImage(Drawable drawable){
		mBoardImage = ((BitmapDrawable) drawable).getBitmap();
		setImageRatio();
	}
	
	public void setPieceSize(int width, int height){
		mPieceSize = new Point(width, height);
	}
	
	public GamePiece getPieceAt(int row, int col){
		return mBoard[row][col];
	}
	
	public int getSize(){
		return mBoardSize;
	}
	
	public void setImageRatio(){
		mImageRatio = (float) mBoardImage.getWidth() / mBoardImage.getHeight();
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
	
	public void loadImage(Context context){
		mContext = context;
		createGameBoard();
	}
	private void createPiece(GamePiece piece) {
		
		Drawable image;
		if(mBoardImage == null)
			Log.e("CMD", "Bitmap not created yet");
		mBoardImage = Bitmap.createScaledBitmap(mBoardImage, mPieceSize.x * mBoardSize, mPieceSize.y * mBoardSize, false);
		Bitmap img;
		img = Bitmap.createBitmap(mBoardImage, mPieceSize.x * piece.getCol(), mPieceSize.y * piece.getRow(), mPieceSize.x, mPieceSize.y);
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
		

}




