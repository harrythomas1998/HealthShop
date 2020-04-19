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

        holder.email.setText(email);
        holder.firstName.setText(fName);
        holder.lastName.setText(lName);




    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public TextView email, firstName, lastName, noOfOrders;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            email = itemView.findViewById(R.id.email_text);
            firstName = itemView.findViewById(R.id.firstNameCardView);
            lastName = itemView.findViewById(R.id.lastNameCardView);
            noOfOrders = itemView.findViewById(R.id.noOfOrdersCardView);

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
