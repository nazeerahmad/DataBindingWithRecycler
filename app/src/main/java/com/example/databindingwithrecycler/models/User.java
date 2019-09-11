package com.example.databindingwithrecycler.models;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.bumptech.glide.Glide;
import com.example.databindingwithrecycler.BR;
import com.example.databindingwithrecycler.R;

public class User extends BaseObservable {

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.example.databindingwithrecycler.BR.name);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(com.example.databindingwithrecycler.BR.email);
    }

    @Bindable
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        notifyPropertyChanged(com.example.databindingwithrecycler.BR.profileImage);
    }

    @Bindable
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
        notifyPropertyChanged(com.example.databindingwithrecycler.BR.about);
    }

   private String name;
    private String email;
    private String profileImage;
    private String about;

    public ObservableField<Long> getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public ObservableField<Long> getNumberOfFollowing() {
        return numberOfFollowing;
    }

    public ObservableField<Long> getNumberOfPosts() {
        return numberOfPosts;
    }

    public ObservableField<Long>numberOfFollowers = new ObservableField<>();
    public ObservableField<Long>numberOfFollowing = new ObservableField<>();
    public ObservableField<Long>numberOfPosts = new ObservableField<>();

    public User() {
    }

    @BindingAdapter({"profileImage"})
    public static void loadImage(ImageView view,String url){
        Glide.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(view);

    }
}
