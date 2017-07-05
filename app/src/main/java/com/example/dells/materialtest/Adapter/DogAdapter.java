package com.example.dells.materialtest.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dells.materialtest.R;
import com.example.dells.materialtest.bean.Dog;

import java.util.ArrayList;

/**
 * Created by Dozen on 2017/7/5.
 */

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Dog> mDogList;

    public DogAdapter(Context mContext, ArrayList<Dog> mDogList) {
        this.mContext = mContext;
        this.mDogList = mDogList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mContext != null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.dog_item, parent, false);
        ViewHolder mViewHolder = new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dog dog = mDogList.get(position);
        holder.dogName.setText(dog.getName());
        Glide.with(mContext).load(dog.getImageId()).into(holder.dogImage);
    }

    @Override
    public int getItemCount() {
        return mDogList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView dogName;
        ImageView dogImage;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            dogName = (TextView) itemView.findViewById(R.id.dog_name);
            dogImage = (ImageView) itemView.findViewById(R.id.dog_image);
        }
    }
}
