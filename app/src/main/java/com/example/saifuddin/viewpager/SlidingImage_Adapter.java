package com.example.saifuddin.viewpager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by saifuddin on five/9/19.
 */

public class SlidingImage_Adapter extends PagerAdapter {
    private final String TAG = SlidingImage_Adapter.class.getSimpleName();
    private DownLoadImageTask downLoadImageTask;
    private ArrayList<HashMap<String, String>> hashMaps;

    private LayoutInflater inflater;
    private Context context;

    public SlidingImage_Adapter(Context context, ArrayList<HashMap<String, String>> hashMaps) {
        this.context = context;
        this.hashMaps = hashMaps;
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

        final TextView tvEmail, tvMob, tvName, tvAddress, tvGender;
        final ImageView ivData;

        assert imageLayout != null;
        tvEmail = imageLayout.findViewById(R.id.tvEmail);
        tvEmail.setText(hashMaps.get(position).get("email"));
        tvMob = imageLayout.findViewById(R.id.tvMob);
        tvMob.setText(hashMaps.get(position).get("mobile"));
        tvName = imageLayout.findViewById(R.id.tvName);
        tvName.setText(hashMaps.get(position).get("name"));
        tvAddress = imageLayout.findViewById(R.id.tvAddress);
        tvAddress.setText(hashMaps.get(position).get("address"));
        tvGender = imageLayout.findViewById(R.id.tvGender);
        tvGender.setText(hashMaps.get(position).get("gender"));
        container.addView(imageLayout, 0);
        ivData = imageLayout.findViewById(R.id.ivData);
        downLoadImageTask = new DownLoadImageTask(ivData);
        downLoadImageTask.execute(hashMaps.get(position).get("image"));
        return imageLayout;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    private class DownLoadImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageView imageView;

        public DownLoadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try {
                InputStream is = new URL(urlOfImage).openStream();
                logo = BitmapFactory.decodeStream(is);
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e(TAG, "DownLoadImageTask" + ex);
            }
            return logo;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}
