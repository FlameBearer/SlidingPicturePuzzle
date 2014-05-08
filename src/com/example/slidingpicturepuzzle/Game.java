package com.example.slidingpicturepuzzle;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Game extends Activity {
	private RelativeLayout mGameLayout;
	private LinearLayout mMoveCountLayout;
	private GameBoard mBoard;
	private Drawable mImage;
	private TableLayout mTable;
	private int mMoveCount = 0;
	private TextView mCounter;
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
        
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		int position = extras.getInt(MainActivity.SELECTED_IMAGE_KEY);
		Log.d("CMD", "Position: " + position);
        mImage = images[position];
        
        newGame(EASY);
        
//        initCount();
//        initGame();
//        initBoard();
//        
//        mGameLayout.addView(mMoveCountLayout);
//        mGameLayout.addView(mTable);
//        mGameLayout.invalidate();
        //start();
   
    }
	
	protected void initCount(){
		mMoveCountLayout = new LinearLayout(this);
		RelativeLayout.LayoutParams countParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		countParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
		mMoveCountLayout.setLayoutParams(countParams);
		mMoveCountLayout.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams textParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		TextView text = new TextView(this);
		text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
		text.setText(R.string.moveCountString);
		text.setLayoutParams(textParams);
		mCounter = new TextView(this);
		mCounter.setLayoutParams(textParams);
		mCounter.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
		mCounter.setText(R.string.moveCount);
		
		mMoveCountLayout.addView(text);
		mMoveCountLayout.addView(mCounter);
		//mMoveCountLayout.setlayou
		
	}
	
	private void start(){
		initCount();
        initGame();
        initBoard();
        
        mGameLayout.addView(mMoveCountLayout);
        mGameLayout.addView(mTable);
        mGameLayout.invalidate();
	}
	
	private void updateCounter(){
		mCounter.setText(String.valueOf(mMoveCount));
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
		if(mBoard == null){
			mBoard = new GameBoard(difficutly);
			mBoard.setImage(mImage);
			start();
		}
		else{
			mLastWidth = mLastHeight = -1;
			mBoard = new GameBoard(difficutly);
			mBoard.setImage(mImage);
			start();
			if(mTable == null)
				Log.d("CMD", "Error table not created");
			
		}
		
		
	}
	
	//Implement hopefully
	//private final class MyTouchListener implements OnTouchListener {
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
		mBlankRow = boardSize - 1;
		mBlankCol = boardSize - 1;
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
		
		Log.e("SIZE", " " + boardSize);
		
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
					img.setBackgroundResource(R.color.image);
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
		    	//Log.d("Blank", "Row: " + mBlankRow + "Column: " + mBlankCol);
				//Log.d("Piece", "Row: " + piece.getRow() + "Column: " + piece.getCol());
	    	}
	    	if(mBoard.isSolved())
	    		Log.d("CMD", "Solved!!!");
	    	else
	    		mBoard.print();
	    	
	     }          
	    
	}
	
	private boolean checkMobility(GamePiece piece){
		int row = piece.getRow();
		int col = piece.getCol();
		//Log.d("Blank", "Row: " + mBlankRow + "Column: " + mBlankCol);
		//Log.d("Piece", "Row: " + row + "Column: " + col);
		if((mBlankRow == row) && (mBlankCol == col)){
			//Log.e("CMD", "Enter no move");
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
		for(int i = 0; i < 10000; i++){
			row = (int) (Math.random() * (boardSize));
			col = (int) (Math.random() * (boardSize));
			//Log.d("Shuffle", "Row: " + row + "Col: " + col);
			if(!(mBlankRow == row && mBlankCol == col)){
				ImageView img = mCells[row][col];
				//GamePiece move = mBoard.getPieceAt(row, col);
				GamePiece move = (GamePiece) img.getTag();
				if(move == null)
					Log.e("move", "Move is null");
				else if(checkMobility(move))
					swapPieces(move);
			}
			
		}
		
		mMoveCount = 0;
		updateCounter();
		//mBoard.print();
	}
	
	private void swapPieces(GamePiece piece){
		int row = piece.getRow();
		int col = piece.getCol();

		ImageView one = mCells[piece.getRow()][piece.getCol()];
		ImageView two = mCells[mBlankRow][mBlankCol];	
		
		GamePiece blank = (GamePiece) two.getTag();
		
		GamePiece num1 = mBoard.getPieceAt(row, col);
		GamePiece num2 = mBoard.getPieceAt(mBlankRow, mBlankCol);

		mBoard.setPiece(num1, mBlankRow, mBlankCol);
		mBoard.setPiece(num2, row, col);
		
		assignPiece(one, blank);
		assignPiece(two, piece);
		one.setOnClickListener(null);
		one.setBackgroundResource(R.color.table);
		two.setOnClickListener(new pieceClick());
		two.setBackgroundResource(R.color.image);
		piece.setPosition(mBlankRow, mBlankCol);		
		mBlankRow = row;
		mBlankCol = col;
		
		mMoveCount++;
		updateCounter();
		
		
	}
	
	private void assignPiece(ImageView img, GamePiece piece){
		img.setImageDrawable(piece.getImage());
		img.setTag(piece);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	
    	switch(item.getItemId()){
    	
    	case R.id.easy_setting:
    		//restart for easy size
    		if(mBoard.getSize() != EASY)
    			newGame(EASY);
    		break;
    	
    	case R.id.medium_settings:
    		//restart for medium size
    		if(mBoard.getSize() != MEDIUM)
    			newGame(MEDIUM);
    		break;
    		
    	case R.id.hard_settings:
    		//restart for hard size
    		if(mBoard.getSize() != HARD)
    			newGame(HARD);
    		break;
    		
    	case R.id.shuffle_settings:
    		//re-shuffle the board
    		shuffle();
    		break;
    	}
		return true;
    	
    }
    
    

}












