package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginMain extends AppCompatActivity {
EditText user,pass;
AppCompatButton submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        user = findViewById(R.id.mainUser);
        pass = findViewById(R.id.mainPass);
        submit = findViewById(R.id.mainSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( user.getText().toString().equals("officer") && pass.getText().toString().equals("0000")){
                    Toast.makeText(LoginMain.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    user.setText("");
                    pass.setText("");
                    Intent intent = new Intent(LoginMain.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginMain.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}