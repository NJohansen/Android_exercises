package com.example.contentproviderexercise2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    List<ContactPerson> contacts;

    public ContactAdapter(List<ContactPerson> numbers) {
        this.contacts = numbers;
    }


    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(final ContactAdapter.ViewHolder holder, int position) {
        ContactPerson person = contacts.get(position);
        holder.column1.setText(person.id);
        holder.column2.setText(person.displayName);
        holder.column3.setText(person.lookupKey);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView column1;
        public TextView column2;
        public TextView column3;


        public ViewHolder(View container) {
            super(container);
            column1 = container.findViewById(R.id.tvColumn1);
            column2 = container.findViewById(R.id.tvColumn2);
            column3 = container.findViewById(R.id.tvColumn3);
        }
    }

}
