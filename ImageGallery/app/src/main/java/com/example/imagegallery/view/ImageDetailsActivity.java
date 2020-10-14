package com.example.imagegallery.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.imagegallery.R;
import com.example.imagegallery.database.AppDatabase;
import com.example.imagegallery.database.PhotoCommentRecyclerviewAdapter;
import com.example.imagegallery.database.PhotoComments;
import com.example.imagegallery.database.PhotoDetails;
import com.example.imagegallery.model.GalleryItem;
import com.example.imagegallery.network.response.Images;

import java.util.ArrayList;
import java.util.List;

public class ImageDetailsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imageView;
    private EditText editTextComment;
    private Button buttonSave;
    private RecyclerView recyclerViewComments;

    private PhotoCommentRecyclerviewAdapter adapter;
    private AppDatabase database;
    private GalleryItem galleryItem;
    private List<PhotoComments> photoComments = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        galleryItem = (GalleryItem) getIntent().getSerializableExtra("PHOTO");
        initViews();
        showImage();
        setUpRecyclerView();
    }

    @Override
    public void onClick(View v) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                saveComments(editTextComment.getText().toString().trim());
            }
        });
    }

    @Override
    public void onNetworkConnected() {

    }

    @Override
    public void onNetworkLost() {

    }

    private void initViews(){
        imageView = findViewById(R.id.iv_gallery_details);
        buttonSave = findViewById(R.id.button_comment);
        editTextComment = findViewById(R.id.edit_comment);
        recyclerViewComments = findViewById(R.id.recyclerview_comment);
        buttonSave.setOnClickListener(this);
    }

    private void setUpRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewComments.setLayoutManager(linearLayoutManager);
        AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    if(getPhotoComments()!=null) {
                        photoComments = getPhotoComments();
                        refreshComments(photoComments);
                    }
                }
        });
        adapter = new PhotoCommentRecyclerviewAdapter(photoComments);
        recyclerViewComments.setAdapter(adapter);

    }

    private void showImage(){
        if(galleryItem != null) {
            List<Images> galleryImages = galleryItem.getImages();
            if (galleryImages != null && !galleryImages.isEmpty()) {
                String url = galleryImages.get(0).getLink();
                Glide.with(this)  //2
                        .load(url) //3
                        .centerCrop() //4
                        .into(imageView);
            }
            savePhotoDetails();
        }
    }

    private void savePhotoDetails(){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                PhotoDetails photoDetails = new PhotoDetails(galleryItem.getId(),
                        galleryItem.getTitle(),
                        galleryItem.getLink());

                database = AppDatabase.Companion.getAppDataBase(ImageDetailsActivity.this);
                database.galleryDao().insertPhotoDetails(photoDetails);
            }
        });

    }

    private void saveComments(String description){
        if(!description.isEmpty()) {
            PhotoComments comment = new PhotoComments(galleryItem.getId(),description,0);
            database = AppDatabase.Companion.getAppDataBase(ImageDetailsActivity.this);
            database.getCommentDao().insertPhotoComment(comment);
            refreshComments(getPhotoComments());
        }else{
            Toast.makeText(getApplicationContext(),"Please enter comment",Toast.LENGTH_LONG).show();
        }
    }

    private List<PhotoComments> getPhotoComments(){
        database = AppDatabase.Companion.getAppDataBase(this);
        return database.getCommentDao().getCommentsBy(galleryItem.getId());
    }

    private void refreshComments(List<PhotoComments> comments){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(comments != null) {
                    adapter.addComments(comments);
                }
            }
        });

    }
}