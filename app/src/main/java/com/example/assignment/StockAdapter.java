package com.example.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.Viewholder> {


    private Context mContext;
    private ArrayList<StockItem> stockItems;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{

        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;
    }

    public StockAdapter(Context context, ArrayList<StockItem> jobList){

        mContext = context;
        stockItems = jobList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.stock_cardview, parent, false);
        return new Viewholder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        StockItem currentItem = stockItems.get(position);

        String name = currentItem.getName();
        String category = currentItem.getCategory();
        String brand = currentItem.getBrand();
        double price = currentItem.getPrice();
        int stock = currentItem.getStock();
        String image = currentItem.getImage();

        holder.title.setText(name);
        holder.brand.setText(brand);
        holder.category.setText(category);
        holder.price.setText("€" + Double.toString(price));
        Picasso.get().load(image).fit().centerInside().into(holder.picture);




    }

    @Override
    public int getItemCount() {
        return stockItems.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public TextView title, brand, category, price;
        public ImageView picture;


        public Viewholder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.stock_title);
            brand = itemView.findViewById(R.id.stock_brand);
            category = itemView.findViewById(R.id.stock_category);
            price = itemView.findViewById(R.id.stock_price);
            picture = itemView.findViewById(R.id.stock_image);

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

