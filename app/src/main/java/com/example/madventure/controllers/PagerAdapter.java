package com.example.madventure.controllers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.madventure.R;
import com.example.madventure.models.PagerData;

import java.util.zip.Inflater;

public class PagerAdapter extends androidx.viewpager.widget.PagerAdapter {
    Context context;
    PagerData pgData;

    public PagerAdapter(Context context) {
        this.context = context;
        pgData = new PagerData(context);
    }

    @Override
    public int getCount() {
        return pgData.pageImages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.one_page, container,
                false);

        ImageView pageImage = (ImageView) view.findViewById(R.id.pageImage);
        TextView pageTitle = (TextView) view.findViewById(R.id.pageTitle);
        TextView pageText = (TextView) view.findViewById(R.id.pageText);

        pageImage.setImageDrawable(context.getDrawable(pgData.pageImages[position]));
        pageTitle.setText(context.getString(pgData.pageTitles[position]));
        pageText.setText(context.getString(pgData.pageTexts[position]));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((androidx.constraintlayout.widget.ConstraintLayout) object);
    }

}
