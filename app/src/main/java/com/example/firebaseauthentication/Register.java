package com.example.firebaseauthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText mfullname,emailid,password;
    Button registerb;
    TextView uloginbt;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mfullname = findViewById(R.id.edittext_name);
        emailid = findViewById(R.id.edittext_email);
        password = findViewById(R.id.edittext_pass);
        registerb = findViewById(R.id.registerbutton);
        uloginbt = findViewById(R.id.lgibbutton);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        registerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString().trim();
                String pass = password.getText().toString().trim();
                
                if(TextUtils.isEmpty(email)){
                    emailid.setError("Email required!");
                    return;
                }

                if(TextUtils.isEmpty(pass)){
                    password.setText("Password required!");
                    return;
                }

                if(pass.length() < 6){
                    Toast.makeText(Register.this, "Password should be >= 6 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();

                }

                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else {
                            Toast.makeText(Register.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                uloginbt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(),Login.class));
                    }
                });
            }
        });


    }
}