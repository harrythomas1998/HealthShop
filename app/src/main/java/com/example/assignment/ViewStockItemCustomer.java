package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import static com.example.assignment.CustomerViewProducts.BRAND;
import static com.example.assignment.CustomerViewProducts.CATEGORY;
import static com.example.assignment.CustomerViewProducts.IMAGE;
import static com.example.assignment.CustomerViewProducts.PRICE;
import static com.example.assignment.CustomerViewProducts.STOCK;
import static com.example.assignment.CustomerViewProducts.TITLE;

public class ViewStockItemCustomer extends AppCompatActivity {

    TextView nameBox, brandBox, categoryBox, stockBox, priceBox;
    ImageView imageBox;

    Button b1;

    StockItem stockItem;

    private FirebaseUser mCurrentUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stock_item_customer);

        b1 = findViewById(R.id.add_to_shopping_bag);

        nameBox = findViewById(R.id.view_stock_item_customer_name);
        brandBox = findViewById(R.id.view_stock_item_customer_brand);
        categoryBox = findViewById(R.id.view_stock_item_customer_category);
        stockBox = findViewById(R.id.view_stock_item_customer_stock_level);
        priceBox = findViewById(R.id.view_stock_item_customer_price);
        imageBox = findViewById(R.id.view_stock_item_customer_image);

        Intent intent = getIntent();

        final String name = intent.getStringExtra(TITLE);
        final String image = intent.getStringExtra(IMAGE);
        final String brand = intent.getStringExtra(BRAND);
        final String category = intent.getStringExtra(CATEGORY);
        final int stock = intent.getIntExtra(STOCK, 0);
        final double price = intent.getDoubleExtra(PRICE, 0);

        nameBox.setText(name);
        Picasso.get().load(image).fit().into(imageBox);
        brandBox.setText(brand);
        categoryBox.setText(category);
        stockBox.setText(stock + "");
        priceBox.setText("€" + price);


        stockItem = new StockItem();

        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = firebaseAuth.getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("ShoppingCart").child(mCurrentUser.getUid());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stockItem.setName(name);
                stockItem.setImage(image);
                stockItem.setStock(stock);
                stockItem.setPrice(price);
                stockItem.setCategory(category);
                stockItem.setBrand(brand);
                stockItem.setReviews(null);

                myRef.push().setValue(stockItem);

                Toast.makeText(ViewStockItemCustomer.this, "Added to your Cart!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
