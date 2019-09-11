package com.example.databindingwithrecycler.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databindingwithrecycler.R;
import com.example.databindingwithrecycler.databinding.PostRowItemBinding;
import com.example.databindingwithrecycler.models.Post;
import com.example.databindingwithrecycler.utils.BindingUtils;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.CustomViewHolder> {

     PostAdapter(ArrayList<Post> postList, PostAdapterListener adapterListener) {
        this.postList = postList;
        this.adapterListener = adapterListener;
    }

    private ArrayList<Post> postList;
    private LayoutInflater layoutInflater;
    private PostAdapterListener adapterListener;

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        PostRowItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.post_row_item, parent, false);
        return new CustomViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        holder.binding.setPost(postList.get(position));
        holder.binding.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapterListener != null)
                    adapterListener.onPostClicked(postList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

     class CustomViewHolder extends RecyclerView.ViewHolder {
        private final PostRowItemBinding binding;

         CustomViewHolder(final PostRowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }

    }

    public interface PostAdapterListener {
        void onPostClicked(Post post);
    }

}
