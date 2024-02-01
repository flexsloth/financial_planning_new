package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {
    private EditText time,present_cost,present_savings;
    private TextView output,futval,savingsop,text;
    private ConstraintLayout layout;
    private String year_time;
    private float cost,cost1;
    private DataBaseHelper db;
    private Button bt;
    private data d;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();try{
        db = new DataBaseHelper(this);
        time = (EditText) findViewById(R.id.timeframe);
        output = (TextView) findViewById(R.id.output);
        futval = (TextView) findViewById(R.id.output2);
        savingsop = (TextView) findViewById(R.id.output3);
        present_cost = (EditText) findViewById(R.id.present_cost);
        present_savings =(EditText) findViewById(R.id.present_saving);
        text=findViewById(R.id.result_textinfo);
        layout =findViewById(R.id.output_textinfo);
        layout.setVisibility(View.INVISIBLE);
        bt = (Button) findViewById(R.id.submit);
        //db.createDatabase();
        }catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        bt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                try {
                    if (!time.getText().toString().equals("") && !present_cost.getText().toString().equals("") && !present_savings.getText().toString().equals("")) {
                        year_time = time.getText().toString();
                        cost = Float.parseFloat(present_cost.getText().toString());
                        d = db.fvFactor(Integer.parseInt(year_time));
                        output.setText(":   "+ (d.getFuture_cost()) * cost);
                        cost1 = Float.parseFloat(present_savings.getText().toString());
                        futval.setText(":   "+ (d.getCurrent_invest_future_cost()) * cost1);
                        savingsop.setText(":   "+ (cost - cost1) / d.getAnual_saving());
                        text.setVisibility(View.INVISIBLE);
                        layout.setVisibility(View.VISIBLE);
                        Animation slideDown = AnimationUtils.loadAnimation(view.getContext(), R.anim.slide_up);
                        layout.startAnimation(slideDown);
                        layout.setVisibility(View.VISIBLE);
                        //insert_data_in_to_firebase(String.valueOf((d.getFuture_cost()) * cost),String.valueOf((d.getCurrent_invest_future_cost())* cost1),String.valueOf(((cost-cost1)/d.getAnual_saving())));

                    }
                    else {
                        time.setError("please enter proper details of all fields");
                        present_cost.setError("please enter proper details of all fields");
                        present_savings.setError("please enter proper details of all fields");
                    }
                }catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void insert_data_in_to_firebase(String valueOf, String valueOf1, String valueOf2) {
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("users");

    }
}