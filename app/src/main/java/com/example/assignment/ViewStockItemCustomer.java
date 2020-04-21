package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ViewStockItemCustomer extends AppCompatActivity implements ArrayInterface {

    TextView nameBox, brandBox, categoryBox, stockBox, priceBox;
    ImageView imageBox;

    Button b1, b2;

    StockItem stockItem;

    private FirebaseUser mCurrentUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;

    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;

    public static final String NAME = "name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stock_item_customer);

        b1 = findViewById(R.id.add_to_shopping_bag);
        b2 = findViewById(R.id.add_review_customer);

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

        recyclerView = findViewById(R.id.reviewsRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        assert name != null;
        if(name.equals("Kids MultiVitamin")) {

            reviewAdapter = new ReviewAdapter(ViewStockItemCustomer.this, reviewsProduct1);
            recyclerView.setAdapter(reviewAdapter);
        }
        if(name.equals("Ester-C")) {

            reviewAdapter = new ReviewAdapter(ViewStockItemCustomer.this, reviewsProduct2);
            recyclerView.setAdapter(reviewAdapter);
        }
        if(name.equals("Ante Natal Support")) {

            reviewAdapter = new ReviewAdapter(ViewStockItemCustomer.this, reviewsProduct3);
            recyclerView.setAdapter(reviewAdapter);
        }
        if(name.equals("Balanced Zinc Complex")) {

            reviewAdapter = new ReviewAdapter(ViewStockItemCustomer.this, reviewsProduct4);
            recyclerView.setAdapter(reviewAdapter);
        }
        if(name.equals("Gentle Iron")) {

            reviewAdapter = new ReviewAdapter(ViewStockItemCustomer.this, reviewsProduct5);
            recyclerView.setAdapter(reviewAdapter);
        }
        if(name.equals("Magnesium Citrate")) {

            reviewAdapter = new ReviewAdapter(ViewStockItemCustomer.this, reviewsProduct6);
            recyclerView.setAdapter(reviewAdapter);
        }
        if(name.equals("Pro Performance Chocolate")) {

            reviewAdapter = new ReviewAdapter(ViewStockItemCustomer.this, reviewsProduct7);
            recyclerView.setAdapter(reviewAdapter);
        }
        if(name.equals("Lean Shake Strawberry")) {

            reviewAdapter = new ReviewAdapter(ViewStockItemCustomer.this, reviewsProduct8);
            recyclerView.setAdapter(reviewAdapter);
        }
        if(name.equals("Vegan Lean Shake Vanilla")) {

            reviewAdapter = new ReviewAdapter(ViewStockItemCustomer.this, reviewsProduct9);
            recyclerView.setAdapter(reviewAdapter);
        }


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

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ViewStockItemCustomer.this, ReviewActivity.class);
                i.putExtra(NAME, name);
                startActivity(i);
            }
        });
    }
}
