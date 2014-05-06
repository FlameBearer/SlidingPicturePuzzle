package com.example.slidingpicturepuzzle;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.WindowManager;
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
	private ImageView[][] cells;
	public static final int EASY = 3;
	public static final int MEDIUM = 4;
	public static final int HARD = 5;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
 
        
   
        
//        //Log.d("CMD", "H: " + height + " W: " + width + " Top: " + getStatusBarHeight());
//        
//        mTable = (TableLayout) this.findViewById(R.id.gameBoard);
//        
//        Resources res = getResources();
//        
//        TypedArray icons = res.obtainTypedArray(R.array.default_image_options);
//        Drawable [] images = new Drawable[icons.length()];
//        
//        for(int i = 0; i < icons.length(); i++)
//        	images[i] = icons.getDrawable(i);
//        
//        icons.recycle();
//        mImage = images[0];
//        
//        contentView.post(new Runnable()
//        {
//            public void run()
//            {
//                int contentHeight = contentView.getHeight();
//                Log.d("Layout", "H: " + mGameBoard.getHeight() + " W" + mGameBoard.getWidth() + " C: "+ contentHeight);
//                Point dim = new Point(mGameBoard.getWidth(), contentHeight - mGameBoard.getHeight());
//               // GameBoard board = new GameBoard(mTable, dim, 3, mImage);
//                
//            }
//        });
        
        
        
    }
	
	protected void initGame(){
		mGameLayout = new RelativeLayout(this){
		
			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
				int width = MeasureSpec.getSize(widthMeasureSpec);
				int height = MeasureSpec.getSize(heightMeasureSpec);
			
			
				if(width != mLastWidth || height != mLastHeight){
					//implement later
					//if(0 < getChildCount())
						//resizeContent(width, height);
					mLastWidth = width;
					mLastHeight = height;
				}
				super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			}
		};
		setContentView(mGameLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
	}
	
	protected void newGame(int difficutly){
		mBoard = new GameBoard(difficutly);
	}
	
	protected void initBoard(){
		mTable = new TableLayout(this);
		final int boardSize = mBoard.getSize();
		cells = new ImageView[boardSize][boardSize];
		
		for(int i = 0; i < boardSize; i++){
			TableRow row = new TableRow(this);
			for(int j = 0; j < boardSize; j++){
				ImageView img = new ImageView(this);
				cells[i][j] = img;
				//img.setOnClickListener(this);
				row.addView(img);
			}
			mTable.addView(row);
		}
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public int getStatusBarHeight() {
    	  int result = 0;
    	  int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
    	  if (resourceId > 0) {
    	      result = getResources().getDimensionPixelSize(resourceId);
    	  }
    	  return result;
    	}

}
