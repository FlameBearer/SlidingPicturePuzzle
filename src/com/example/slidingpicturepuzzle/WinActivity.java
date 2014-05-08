package com.example.slidingpicturepuzzle;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class WinActivity extends Activity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        
        TextView count = (TextView) this.findViewById(R.id.moveCount);
        ImageView image = (ImageView) this.findViewById(R.id.winImage);
        
        Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		int moveCount = extras.getInt(Game.MOVE_COUNT_KEY);
		
		String text = "Move Count: " + moveCount;
		count.setText(text);
		int index = extras.getInt(Game.WINNING_IMAGE_KEY);
		
		Resources res = getResources();
        
        TypedArray icons = res.obtainTypedArray(R.array.default_image_options);
        Drawable [] images = new Drawable[icons.length()];
        
        for(int i = 0; i < icons.length(); i++)
        	images[i] = icons.getDrawable(i);
        
        icons.recycle();
        
        image.setImageDrawable(images[index]);
	}

}
