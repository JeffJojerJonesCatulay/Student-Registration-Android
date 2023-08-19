package com.example.exer7catulayjeffjojerjones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class searchStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student);

        bottomNavigation();
    }

    private void bottomNavigation(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_register:
                        startActivity(new Intent(getApplicationContext(),
                                MainActivity.class));
                        finish();
                        bottomNavigationView.setSelectedItemId(R.id.nav_search);
                        return true;
                    case R.id.nav_search:
                        return true;
                }
                finish();
                return false;
            }
        });
    }
}