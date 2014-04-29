package com.example.slidingpicturepuzzle;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		
		return createViewFromResource(position, convertView, parent, mLayout);
		
	}
	
	private View createViewFromResource(int position, View convertView, ViewGroup parent, int resource) {
        
		View view;

		if (convertView == null) {
			view = mInflater.inflate(resource, parent, false);
		} 
        
		else {
			view = convertView;   
        }
        
		ImageView imgView = (ImageView) view.findViewById(R.id.gridImage);
		imgView.setImageDrawable(mImages[position]);
		imgView.setBackgroundColor(0xFFFF0000);
        return view;
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
		return 0;
	}

}
