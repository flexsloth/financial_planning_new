package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText email1,pass1;
    Button loginbt;
    TextView text,register_text;
    String emailpatt="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Intent i;
    String str;
    static register_data user_cred;
    //private static final String shp="";

    private FirebaseAuth mAuth;
    private FirebaseUser muser;
    private FirebaseDatabase mdata;
    private DatabaseReference databaseReference;
    ProgressDialog p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        email1 = (EditText) findViewById(R.id.logemail);
        pass1 = (EditText) findViewById(R.id.loginpass);
        loginbt = (Button) findViewById(R.id.button);
        text = (TextView) findViewById(R.id.textView4);
        register_text = (TextView) findViewById(R.id.register_text);
        loginbt.setOnClickListener(this);
        register_text.setOnClickListener(this);
        text.setOnClickListener(this);
        p= new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View view) {
        if(view==text) {
//            Intent i = new Intent(getApplicationContext(),registration.class);
//            startActivity(i);
            FirebaseAuth.getInstance().sendPasswordResetEmail(email1.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),"Check your Email",Toast.LENGTH_LONG).show();
                }
            });
            Toast.makeText(getApplicationContext(), "password reset Link Sent", Toast.LENGTH_LONG).show();
        }
        if(view==register_text){
            Intent i = new Intent(getApplicationContext(),registration.class);
            startActivity(i);
        }
        if(view==loginbt)
        {
            i= new Intent(getApplicationContext(), main_menue.class);
            String email = email1.getText().toString();
            String password = pass1.getText().toString();
            if(email.equals("register@gmail.com") && password.equals("myregestration"))
                startActivity(i);
            if(!email.matches(emailpatt))
            {
                email1.setError("Enter Context Email");
            }
            else if(password.isEmpty()||password.length()<6) {
                email1.setError("Enter Proper password");
            }
            else {
//                Toast.makeText(getApplicationContext(),"logging",Toast.LENGTH_LONG).show();
//                p.setMessage("Please wait Logging in");
//                p.setTitle("Login");
//                p.setCanceledOnTouchOutside(false);
//                p.show();
//                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            getdata(email,password);
//                            p.dismiss();
//                            Toast.makeText(getApplicationContext(), "Login sucessfull", Toast.LENGTH_SHORT).show();
//                            i= new Intent(getApplicationContext(), main_menue.class);
//                            startActivity(i);
//                            finish();
//                        } else {
//                            Toast.makeText(getApplicationContext(), "" + task.getException(), Toast.LENGTH_SHORT).show();
//                            p.dismiss();
//                        }
//                    }
//                });
                finish();
                startActivity(i);
           }
        }
    }

    private void getdata(String email,String password) {
        mdata.getReference().child("User Information").child(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        register_data i1 = ds.getValue(register_data.class);
                        if (i1 != null && i1.getEmail().equals(email)) {
                            user_cred=i1;
                            p.dismiss();
                            Toast.makeText(getApplicationContext(), "Login sucessfull", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(i);
                        }
                    }

                }
                else {
                    Toast.makeText(login.this, "failed to collect data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}