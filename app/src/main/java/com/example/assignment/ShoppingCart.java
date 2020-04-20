package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShoppingCart extends AppCompatActivity implements  StockAdapter.OnItemClickListener, ArrayInterface {

    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    TextView totalNum;

    private RecyclerView recyclerView;
    private StockAdapter adapter;

    double total;

    public static final String TITLE = "title";
    public static final String BRAND = "brand";
    public static final String CATEGORY = "category";
    public static final String  PRICE = "price";
    public static final String STOCK = "stock";
    public static final String IMAGE = "image";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        recyclerView = findViewById(R.id.cartRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        adapter = new StockAdapter(ShoppingCart.this, shoppingCart);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(ShoppingCart.this);

        totalNum = findViewById(R.id.totalTextBox);



        reference = FirebaseDatabase.getInstance().getReference().child("ShoppingCart").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                shoppingCart.clear();

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){

                    StockItem stockItem = snapshot1.getValue(StockItem.class);
                    assert stockItem != null;
                    stockItem.setKey(snapshot1.getKey());
                    total = total + stockItem.getPrice();
                    shoppingCart.add(stockItem);


                }
                adapter.notifyDataSetChanged();

                totalNum.setText("Total: €" + total);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }
}
