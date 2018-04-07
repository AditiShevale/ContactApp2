package com.example.aditi.contactapp2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Recycler extends RecyclerView.Adapter<Recycler.MyViewHolder> {
    private List<Contact>mContactList;



    public Recycler(MainActivity mainActivity, List<Contact> contactList){
        mContactList = contactList;
    }

    @NonNull
    @Override
    public Recycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Contact contact = mContactList.get(position);
        Context context = holder.contactImg.getContext();
        Picasso.with(context).load(contact.getImage()).into(holder.contactImg);
        Log.i("xyz",String.valueOf(contact
                .getImage()));
        //holder.bind(mContactList.get(position));
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView contactImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            contactImg = itemView.findViewById(R.id.imageView);
        }


    }
}
