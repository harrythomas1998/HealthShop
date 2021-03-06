package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.assignment.ShoppingCart.TOTAL;

public class Checkout extends AppCompatActivity implements ArrayInterface {

    EditText nameOnCard, numCard, cvv, month, year;
    Spinner s1;
    Button b1;

    Order order;
    Card card;

    String method;
    ArrayList<StockItem> sItems;

    private FirebaseUser user;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference, ref2, ref3, ref4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        nameOnCard = findViewById(R.id.checkout_name_on_card);
        numCard = findViewById(R.id.checkout_card_num);
        cvv = findViewById(R.id.cvv_editText);
        month = findViewById(R.id.expiry_month);
        year = findViewById(R.id.expiry_year);
        s1 = findViewById(R.id.cardType_spinner);
        b1 = findViewById(R.id.confirm_order_button);

        order = new Order();
        card = new Card();
        sItems = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        Intent intent = getIntent();
        final double total = intent.getDoubleExtra(TOTAL, 0);


        List<String> cardTypes = new ArrayList<>();
        cardTypes.add("-Choose your Card Type-");
        cardTypes.add("MasterCard");
        cardTypes.add("American Express");
        cardTypes.add("Visa");

        ArrayAdapter<String> cardAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cardTypes);
        cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(cardAdapter);


        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 method = s1.getSelectedItem().toString();

                order.setPaymentMethod(method);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        reference = FirebaseDatabase.getInstance().getReference().child("ShoppingCart").child(user.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot snapshot1: dataSnapshot.getChildren()){

                    StockItem stockItem = snapshot1.getValue(StockItem.class);
                    assert stockItem != null;
                    stockItem.setKey(snapshot1.getKey());
                    sItems.add(stockItem);

                }
                order.setItems(sItems);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref2 = FirebaseDatabase.getInstance().getReference().child("Orders");
        ref3 = FirebaseDatabase.getInstance().getReference().child("ShoppingCart").child(user.getUid());
        ref3 = FirebaseDatabase.getInstance().getReference().child("ShoppingCart").child(user.getUid()).child("Orders");
        ref4 = FirebaseDatabase.getInstance().getReference().child("Card").child(user.getUid());

        order.setTotal(total);
        order.setUser(user.getEmail());



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameCard = nameOnCard.getText().toString();
                String cardNum = numCard.getText().toString();
                String c = cvv.getText().toString();
                int y = Integer.parseInt(year.getText().toString());
                int m = Integer.parseInt(month.getText().toString());



                boolean result = false;
                AbstractCardValidator validator = null;

                if (method.equals("Visa")) {
                    validator = new VisaValidation(nameCard, cardNum, m, y, c);

                } else if (method.equals("MasterCard")) {
                    validator = new MastercardValidation(nameCard, cardNum, m, y, c);

                } else if (method.equals("American Express")) {
                    validator = new AmericanExpressValidation(nameCard, cardNum, m, y, c);

                }

                assert validator != null;
                result = validator.validate();

                if (!result) {

                    Toast.makeText(Checkout.this, "Invalid Card Details!", Toast.LENGTH_LONG).show();

                } else {

                    card.setCardNum(cardNum);
                    card.setCvv(c);
                    card.setExpiryMonth(m);
                    card.setExpiryYear(y);
                    card.setNameOnCard(nameCard);

                    ref2.push().setValue(order);

                    ref3.removeValue();

                    ref4.push().setValue(card);
                    Toast.makeText(Checkout.this, "Order Confirmed!", Toast.LENGTH_LONG).show();

                }




            }


        });





    }
}
