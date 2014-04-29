package com.example.slidingpicturepuzzle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class MyGridImageAdapter extends ArrayAdapter<Drawable>{
	
	public MyGridImageAdapter(Context context, Drawable[] images ){
		super(context, R.layout.gridimage, images);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		View view = super.getView(position, convertView, parent);
		ImageView imgView = (ImageView) view.findViewById(R.id.gridImage);
		
		final Drawable img = getItem(position);
		
		imgView.setImageDrawable(img);
		
		return view;
	}

}
