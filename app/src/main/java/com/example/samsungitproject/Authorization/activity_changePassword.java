package com.example.samsungitproject.Authorization;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samsungitproject.Authorization.Login.LoginActivity;
import com.example.samsungitproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class activity_changePassword extends AppCompatActivity {

    TextView toLogin;
    Button Ch_password;
    EditText editText;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        toLogin = findViewById(R.id.tvToLogChange);
        Ch_password = findViewById(R.id.btnChangePassword);
        editText = findViewById(R.id.Email_change);

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_changePassword.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        Ch_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

        auth = FirebaseAuth.getInstance();


    }

    private void resetPassword() {
        String email = editText.getText().toString().trim();

        if (email.isEmpty()){
            editText.setError("Cannot be empty!");
            editText.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText.setError("Please provide valid email!");
            editText.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(activity_changePassword.this, "Check your email to reset your password", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(activity_changePassword.this, "Try again!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}