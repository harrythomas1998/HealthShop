package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminViewStock extends AppCompatActivity implements StockAdapter.OnItemClickListener, ArrayInterface  {


    private RecyclerView recyclerView;
    private StockAdapter stockAdapter;
    SearchView search;

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
        setContentView(R.layout.activity_admin_view_stock);


        recyclerView = findViewById(R.id.stockRecyclerAdmin);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        search = findViewById(R.id.searchViewStock);


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

                stockAdapter = new StockAdapter(AdminViewStock.this, stockItems);
                recyclerView.setAdapter(stockAdapter);
                stockAdapter.setOnItemClickListener(AdminViewStock.this);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                stockAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(AdminViewStock.this, ViewStockItemAdmin.class );
        StockItem clickedStocKItem = stockItems.get(position);

        i.putExtra(TITLE, clickedStocKItem.getName());
        i.putExtra(BRAND, clickedStocKItem.getBrand());
        i.putExtra(CATEGORY, clickedStocKItem.getCategory());
        i.putExtra(PRICE, clickedStocKItem.getPrice());
        i.putExtra(STOCK, clickedStocKItem.getStock());
        i.putExtra(IMAGE, clickedStocKItem.getImage());

        startActivity(i);

    }
}
