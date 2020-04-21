package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.assignment.AdminViewStock.BRAND;
import static com.example.assignment.AdminViewStock.CATEGORY;
import static com.example.assignment.AdminViewStock.IMAGE;
import static com.example.assignment.AdminViewStock.KEY;
import static com.example.assignment.AdminViewStock.PRICE;
import static com.example.assignment.AdminViewStock.STOCK;
import static com.example.assignment.AdminViewStock.TITLE;

public class ViewStockItemAdmin extends AppCompatActivity {

    TextView nameBox, brandBox, categoryBox, stockBox, priceBox;
    ImageView imageBox;

    public static final String KEY2 = "key";

    Button b1, add, minus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stock_item_admin);

        b1 = findViewById(R.id.edit_stock_button);
        add = findViewById(R.id.addStockButton);
        minus = findViewById(R.id.minusStockButton);

        nameBox = findViewById(R.id.view_stock_item_admin_name);
        brandBox = findViewById(R.id.view_stock_item_admin_brand);
        categoryBox = findViewById(R.id.view_stock_item_admin_category);
        stockBox = findViewById(R.id.view_stock_item_admin_stock_level);
        priceBox = findViewById(R.id.view_stock_item_admin_price);
        imageBox = findViewById(R.id.view_stock_item_admin_image);

        final Intent intent = getIntent();

        final String key = intent.getStringExtra(KEY);
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


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ViewStockItemAdmin.this, EditStock.class);
                intent.putExtra(KEY2, key);
                startActivity(i);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int newStock = Integer.parseInt(stockBox.getText().toString());

                stockBox.setText(newStock + 1 + "");

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int newStock = Integer.parseInt(stockBox.getText().toString());

                stockBox.setText(newStock - 1 + "");


            }
        });




    }
}
