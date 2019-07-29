package com.haiph.assignmentandroidnw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ImageDetailsActivity extends AppCompatActivity {

    private FloatingActionButton fab, fabShare, fabSave, fabLike, fabSetImage;
    boolean showbutton ;
    private ImageView imgDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        fab = findViewById(R.id.fabImage);
        imgDetail=findViewById(R.id.imgDetail);
        fabLike = findViewById(R.id.fabLike);
        fabSave = findViewById(R.id.fabSave);
        fabSetImage = findViewById(R.id.fabSetImage);
        fabShare=findViewById(R.id.fabShare);


        Intent intent=getIntent();
        String url;


        url = intent.getStringExtra("img");

      Glide.with(ImageDetailsActivity.this).load(url).into(imgDetail);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         if (showbutton ==false){

             fabShow();

             showbutton = true;
         }else {
             fabHide();
             showbutton=false;
         }
            }
        });



    }

    private void fabShow() {

        fabSetImage.show();
        fabSave.show();

        fabLike.show();
        fabShare.show();
    }

    private void fabHide(){
        fabSetImage.hide();
        fabSave.hide();

        fabLike.hide();
        fabShare.hide();
    }
}
