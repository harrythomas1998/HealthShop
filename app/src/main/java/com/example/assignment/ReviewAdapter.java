package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.Viewholder> {


    private Context mContext;
    private ArrayList<Review> reviews;


    public ReviewAdapter(Context context, ArrayList<Review> jobList){

        mContext = context;
        reviews = jobList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.review_cardview, parent, false);
        return new Viewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        Review currentItem = reviews.get(position);

        String t = currentItem.getReviewText();
        String rating = currentItem.getRating();


        holder.text.setText(t);
        holder.stars.setText(rating + " Stars");


    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public TextView text, stars;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.review_text);
            stars = itemView.findViewById(R.id.review_stars);

        }
    }

}