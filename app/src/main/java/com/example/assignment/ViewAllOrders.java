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

public class ViewAllOrders extends AppCompatActivity implements OrdersAdapter.OnItemClickListener, ArrayInterface {

    private RecyclerView recyclerView;
    private OrdersAdapter adapter;

    DatabaseReference reference;
    private FirebaseAuth firebaseAuth;

    public static final String EMAIL = "email";
    public static final String TOTAL = "total";
    public static final String PAYMENTMETHOD = "paymentmethod";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_orders);

        recyclerView = findViewById(R.id.allOrdersRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Orders");


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                allOrders.clear();

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){

                    Order order = snapshot1.getValue(Order.class);
                    assert order != null;

                    allOrders.add(order);
                }

                adapter = new OrdersAdapter(ViewAllOrders.this, allOrders);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(ViewAllOrders.this);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(this, CustomerView.class);
        Order clickedOrderItem = allOrders.get(position);

        i.putExtra(EMAIL, clickedOrderItem.getUser());
        i.putExtra(TOTAL, clickedOrderItem.getTotal());
        i.putExtra(PAYMENTMETHOD, clickedOrderItem.getPaymentMethod());


        startActivity(i);

    }
}
