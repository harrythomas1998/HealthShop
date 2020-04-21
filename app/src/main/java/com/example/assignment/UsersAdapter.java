package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.Viewholder> {


    private Context mContext;
    private ArrayList<User> users;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{

        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;
    }

    public UsersAdapter(Context context, ArrayList<User> jobList){

        mContext = context;
        users = jobList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.user_cardview, parent, false);
        return new Viewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        User currentItem = users.get(position);

        String email = currentItem.getEmail();
        String fName = currentItem.getFirstName();
        String lName = currentItem.getLastName();

        String lin1Txt = currentItem.getAddress().getLine1();
        String townTxt = currentItem.getAddress().getTown();
        String countyTxt = currentItem.getAddress().getCounty();
        String eircodeTxt = currentItem.getAddress().getEircode();


        holder.email.setText(email);
        holder.firstName.setText(fName);
        holder.lastName.setText(lName);
        holder.line1.setText(lin1Txt);
        holder.town.setText(townTxt);
        holder.county.setText(countyTxt);
        holder.eircode.setText(eircodeTxt);




    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public TextView email, firstName, lastName, line1, town, county, eircode;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            email = itemView.findViewById(R.id.email_text);
            firstName = itemView.findViewById(R.id.firstNameCardView);
            lastName = itemView.findViewById(R.id.lastNameCardView);
            line1 = itemView.findViewById(R.id.line1_view);
            town = itemView.findViewById(R.id.town_view);
            county = itemView.findViewById(R.id.county_view);
            eircode = itemView.findViewById(R.id.eircode_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(mListener !=null){

                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){

                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

}
