package com.example.slidingpicturepuzzle;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Game extends Activity {
	private RelativeLayout mGameLayout;
	private GameBoard mBoard;
	private Drawable mImage;
	private TableLayout mTable;
	private int mBlankRow;
	private int mBlankCol;
	private int mLastWidth = -1, mLastHeight = -1;
	private ImageView[][] mCells;
	public static final int EASY = 3;
	public static final int MEDIUM = 4;
	public static final int HARD = 5;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
             
        Resources res = getResources();
        
        TypedArray icons = res.obtainTypedArray(R.array.default_image_options);
        Drawable [] images = new Drawable[icons.length()];
        
        for(int i = 0; i < icons.length(); i++)
        	images[i] = icons.getDrawable(i);
        
        icons.recycle();
        mImage = images[1];
        newGame(3);
        mBoard.setImage(mImage);
        
        initGame();
        initBoard();
        mBlankRow = 2;
        mBlankCol = 2;
        mGameLayout.addView(mTable);
        mGameLayout.invalidate();
   
    }
	
	protected void initGame(){
		mGameLayout = new RelativeLayout(this){
		
			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
				int width = MeasureSpec.getSize(widthMeasureSpec);
				int height = MeasureSpec.getSize(heightMeasureSpec);
			
			
				if(width != mLastWidth || height != mLastHeight){
					//implement later
					if(0 < getChildCount())
						resizeContent(width, height);
					mLastWidth = width;
					mLastHeight = height;
				}
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			}
		};
		
		//mGameLayout.setBackgroundResource(R.color.background);
		
		setContentView(mGameLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
	}
	
	protected void newGame(int difficutly){
		mBoard = new GameBoard(difficutly);
	}
	
	public void onClick(View view){
		final Object tag = view.getTag();
		ImageView imgView = (ImageView) view;
		imgView.setColorFilter(R.color.dimmer);
		
	}
	
	//Implement hopefully
