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

public class login extends AppCompatActivity implements View.OnClickListener {
    EditText email1,pass1;
    Button loginbt;
    TextView text;
    String emailpatt="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Intent i;
    String str;
    //private static final String shp="";

    private FirebaseAuth mAuth;
    FirebaseUser muser;
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
        loginbt.setOnClickListener(this);
        text.setOnClickListener(this);
        p= new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View view) {
        if(view==text) {
            Intent i = new Intent(getApplicationContext(),registration.class);
            startActivity(i);
//            FirebaseAuth.getInstance().sendPasswordResetEmail(email1.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    Toast.makeText(getApplicationContext(),"Check your Email",Toast.LENGTH_LONG).show();
//                }
//            });
//            Toast.makeText(getApplicationContext(), "password reset Link Sent", Toast.LENGTH_LONG).show();
        }
        if(view==loginbt)
        {
//            i= new Intent(getApplicationContext(), MainActivity.class);
//            Toast.makeText(getApplicationContext(),"logging",Toast.LENGTH_LONG).show();
//            String email = email1.getText().toString();
//            String password = pass1.getText().toString();
//            if(email.equals("register@gmail.com") && password.equals("myregestration"))
//                startActivity(i);
//            if(!email.matches(emailpatt))
//            {
//                email1.setError("Enter Context Email");
//            }
//            else if(password.isEmpty()||password.length()<6) {
//                email1.setError("Enter Proper password");
//            }
//            else {
//                p.setMessage("Please wait Logging in");
//                p.setTitle("Login");
//                p.setCanceledOnTouchOutside(false);
//                p.show();
//                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            p.dismiss();
//                            Toast.makeText(getApplicationContext(), "Login sucessfull", Toast.LENGTH_SHORT).show();
//                            i = new Intent(getApplicationContext(), MainActivity.class);
//                            finish();
//                            startActivity(i);
//                        } else {
//                            Toast.makeText(getApplicationContext(), "" + task.getException(), Toast.LENGTH_SHORT).show();
//                            p.dismiss();
//                        }
//                    }
//                });
//            }
            i= new Intent(getApplicationContext(), main_menue.class);
            startActivity(i);
        }
    }
}