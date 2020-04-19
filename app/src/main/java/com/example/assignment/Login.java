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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginButton;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

                if(mFirebaseUser != null){
                    Toast.makeText(Login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, CustomerHome.class );
                    startActivity(i);

                }

                else{
                    Toast.makeText(Login.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();


                if(emailText.isEmpty() || passwordText.isEmpty()){

                    Toast.makeText(getApplicationContext(),"You must fill in all fields!",Toast.LENGTH_LONG).show();
                }


                else if(passwordText.contains(" ")){
                    Toast.makeText(getApplicationContext(),"Password cannot have a space!",Toast.LENGTH_LONG).show();
                    password.requestFocus();
                }
                else if(emailText.equals("Admin") && passwordText.equals("adminp123")){

                    Intent intToHome = new Intent(Login.this, AdminHome.class);
                    startActivity(intToHome);
                }
                else {
                    mFirebaseAuth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Login Error",Toast.LENGTH_LONG).show();


                            }

                            else{
                                Intent intToHome = new Intent(Login.this, CustomerHome.class);
                                startActivity(intToHome);
                            }

                        }
                    });

                }

            }
        });


    }
}
