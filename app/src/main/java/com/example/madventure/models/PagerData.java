package com.example.madventure.models;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madventure.R;
import com.example.madventure.controllers.PagerAdapter;

public class PagerData {
    private Context context;

    public PagerData(Context context) {
        this.context=context;
    }

    public int pageImages[] = {
            R.drawable.page_image1,
            R.drawable.page_image2,
            R.drawable.page_image3
    };

    public int pageTitles[] = {
            R.string.onboarding_page_title1,
            R.string.onboarding_page_title2,
            R.string.onboarding_page_title3
    };


    public int pageTexts[] = {
            R.string.onboarding_page_text1,
            R.string.onboarding_page_text2,
            R.string.onboarding_page_text3
    };


}
