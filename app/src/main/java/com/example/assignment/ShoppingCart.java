package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShoppingCart extends AppCompatActivity implements  ShoppingCartAdapter.OnItemClickListener, ArrayInterface {

    DatabaseReference reference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    TextView totalNum;
    Button b1;

    private RecyclerView recyclerView;
    private ShoppingCartAdapter adapter;

    double total;

    public static final String TITLE = "title";
    public static final String BRAND = "brand";
    public static final String CATEGORY = "category";
    public static final String  PRICE = "price";
    public static final String STOCK = "stock";
    public static final String IMAGE = "image";
    public static final String TOTAL = "total";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        recyclerView = findViewById(R.id.cartRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        adapter = new ShoppingCartAdapter(ShoppingCart.this, shoppingCart);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(ShoppingCart.this);

        totalNum = findViewById(R.id.totalTextBox);

        b1 = findViewById(R.id.checkoutButton);



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

                if(total > 50.0){

                    totalNum.setText("Total: €" + total/.9);
                }
                else{

                    totalNum.setText("Total: €" + total);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ShoppingCart.this, Checkout.class);
                i.putExtra(TOTAL, total);

                startActivity(i);

                Toast.makeText(ShoppingCart.this, "Gone to next class", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDeleteClick(int position) {

        StockItem selectedItem = shoppingCart.get(position);
        String selectedKey = selectedItem.getKey();
        reference.child(selectedKey).removeValue();
        removeItem(position);

        Toast.makeText(this, "Product Removed", Toast.LENGTH_SHORT).show();

    }

    public void removeItem(int position){

        shoppingCart.remove(position);
        adapter.notifyDataSetChanged();
    }
}
