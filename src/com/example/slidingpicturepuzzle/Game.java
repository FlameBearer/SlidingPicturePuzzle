package com.example.slidingpicturepuzzle;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Game extends Activity {
	private RelativeLayout mGameLayout;
	private GameBoard mBoard;
	private Drawable mImage;
	private TableLayout mTable;
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
				img.setOnTouchListener(new MyTouchListener());
				
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
			}
		}
		
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

}
