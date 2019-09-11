package com.example.databindingwithrecycler.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.load.engine.Resource;
import com.example.databindingwithrecycler.R;
import com.example.databindingwithrecycler.databinding.ActivityMainBinding;
import com.example.databindingwithrecycler.models.Post;
import com.example.databindingwithrecycler.models.User;
import com.example.databindingwithrecycler.utils.GridSpacingItemDecoration;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements PostAdapter.PostAdapterListener {

    private MyClickHandler handler;
    private PostAdapter adapter;
    private RecyclerView recyclerView;
    private ActivityMainBinding binding;
    private User user;
    private Context context;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=MainActivity.this;
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        Toolbar toolbar=binding.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.toolbar_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fab=binding.content.fab;
        handler=new MyClickHandler(this);
        renderProfile();
        initRecyclerView();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.onProfileFabClicked(view);
            }
        });

    }

    private void renderProfile() {
        user=new User();
        user.setName("David Attenborough");
        user.setEmail("david123@gmail.com");
        user.setProfileImage("https://api.androidhive.info/images/nature/david.jpg");
        user.setAbout("Naturalist");

        user.numberOfPosts.set(3400l);
        user.numberOfFollowers.set(3050890l);
        user.numberOfFollowing.set(150l);

        binding.setUser(user);
        binding.content.setHandler(handler);
    }

    private void initRecyclerView() {
        recyclerView=binding.content.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3,dpToPx(4),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        adapter=new PostAdapter(getPost(),this);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Post> getPost() {
        ArrayList<Post>posts = new ArrayList<>();
        for (int i=1;i<10;i++){
            Post post = new Post();
            post.setImageUrl("https://api.androidhive.info/images/nature/"+i+".jpg");
            posts.add(post);
        }
        return posts;
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));

    }

    @Override
    public void onPostClicked(Post post) {
        Toast.makeText(getApplicationContext(), "Post clicked! " + post.getImageUrl(), Toast.LENGTH_SHORT).show();

    }


    public class MyClickHandler{
        Context context;

        public MyClickHandler(Context context) {
            this.context = context;
        }

        public void onProfileFabClicked(View view) {
            user.setName("Sir David Attenborough");
            user.setProfileImage("https://api.androidhive.info/images/nature/david1.jpg");

            // updating ObservableField
            user.numberOfPosts.set(5500L);
            user.numberOfFollowers.set(5050890L);
            user.numberOfFollowing.set(180L);
        }

        public boolean onProfileImageLongPressed(View view) {
            Toast.makeText(getApplicationContext(), "Profile image long pressed!", Toast.LENGTH_LONG).show();
            return false;
        }

        public void onFollowersClicked(View view) {
            Toast.makeText(context, "Followers is clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onFollowingClicked(View view) {
            Toast.makeText(context, "Following is clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onPostsClicked(View view) {
            Toast.makeText(context, "Posts is clicked!", Toast.LENGTH_SHORT).show();
        }
    }
}
