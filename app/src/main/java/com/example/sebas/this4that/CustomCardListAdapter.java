package com.example.sebas.this4that;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

/**
 * Created by sebas on 4/25/2017.
 */

public class CustomCardListAdapter extends ArrayAdapter<Cards> {


    private Context mContext;
    private int mResource;
    private ArrayList<Cards> cards;
    private int lastPosition = -1;
    private final static String TAG = "CustomCardListAdapter";

/**
 * Holds variables in a View
 */
    private static class ViewHolder {
        TextView title;
        ImageView image;
    }

    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public CustomCardListAdapter(Context context, int resource, ArrayList<Cards> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        cards = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information

        setupImageLoader();
        String title = getItem(position).getCardTitle();
        String imgUrl = getItem(position).getCardImageUrl();

        //create the view result for showing the animation

      try {

          final View result;

          //ViewHolder object
          ViewHolder holder;


          if (convertView == null) {
              LayoutInflater inflater = LayoutInflater.from(mContext);
              convertView = inflater.inflate(mResource, parent, false);
              holder = new ViewHolder();
              holder.title = (TextView) convertView.findViewById(R.id.cardText);
              holder.image = (ImageView) convertView.findViewById(R.id.cardImage);

              result = convertView;

              convertView.setTag(holder);
          } else {
           //   Toast.makeText(this.getContext(), ":( NNOOOOO", Toast.LENGTH_LONG).show();
              holder = (ViewHolder) convertView.getTag();
              result = convertView;
          }


          Animation animation = AnimationUtils.loadAnimation(mContext,
                  (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
          result.startAnimation(animation);
          lastPosition = position;

          holder.title.setText(title);

          ImageLoader imageLoader = ImageLoader.getInstance();

          int defaultImage = mContext.getResources().getIdentifier("@drawable/image_failed", null, mContext.getPackageName());

          //create display options
          DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                  .cacheOnDisc(true).resetViewBeforeLoading(true)
                  .showImageForEmptyUri(defaultImage)
                  .showImageOnFail(defaultImage)
                  .showImageOnLoading(defaultImage).build();

          //download and display image from url
          imageLoader.displayImage(imgUrl, holder.image, options);

          if(convertView == null){
              Toast.makeText(this.getContext(), "this is sad", Toast.LENGTH_LONG).show();
          }
          return convertView;
      }catch (IllegalArgumentException e){
          Log.e(TAG, "getView: IllegalArgumentException: " + e.getMessage());
          return convertView;
      }

    }

    private void setupImageLoader(){
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }
}

