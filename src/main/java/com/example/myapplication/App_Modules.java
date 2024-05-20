package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.text.LineBreaker;
import android.icu.lang.UCharacter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class App_Modules extends AppCompatActivity {
    TextView home, officer, admin, adminPage, voterScreen, navigationDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_modules);
        home = findViewById(R.id.home_module_info);
        officer = findViewById(R.id.officer_Login_module_info);
        admin = findViewById(R.id.admin_login_module_info);
        adminPage = findViewById(R.id.admin_page_module_info);
        voterScreen = findViewById(R.id.voting_module_info);
        navigationDrawer = findViewById(R.id.navigation_module_info);
        home.setText("The home module consist of Rules and Regulations that are applicable to both Gadzetedn officer as well as the election commissionar.These rules must be followed by both Officer as well as the election commissionar.");
        officer.setText("The officer login module consist of a login screen for the Gadzeted officer who is going to conduct the election for the perticular election booth. The login Credentials will be provided by the election commission itself.");
        admin.setText("The admin login page is also same as the officer login page where the election commissionar has to do login with the login credenials provided by the election commission.");
        adminPage.setText("The admin page can only be accessed on login of the admin ie. The election commissionar which includes the results of election and the winning and loosing parties");
        voterScreen.setText("As the admin page can be only accessed only after admin login the Voting screen also will appear on login of the election officer with the credentials provided by the election commission");
        navigationDrawer.setText("The navigation drawer is the main operational module which consist of the menu for all the modules inside the app such as about us, Admin Login, Officer Login, App modules Screen etc.");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            home.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            officer.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            admin.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            adminPage.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            voterScreen.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            navigationDrawer.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
        }


    }

    public void goBack(View view) {
        Intent intent3 =  new Intent(App_Modules.this,Home_Screen.class);
        startActivity(intent3);
    }
}