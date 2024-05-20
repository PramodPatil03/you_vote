package com.example.myapplication;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavAction;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home_Screen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
DrawerLayout drawerLayout;
NavigationView navigationView;
TextView t1,t2,t3,t4,t5,aboutApp2;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        drawerLayout = findViewById(R.id.home_na_drawer);
        navigationView = findViewById(R.id.home_nav_view);
        toolbar = findViewById(R.id.toolbar_main);
t1 = findViewById(R.id.textview1);
t2 = findViewById(R.id.textview2);
t3 = findViewById(R.id.textview3);
t4 = findViewById(R.id.textview4);
t5 = findViewById(R.id.textview5);
aboutApp2 = findViewById(R.id.aboutApp2);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                t1.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
                t2.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
                t3.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
                t4.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
                t5.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
                aboutApp2.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
                aboutApp2.setText("Hello User!!!\n  This app is made to briefly demonstrate how we can change the traditional way of voting. This app is made to replace the traditional EVM machine which works on paper and ink usage. This App usage an internal private file to save all the voting data so doesn't waste papers.\n\n   The traditional way used to count the votes manually counting each paper and each machine,Whereas this app automatically counts the votes at the time of voting itself so the results can be declared in minimun time" );
            }


        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    @Override
    public  void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(R.id.nav_home == item.getItemId()){
            Toast.makeText(Home_Screen.this,"Home",Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        } else if (R.id.nav_aboutus == item.getItemId()) {
            Intent intent3= new Intent(Home_Screen.this,About_Us.class);
            startActivity(intent3);
        }else if (R.id.nav_modules == item.getItemId()) {
            Intent intent4= new Intent(Home_Screen.this,App_Modules.class);
            startActivity(intent4);
        }else if (R.id.officer_Login == item.getItemId()) {
            Intent intent1= new Intent(Home_Screen.this,LoginMain.class);
            startActivity(intent1);
        }else if (R.id.admin_login == item.getItemId()) {
            Intent intent2= new Intent(Home_Screen.this,AdminLogin.class);
            startActivity(intent2);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}