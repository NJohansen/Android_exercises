package com.example.randompicturesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Context context;


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView);
        }
    }

    public ImageAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = String.format("https://picsum.photos/500/500?image=%d",position);
        Glide.with(context).clear(holder.imageView);
        Glide.with(context).load(url).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return 50;
    }
}
