package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
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


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.security.auth.callback.Callback;

public class registration extends AppCompatActivity {

    private EditText name, phno, email, pass, age, occupation;
    private Button bt;
    private ProgressDialog p;
    register_data ud;
    private FirebaseAuth mAuth;
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
//        mAuth.setLanguageCode("fr");
        try{
            mAuth = FirebaseAuth.getInstance();
            databaseReference = FirebaseDatabase.getInstance().getReference();
        Toast.makeText(this, databaseReference.toString(), Toast.LENGTH_SHORT).show();}catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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
                    ud.setId(generateUniqueKey());
                    mAuth.createUserWithEmailAndPassword(ud.getEmail(),ud.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                databaseReference.child(ud.getEmail()).setValue(ud).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            p.dismiss();
                                            Toast.makeText(getApplicationContext(), "Registration completed", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(), login.class);
                                            startActivity(i);
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(),""+task.getException(),Toast.LENGTH_SHORT).show();
                                            p.dismiss();
                                        }
                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),""+task.getException(),Toast.LENGTH_SHORT).show();
                                p.dismiss();
                            }
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(this, "error found", Toast.LENGTH_SHORT).show();
                }
            }
        }
    public static String generateUniqueKey() {
        // Generate a random UUID
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}