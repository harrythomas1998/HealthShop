package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.assignment.ViewStockItemCustomer.NAME;

public class ReviewActivity extends AppCompatActivity implements ArrayInterface{

    EditText comment, stars;
    Button b1;
    Review review;

    private FirebaseUser mCurrentUser;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        comment = findViewById(R.id.comment_edit_text);
        stars = findViewById(R.id.stars_edit_text);

        Intent intent = getIntent();

        final String name = intent.getStringExtra(NAME);

        b1 = findViewById(R.id.comment_submit_button);

        firebaseAuth = FirebaseAuth.getInstance();
        mCurrentUser = firebaseAuth.getCurrentUser();

        review = new Review();



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String c = comment.getText().toString();
                final String star = stars.getText().toString();

                if(name.equals("Kids MultiVitamin")) {

                    review.setRating(star);
                    review.setReviewText(c);
                    review.setStockItem(null);
                    reviewsProduct1.add(review);
                }
                if(name.equals("Ester-C")) {

                    review.setRating(star);
                    review.setReviewText(c);
                    review.setStockItem(null);
                    reviewsProduct2.add(review);
                }
                if(name.equals("Ante Natal Support")) {

                    review.setRating(star);
                    review.setReviewText(c);
                    review.setStockItem(null);
                    reviewsProduct3.add(review);
                }
                if(name.equals("Balanced Zinc Complex")) {

                    review.setRating(star);
                    review.setReviewText(c);
                    review.setStockItem(null);
                    reviewsProduct4.add(review);
                }
                if(name.equals("Gentle Iron")) {

                    review.setRating(star);
                    review.setReviewText(c);
                    review.setStockItem(null);
                    reviewsProduct5.add(review);
                }
                if(name.equals("Magnesium Citrate")) {

                    review.setRating(star);
                    review.setReviewText(c);
                    review.setStockItem(null);
                    reviewsProduct6.add(review);
                }
                if(name.equals("Pro Performance Chocolate")) {

                    review.setRating(star);
                    review.setReviewText(c);
                    review.setStockItem(null);
                    reviewsProduct7.add(review);
                }
                if(name.equals("Lean Shake Strawberry")) {

                    review.setRating(star);
                    review.setReviewText(c);
                    review.setStockItem(null);
                    reviewsProduct8.add(review);
                }
                if(name.equals("Vegan Lean Shake Vanilla")) {

                    review.setRating(star);
                    review.setReviewText(c);
                    review.setStockItem(null);
                    reviewsProduct9.add(review);
                }

                Toast.makeText(ReviewActivity.this, "Review Submitted!", Toast.LENGTH_LONG).show();

            }
        });



    }
}
