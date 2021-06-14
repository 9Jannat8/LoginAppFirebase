package com.example.loginappfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText signInEmailText, signInPasswordText;
    private Button signInButton;
    private TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Sign In Activity");

        signInEmailText = findViewById(R.id.signInUserNameEditTextId);
        signInPasswordText = findViewById(R.id.signInPasswordEditTextId);

        signInButton = findViewById(R.id.signInButtonId);
        signUpTextView = findViewById(R.id.signUpTextViewId);

        signInButton.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.signInButtonId:

                break;

            case R.id.signUpTextViewId:
                Intent intent = new Intent(getApplicationContext(), SignUpFirebase.class);
                startActivity(intent);
                break;
        }
    }
}