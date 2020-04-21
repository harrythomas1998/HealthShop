package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.Viewholder> {


    private Context mContext;
    private ArrayList<Order> orders;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{

        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;
    }

    public OrdersAdapter(Context context, ArrayList<Order> jobList){

        mContext = context;
        orders = jobList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.order_cardview, parent, false);
        return new Viewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        Order currentItem = orders.get(position);

        String email = currentItem.getUser();
        double total = currentItem.getTotal();

        holder.email.setText(email);
        holder.total.setText("€" + Double.toString(total));




    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public TextView email, total;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            email = itemView.findViewById(R.id.orderItemEmail);
            total = itemView.findViewById(R.id.orderItemPrice);

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
