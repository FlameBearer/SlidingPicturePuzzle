package com.example.slidingpicturepuzzle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WinActivity extends Activity {
	private Context mContext;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        
        mContext = this;
        
        TextView count = (TextView) this.findViewById(R.id.moveCount);
        ImageView image = (ImageView) this.findViewById(R.id.winImage);
        Button button = (Button) this.findViewById(R.id.backButton);
        
        button.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View view){
        		Intent intent = new Intent(mContext, MainActivity.class);
        	    startActivity(intent);
        	}
        });
        
        Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		int moveCount = extras.getInt(Game.MOVE_COUNT_KEY);
		
		String text = "Total Moves: " + moveCount;
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
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
