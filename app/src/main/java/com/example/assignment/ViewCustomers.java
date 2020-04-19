package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewCustomers extends AppCompatActivity  implements UsersAdapter.OnItemClickListener, ArrayInterface{

    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;

    DatabaseReference reference;
    private FirebaseAuth firebaseAuth;

    public static final String EMAIL = "email";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);

        recyclerView = findViewById(R.id.userRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("User");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                users.clear();

                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){

                    User user = snapshot1.getValue(User.class);
                    assert user != null;
                    user.setKey(snapshot1.getKey());
                    users.add(user);
                }

                usersAdapter = new UsersAdapter(ViewCustomers.this, users);
                recyclerView.setAdapter(usersAdapter);
                usersAdapter.setOnItemClickListener(ViewCustomers.this);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onItemClick(int position) {

        Intent i = new Intent(this, CustomerView.class);
        User clickedUserItem = users.get(position);

        i.putExtra(EMAIL, clickedUserItem.getEmail());
        i.putExtra(FIRSTNAME, clickedUserItem.getFirstName());
        i.putExtra(LASTNAME, clickedUserItem.getLastName());


        startActivity(i);

    }
}
