package com.example.slidingpicturepuzzle;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;
import android.widget.TableLayout;

public class Game extends Activity {
	private TableLayout mGameBoard;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_layout);
        
        mGameBoard = (TableLayout) this.findViewById(R.id.gameBoard);
        Resources res = getResources();
        
        TypedArray icons = res.obtainTypedArray(R.array.default_image_options);
        Drawable [] images = new Drawable[icons.length()];
        
        for(int i = 0; i < icons.length(); i++)
        	images[i] = icons.getDrawable(i);
        
        icons.recycle();
        GameBoard board = new GameBoard(mGameBoard, 3, images[1]);
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
