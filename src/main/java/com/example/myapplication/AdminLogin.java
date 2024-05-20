package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
EditText adminUser,adminPass;
AppCompatButton submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        adminUser = findViewById(R.id.adminUser);
        adminPass = findViewById(R.id.adminPass);
        submit = findViewById(R.id.loginAdmin);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adminUser.getText().toString().equals("admin") && adminPass.getText().toString().equals("0000")){
                    Toast.makeText(AdminLogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    adminUser.setText("");
                    adminPass.setText("");
                    Intent intent = new Intent(AdminLogin.this,AdminPage.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(AdminLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}