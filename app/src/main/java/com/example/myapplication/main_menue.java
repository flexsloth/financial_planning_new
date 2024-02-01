package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class main_menue extends AppCompatActivity implements View.OnClickListener {

    private ImageView option,record_icon,home_icon,calculator_icon,category_icon,analysis_icon;
    private DrawerLayout drawerLayout;
    static int number=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menue);
        getSupportActionBar().hide();
        option = (ImageView) findViewById(R.id.open_drawer_button);
        drawerLayout = findViewById(R.id.drawer_layout1);
        record_icon = (ImageView) findViewById(R.id.record_icon);
        analysis_icon=(ImageView)findViewById(R.id.analysis_icon);
        home_icon = (ImageView) findViewById(R.id.home_icon);
        calculator_icon = (ImageView) findViewById(R.id.calculator_icon);
        category_icon =(ImageView)findViewById(R.id.category_icon);
        analysis_icon.setOnClickListener(this);
        home_icon.setOnClickListener(this);
        calculator_icon.setOnClickListener(this);
        category_icon.setOnClickListener(this);
        record_icon.setOnClickListener(this);
        option.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new home_profile_class()).commit();
        number=0;
    }

    @Override
    public void onClick(View view) {
        if(view==option){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        else if(view==record_icon){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new records_activity()).commit();
            number=1;
        }
        else if(view==home_icon){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new home_profile_class()).commit();
            number=0;
        }
        else if(view==calculator_icon){
           // getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new calculator_classificationclass_java()).commit();
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        else if(view==category_icon){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new catagery_class()).commit();
            number=3;
        }
        else if(view==analysis_icon){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new analysis_class()).commit();
            //Toast.makeText(this, "analysis", Toast.LENGTH_SHORT).show();
            number=2;
        }
    }

    @Override
    public void onBackPressed() {
        if(number!=0){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new home_profile_class()).commit();
            overridePendingTransition(R.anim.swipe_in_left, R.anim.swipe_out_right);
            number=0;
        }
        else{
            super.onBackPressed();
            //Toast.makeText(this, "fuck up", Toast.LENGTH_SHORT).show();
        }
    }
}