package com.example.dells.materialtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DogActivity extends AppCompatActivity {
    public static final String DOG_NAME = "dog_name";
    public static final String DOG_IMAGE_ID = "dog_image_id";
    private Toolbar mToolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView dogImageView;
    private TextView dogTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);
        Intent intent = getIntent();
        String dogName = intent.getStringExtra(DOG_NAME);
        int dogImageId = intent.getIntExtra(DOG_IMAGE_ID, 0);
        mToolbar = (Toolbar) findViewById(R.id.dog_toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        dogImageView = (ImageView) findViewById(R.id.dog_image_view);
        dogTextView = (TextView) findViewById(R.id.dog_content_text);

        setSupportActionBar(mToolbar);
        collapsingToolbarLayout.setTitle(dogName);
        Glide.with(this).load(dogImageId).into(dogImageView);
        String dogContent = generateDogContent (dogName);
        dogTextView.setText(dogContent);
    }

    private String generateDogContent(String dogName) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 500; i++){
            stringBuilder.append(dogName);
        }
        return stringBuilder.toString();
    }
}