//	private final class MyTouchListener implements OnTouchListener {
//
//	    	int prevX,prevY;
//
//	        @Override
//	        public boolean onTouch(final View v,final MotionEvent event){
//
//	        	//final TableRow.LayoutParams par = (TableRow.LayoutParams)v.getLayoutParams();
//	        	float downx, downy, upx, upy;
//	        	ImageView view = (ImageView) v;
//	        	
//	        	switch(event.getAction()){
//	        	
//	        		case MotionEvent.ACTION_MOVE:{
//  
//	        			upx = event.getX();
//	        			upy = event.getY();
//	        			
//	        			view.invalidate();
//	        			downx = upx;
//	        			downy = upy;
//
//	        		}
//	        		
//	        		case MotionEvent.ACTION_UP:{
//	        			
//	        			upx = event.getX();
//	        			upy = event.getY();
//	        			view.invalidate();
//	        			break;
//	        			
//	        		}
//	        		
//	        		case MotionEvent.ACTION_DOWN: {
//	        			
//	        			downx = event.getX();
//	        			downy = event.getY();
//	        			break;
//	        			
//	        		}
//	        	}
//	        	
//	        	return true;
//	        	
//	        }
//	    	
//	 }
	
	protected void initBoard(){
		mTable = new TableLayout(this);
		mTable.setBackgroundResource(R.color.table);
		final int boardSize = mBoard.getSize();
		mCells = new ImageView[boardSize][boardSize];
		
		for(int i = 0; i < boardSize; i++){
			TableRow row = new TableRow(this);
			for(int j = 0; j < boardSize; j++){
				ImageView img = new ImageView(this);
				mCells[i][j] = img;
				//img.setOnTouchListener(new MyTouchListener());
				
				
				row.addView(img);
			}
			mTable.addView(row);
		}
	}

	//resizes content for spacing between cells
	protected void resizeContent(int screenWidth, int screenHeight){
		
		float imageRatio = mBoard.getImageRatio();
		int boardSize = mBoard.getSize();
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		int borderWidth = (int) Math.ceil(metrics.density);
		int spacing = borderWidth * 2 * boardSize;
		float newRatio = (float) (screenWidth - spacing) / (screenHeight -  spacing);
		
		int width, height;
		if(newRatio > imageRatio){
			//scale to screen height
			height = screenHeight;
			width = (int) (imageRatio * height);
			
		}
		else {
			//scale to screen height
			width = screenWidth;
			height = (int) (width / imageRatio);
		}
		
		height -= height % boardSize;
		width -= width % boardSize;
		
		RelativeLayout.LayoutParams boardParams = new RelativeLayout.LayoutParams(width, height);
		boardParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		mTable.setLayoutParams(boardParams);
		
		//load and resize the image
		width = width / boardSize - 2 * borderWidth;
		height = height / boardSize - 2 * borderWidth;
		mBoard.setPieceSize(width, height);
		//load image from intent?
		mBoard.loadImage(this);
		
		TableRow.LayoutParams cellParams = new TableRow.LayoutParams(width, height);
		cellParams.setMargins(borderWidth, borderWidth, borderWidth, borderWidth);
		
		for(int i = 0; i < boardSize; i++){
			for(int j = 0; j < boardSize; j++){
				ImageView img = mCells[i][j];
				img.setLayoutParams(cellParams);
				GamePiece piece = mBoard.getPieceAt(i, j);
				img.setImageDrawable(piece.getImage());
				img.setTag(piece);
				if(i != (boardSize - 1) || j != (boardSize - 1)){
					img.setOnClickListener(new pieceClick());
				}
				else
					img.setOnClickListener(null);
			}
		}
		shuffle();
	}

	private final class pieceClick implements View.OnClickListener {
	    @Override           
	    public void onClick(View view) {
	    	final Object tag = view.getTag();
	    	GamePiece piece = (GamePiece) tag;
	    	
	    	if(checkMobility(piece)){
	    		swapPieces(piece);
		    	Log.d("Blank", "Row: " + mBlankRow + "Column: " + mBlankCol);
				Log.d("Piece", "Row: " + piece.getRow() + "Column: " + piece.getCol());
	    	}
	    	
	     }          
	    
	}
	
	private boolean checkMobility(GamePiece piece){
		int row = piece.getRow();
		int col = piece.getCol();
		Log.d("Blank", "Row: " + mBlankRow + "Column: " + mBlankCol);
		Log.d("Piece", "Row: " + row + "Column: " + col);
		if((mBlankRow == row) && (mBlankCol == col)){
			Log.e("CMD", "Enter no move");
			return false;	
		}
		else if(((row - mBlankRow) == 0) && (Math.abs(col - mBlankCol) == 1))
			return true;
		else if(((col - mBlankCol) == 0) && (Math.abs(row - mBlankRow) == 1))
			return true;
		else
			return false;
	}
	
	private void shuffle(){
		int boardSize = mBoard.getSize();
		int row, col;
		for(int i = 0; i < 1000; i++){
			row = (int) (Math.random() * (boardSize));
			col = (int) (Math.random() * (boardSize));
			Log.d("Shuffle", "Row: " + row + "Col: " + col);
			ImageView img = mCells[row][col];
			GamePiece move = mBoard.getPieceAt(row, col);
			if(move == null)
				Log.e("move", "Move is null");
			else if(checkMobility(move))
				swapPieces(move);
			
		}
	}
	
	private void swapPieces(GamePiece piece){
		int row = piece.getRow();
		int col = piece.getCol();

		ImageView one = mCells[piece.getRow()][piece.getCol()];
		ImageView two = mCells[mBlankRow][mBlankCol];	
		
		GamePiece blank = (GamePiece) two.getTag();
		
//		GamePiece blank = new GamePiece((mBlankRow * mBlankCol) + mBlankCol);
//		blank.setImage(null);
//		blank.setPosition(mBlankRow, mBlankCol);		
		//GamePiece blank = mBoard.getPieceAt(mBlankRow, mBlankCol);		
		assignPiece(one, blank);
		assignPiece(two, piece);
		one.setOnClickListener(null);
		two.setOnClickListener(new pieceClick());
		piece.setPosition(mBlankRow, mBlankCol);		
		mBlankRow = row;
		mBlankCol = col;
		
		
	}
	
	private void assignPiece(ImageView img, GamePiece piece){
		img.setImageDrawable(piece.getImage());
		img.setTag(piece);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    

}
