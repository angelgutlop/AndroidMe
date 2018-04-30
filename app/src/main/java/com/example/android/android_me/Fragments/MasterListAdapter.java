package com.example.android.android_me.Fragments;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

public class MasterListAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> bodyPartsList = AndroidImageAssets.getAll();


    MasterListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return bodyPartsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {

            imageView = new ImageView(context);
            imageView.setAdjustViewBounds(true);
            //imageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(bodyPartsList.get(position));
        return imageView;
    }

}
