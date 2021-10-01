package com.example.firebaseauthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    TextView mloginbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mloginbt = findViewById(R.id.lgibbutton);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mloginbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
    }

    public void login(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();

    }
}