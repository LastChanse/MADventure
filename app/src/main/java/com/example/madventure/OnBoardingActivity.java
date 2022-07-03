package com.example.madventure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.madventure.controllers.PagerAdapter;

public class OnBoardingActivity extends AppCompatActivity {

    ViewPager pager;
    PagerAdapter pagerAdapter = new PagerAdapter(this);
    ImageView point1;
    ImageView point2;
    ImageView point3;
    Button skip;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        point1 = (ImageView) findViewById(R.id.point1);
        point2 = (ImageView) findViewById(R.id.point2);
        point3 = (ImageView) findViewById(R.id.point3);

        skip = (Button) findViewById(R.id.btnSkip);
        next = (Button) findViewById(R.id.btnNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(view, 0);
            }
        });

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    point1.setImageDrawable(getDrawable(R.drawable.point_selected));
                    point2.setImageDrawable(getDrawable(R.drawable.point));
                    point3.setImageDrawable(getDrawable(R.drawable.point));
                    skip.setVisibility(View.VISIBLE);
                    skip.setClickable(true);
                    next.setText("Next");
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            next(view, position);
                        }
                    });
                } else if (position == 1) {
                    point1.setImageDrawable(getDrawable(R.drawable.point));
                    point2.setImageDrawable(getDrawable(R.drawable.point_selected));
                    point3.setImageDrawable(getDrawable(R.drawable.point));
                    skip.setVisibility(View.VISIBLE);
                    skip.setClickable(true);
                    next.setText("Next");
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            next(view, position);
                        }
                    });
                } else if (position == 2) {
                    point1.setImageDrawable(getDrawable(R.drawable.point));
                    point2.setImageDrawable(getDrawable(R.drawable.point));
                    point3.setImageDrawable(getDrawable(R.drawable.point_selected));
                    skip.setVisibility(View.INVISIBLE);
                    skip.setClickable(false);
                    next.setText("Done");
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            skip(view);
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void skip(View view) {
        startActivity(new Intent(this, AuthorizationActivity.class));
    }

    public void next(View view, int position) {
        pager.setCurrentItem(position+1);
    }
}