package com.example.saifuddin.viewpager;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by saifuddin on five/9/19.
 */

public class SlidingImage_Adapter extends PagerAdapter {

    private ArrayList<HashMap<String, String>> hashMaps;

    private LayoutInflater inflater;
    private Context context;

    public SlidingImage_Adapter(Context context, ArrayList<HashMap<String, String>> IMAGES) {
        this.context = context;
        this.hashMaps =IMAGES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return hashMaps.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View imageLayout = inflater.inflate(R.layout.slidingimages_layout, container, false);

        assert imageLayout != null;
        final TextView tvEmail = imageLayout.findViewById(R.id.tvEmail);
        tvEmail.setText(hashMaps.get(position).get("email"));
        final TextView tvMob = imageLayout.findViewById(R.id.tvMob);
        tvMob.setText(hashMaps.get(position).get("mobile"));
        final TextView tvName = imageLayout.findViewById(R.id.tvName);
        tvName.setText(hashMaps.get(position).get("name"));
//        imageView.setImageResource(hashMaps.get(position));
        container.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
