package com.example.loginappfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFirebase extends AppCompatActivity implements View.OnClickListener {

    private EditText signUpEmailText, signUpPasswordText;
    private Button signUpButton;
    private TextView signInTextView;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_firebase);
        this.setTitle("Sign Up Activity");

        mAuth = FirebaseAuth.getInstance();

        signUpEmailText = findViewById(R.id.signUpUserNameEditTextId);
        signUpPasswordText = findViewById(R.id.signUpPasswordEditTextId);

        progressBar = findViewById(R.id.progressBarId);

        signUpButton = findViewById(R.id.signUpButtonId);
        signInTextView = findViewById(R.id.signInTextViewId);

        signUpButton.setOnClickListener(this);
        signInTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.signUpButtonId:
                userRegister();
                break;

            case R.id.signInTextViewId:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void userRegister() {
        String email = signUpEmailText.getText().toString().trim();
        String password = signUpPasswordText.getText().toString().trim();//trim() is used for ignore blank space

        //checking the validity of the email
        if(email.isEmpty())
        {
            signUpEmailText.setError("Enter an email address");
            signUpEmailText.requestFocus();// to bring cursor on point
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())//email address a jodi @ sign na dey thle ata kaj korbe
        {
            signUpEmailText.setError("Enter a valid email address");
            signUpEmailText.requestFocus();
            return;
        }


        //checking the validity of the password
        if(password.isEmpty())
        {
            signUpPasswordText.setError("Enter a password");
            signUpPasswordText.requestFocus();
            return;
        }

        if (password.length() < 6)
        {
            signUpPasswordText.setError("Minimum length of a password should be 6");
            signUpPasswordText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Register is successful", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Register is not successful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}