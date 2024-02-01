package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class main_menue extends AppCompatActivity implements View.OnClickListener {

    private ImageView option,record_icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menue);
        getSupportActionBar().hide();
        option = (ImageView) findViewById(R.id.open_drawer_button);
        record_icon = (ImageView) findViewById(R.id.record_icon);
        record_icon.setOnClickListener(this);
        option.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==option){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        else if(view==record_icon){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new records_activity()).commit();
        }
    }
}