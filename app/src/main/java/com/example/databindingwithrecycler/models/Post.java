package com.example.databindingwithrecycler.models;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.databindingwithrecycler.R;

public class Post {
   private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view,String url){
        Glide.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view);

    }

}
