package com.haiph.assignmentandroidnw;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import android.Manifest;
import android.app.Activity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.haiph.assignmentandroidnw.fragment.FavoriteFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import ir.siaray.downloadmanagerplus.classes.Downloader;
import ir.siaray.downloadmanagerplus.interfaces.DownloadListener;

public class ImageDetailsActivity extends AppCompatActivity {
    private AlertDialog alertDialog ;
    private FloatingActionButton fab, fabShare, fabSave, fabLike, fabSetImage;
    boolean showbutton;
    private static final int REQUEST_CODE = 1;
    private ImageView imgDetail;
    final Intent intent = getIntent();
    private Bitmap bitmap;
    String url;
    DownloadManager downloadManager;
    String selectedImagePath;
    private static final int PERMISSION_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        final Intent intent=getIntent();


        url = intent.getStringExtra("img");

        fab = findViewById(R.id.fabImage);
        imgDetail = findViewById(R.id.imgDetail);
        fabLike = findViewById(R.id.fabLike);


        fabSave = findViewById(R.id.fabSave);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog = new AlertDialog.Builder(ImageDetailsActivity.this).create();
                alertDialog.setTitle("Save Image");
                alertDialog.setTitle("You sure to save your image");
                alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SaveImage();
                    }
                });
                alertDialog.setButton2("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });


        fabSetImage = findViewById(R.id.fabSetImage);
        fabSetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                   setWallpaper();
            }
        });

        fabShare = findViewById(R.id.fabShare);
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            getShare();
            }
        });



        Glide.with(ImageDetailsActivity.this).load(url).into(imgDetail);

        fabLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






            }
        });



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showbutton == false) {

                    fabShow();

                    showbutton = true;
                } else {
                    fabHide();
                    showbutton = false;
                }
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE

                }, PERMISSION_REQUEST_CODE);
            }


    }

    private void fabShow() {

        fabSetImage.show();
        fabSave.show();

        fabLike.show();
        fabShare.show();
    }

    private void fabHide() {
        fabSetImage.hide();
        fabSave.hide();

        fabLike.hide();
        fabShare.hide();
    }

    private void getShare() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Uri bmpUri = (Uri) getLocalImageBitmapUri(imgDetail);
        if (bmpUri != null) {
            // Construct a ShareIntent with link to image
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            shareIntent.setType("image/*");
            // Launch sharing dialog for image
            startActivity(Intent.createChooser(shareIntent, "Share Image"));
        } else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void StartDownload(){


    }

    private Uri getLocalImageBitmapUri(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }


    private void SaveImage() {
        Picasso.get()
                .load(url)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                        try {
                            File mydie = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString() + "/Camera");
                            if (!mydie.exists()) {
                                mydie.mkdirs();
                            }
                            FileOutputStream fileOutputStream = new FileOutputStream(new File(mydie, new Date().toString() + ".jpg"));
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);


                            fileOutputStream.flush();
                            fileOutputStream.close();
                            Toast.makeText(getApplicationContext(), "Save", Toast.LENGTH_LONG).show();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }

                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

    }

    public void setWallpaper(){
        downloadManager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Long reference = downloadManager.enqueue(request);

        Picasso.get().load(url).into(new Target(){
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(ImageDetailsActivity.this);
                try {
                    wallpaperManager.setBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(ImageDetailsActivity.this, "Wallpaper Changed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Toast.makeText(ImageDetailsActivity.this, "Loading image failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Toast.makeText(ImageDetailsActivity.this, "Downloading image", Toast.LENGTH_SHORT).show();
            }
        });

    }

//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==REQUEST_CODE&&resultCode== Activity.RESULT_OK){
//            try {
//                Uri selectedImage = data.getData();
//                String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//                cursor.moveToFirst();
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                String filePath = cursor.getString(columnIndex);
//                Log.v("roni", filePath);
//                cursor.close();
//                if(bitmap != null && !bitmap.isRecycled())
//                {
//                    bitmap = null;
//                }
//                bitmap = BitmapFactory.decodeFile(filePath);
//                //imageView.setBackgroundResource(0);
//                imgDetail.setImageBitmap(bitmap);
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//
//
//    }
//
//    public void saveImageToStorageLibary(){
//        SharedPreferences sharedPreferences=getSharedPreferences("image",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString("ImagePath",selectedImagePath);
//        editor.commit();
//    }
//
//    public void restore(){
//        SharedPreferences sharedPreferences = getSharedPreferences("image",MODE_PRIVATE);
//        selectedImagePath = sharedPreferences.getString("ImagePath","");
//        bitmap=BitmapFactory.decodeFile(selectedImagePath);
//        imgDetail.setImageBitmap(bitmap);
//
//
//    }

}
