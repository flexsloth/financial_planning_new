package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.security.auth.callback.Callback;

public class registration extends AppCompatActivity {

    private EditText name, phno, email, pass, age, occupation;
    private Button bt;
    private ProgressDialog p;
    register_data ud;
    private FirebaseAuth mAuth;
    private FirebaseUser muser;
    FirebaseDatabase mdata;
    DatabaseReference databaseReference;
    private EditText input;
    private String emailPat = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    AlertDialog.Builder builder;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        name = (EditText) findViewById(R.id.name1);
        phno = (EditText) findViewById(R.id.phno1);
        email = (EditText) findViewById(R.id.email1);
        pass = (EditText) findViewById(R.id.Password1);
        bt = (Button) findViewById(R.id.submit1);
        age = (EditText) findViewById(R.id.age);
        occupation = (EditText) findViewById(R.id.occupation);
        p = new ProgressDialog(this);
        ud = new register_data();
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
        mdata = FirebaseDatabase.getInstance();
        mAuth.setLanguageCode("fr");
        databaseReference = mdata.getReference();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }
        private void register() {
            ud.setPhoneno(phno.getText().toString());
            ud.setEmail(email.getText().toString());
            ud.setPassword(pass.getText().toString());
            ud.setName(name.getText().toString());
            ud.setAge(age.getText().toString());
            ud.setOccupation(occupation.getText().toString());
            if (!ud.getEmail().matches(emailPat)) {
                email.setError("enter correct email");
            } else if (ud.getName().equals(""))
                name.setError("enter name");
            else if (ud.getPhoneno().equals("") || phno.getText().length() < 10)
                phno.setError("enter valid phno");
            else if (ud.getEmail().equals(""))
                email.setError("enter email");
            else if (ud.getPassword().equals("") || pass.length() < 6)
                pass.setError("enter valid password");
            else if (ud.getAge().equals(""))
                age.setError("enter valid age");
            else if (ud.getOccupation().equals(""))
                occupation.setError("enter valid occupation");
            else {
                try {
                    p.setMessage("Please wait Registering...");
                    p.setTitle("Registration");
                    p.setCanceledOnTouchOutside(false);
                    p.show();
                    String phoneNumber = "+91" + phno.getText().toString().trim();
                    sendVerificationCode(phoneNumber);
                } catch (Exception e) {
                    Toast.makeText(this, "error found", Toast.LENGTH_SHORT).show();
                }
            }
        }

    private void sendVerificationCode(String phoneNumber) {
        Intent i =new Intent(getApplicationContext(),login.class);
        startActivity(i);
    }
}