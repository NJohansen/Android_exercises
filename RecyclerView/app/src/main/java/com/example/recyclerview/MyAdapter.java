package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Integer> myDataset;

    /**
     * @param dataset
    /* Provide a suitable constructor (depends on the kind of dataset) */
    MyAdapter(ArrayList<Integer> dataset){
        myDataset = dataset;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView textView;
        View frameLayout;

        ViewHolder(View frameLayout, TextView v) {
            super(frameLayout);
            this.frameLayout = frameLayout;
            textView = v;
        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.the_textview, parent, false);
        TextView tv = v.findViewById(R.id.rand_textview);
        final ViewHolder vh = new ViewHolder(v, tv);

        vh.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(vh.getAdapterPosition(),v);
            }
        });

        return vh;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(myDataset.get(position).toString());
    }

    // Return the size of your dataset (invoked by the random_tv manager)
    @Override
    public int getItemCount() {
        return myDataset.size();
    }

    private void deleteItem(final int position, View v){
        String message = String.format("Removing item at position: %s with value: %s",position,myDataset.get(position));
        Log.i("Holder", message);
        final int number = myDataset.get(position);
        Snackbar snack = Snackbar.make(v,message, BaseTransientBottomBar.LENGTH_LONG);
        snack.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDataset.add(position,number);
                notifyDataSetChanged();
            }
        });
        snack.show();
        myDataset.remove(position);
        notifyDataSetChanged();
    }

}
