package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CustomerViewProducts extends AppCompatActivity implements StockAdapter.OnItemClickListener, ArrayInterface  {


    private RecyclerView recyclerView;
    private StockAdapter stockAdapter;

    DatabaseReference reference;
    private FirebaseAuth firebaseAuth;

    public static final String TITLE = "title";
    public static final String BRAND = "brand";
    public static final String CATEGORY = "category";
    public static final String  PRICE = "price";
    public static final String STOCK = "stock";
    public static final String IMAGE = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_products);


        recyclerView = findViewById(R.id.productsRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("StockItem");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                stockItems.clear();

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){

                    StockItem stockItem = snapshot1.getValue(StockItem.class);
                    assert stockItem != null;
                    stockItem.setKey(snapshot1.getKey());
                    stockItems.add(stockItem);
                }

                stockAdapter = new StockAdapter(CustomerViewProducts.this, stockItems);
                recyclerView.setAdapter(stockAdapter);
                stockAdapter.setOnItemClickListener(CustomerViewProducts.this);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(CustomerViewProducts.this, AdminViewStock.class );
        StockItem clickedStocKItem = stockItems.get(position);

        i.putExtra(TITLE, clickedStocKItem.getName());
        i.putExtra(BRAND, clickedStocKItem.getBrand());
        i.putExtra(CATEGORY, clickedStocKItem.getPrice());
        i.putExtra(PRICE, clickedStocKItem.getPrice());
        i.putExtra(STOCK, clickedStocKItem.getStock());
        i.putExtra(IMAGE, clickedStocKItem.getImage());



    }
}

