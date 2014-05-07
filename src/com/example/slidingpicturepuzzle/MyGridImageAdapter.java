package com.example.slidingpicturepuzzle;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyGridImageAdapter extends BaseAdapter{
	private Context mContext;
	private final Drawable[] mImages;
	private LayoutInflater mInflater;
	private int mLayout;
	
	public MyGridImageAdapter(Context context, Drawable[] images ){
		mContext = context;
		mImages = images;
		mLayout = R.layout.gridimage;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		
		return createViewFromResource(position, convertView, parent, mLayout);
		
	}
	
	private View createViewFromResource(final int position, View convertView, ViewGroup parent, int resource) {
        
		View view;

		if (convertView == null) {
			view = mInflater.inflate(resource, parent, false);
		} 
        
		else {
			view = convertView;   
        }
        
		ImageView imgView = (ImageView) view.findViewById(R.id.gridImage);
		imgView.setImageDrawable(mImages[position]);
		
		view.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View view){
        		launchGame(position);
        		ImageView img = (ImageView) view;
        		img.setColorFilter(R.color.dimmer);
        	}
        });
		
        return view;
    }
	
    private void launchGame(int position){
    	Intent intent = new Intent(mContext, Game.class);
    	intent.putExtra(MainActivity.SELECTED_IMAGE_KEY, position);
    	//startActivityForResult(intent, MainActivity.GAME_REQUEST);
    	mContext.startActivity(intent);
    }

	@Override
	public int getCount() {
		return mImages.length;
	}

	@Override
	public Object getItem(int arg0) {
		return mImages[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

}
