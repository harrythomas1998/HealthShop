package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText fName, lName, password, email;
    EditText line1, town, county, eircode;
    Button registerButton;

    FirebaseAuth mFirebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        fName = findViewById(R.id.registerFirstName);
        lName = findViewById(R.id.registerLastName);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);

        line1 = findViewById(R.id.registerLine1);
        town = findViewById(R.id.registertown);
        county = findViewById(R.id.registerCounty);
        eircode = findViewById(R.id.registerEircode);

        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                final String firstNameText = fName.getText().toString();
                final String lastNameText = lName.getText().toString();

                final String line1txt = line1.getText().toString();
                String towntxt = town.getText().toString();
                final String countytxt = county.getText().toString();
                final String eircodetxt = eircode.getText().toString();

                final Address a1 = new Address(line1txt, towntxt, countytxt, eircodetxt);



                if(emailText.isEmpty() || passwordText.isEmpty() || firstNameText.isEmpty() || lastNameText.isEmpty()){

                    Toast.makeText(getApplicationContext(),"You must fill in all fields!",Toast.LENGTH_LONG).show();
                }
                else if(passwordText.contains(" ")){
                    Toast.makeText(getApplicationContext(),"Password cannot have a space!",Toast.LENGTH_LONG).show();
                    password.requestFocus();
                }
                else if(passwordText.length() <= 6){
                    Toast.makeText(getApplicationContext(),"Password must be over 6 characters!",Toast.LENGTH_LONG).show();
                    password.requestFocus();
                }
                else{

                    mFirebaseAuth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){

                                Toast.makeText(getApplicationContext(),"Sign Up Unsuccessful, Try Again",Toast.LENGTH_SHORT).show();

                            }
                            else{

                                User user = new User(emailText, firstNameText, lastNameText, a1);

                                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser()

                                        .getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        startActivity(new Intent(Register.this, CustomerHome.class));

                                    }
                                });



                            }


                        }
                    });
                }
            }
        });

    }
}
