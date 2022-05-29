package com.example.samsungitproject.Authorization.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samsungitproject.Authorization.Register.RegisterActivity;
import com.example.samsungitproject.Authorization.activity_changePassword;
import com.example.samsungitproject.Main.MainActivity;
import com.example.samsungitproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView tvtoReg, tvtoCh;
    Button btnRegister_l;
    EditText edit_Password_l;
    EditText edit_Email_l;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_Email_l = findViewById(R.id.Email_l);
        edit_Password_l = findViewById(R.id.Password_l);
        btnRegister_l = findViewById(R.id.btnSignup_l);
        tvtoReg = findViewById(R.id.tvToReg);
        mAuth = FirebaseAuth.getInstance();
        tvtoCh = findViewById(R.id.tvToChange);

        tvtoCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, activity_changePassword.class);
                startActivity(intent);
            }
        });

        tvtoReg.setOnClickListener(view -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

        btnRegister_l.setOnClickListener(view -> {
            loginUser();
        });
    }
    private void loginUser(){
        String email = edit_Email_l.getText().toString();
        String password = edit_Password_l.getText().toString();


        if (email.isEmpty()) {
            edit_Email_l.setError("Email cannot be empty");
            edit_Email_l.requestFocus();
        }
        else if (password.isEmpty()) {
            edit_Password_l.setError("Password cannot be empty");
            edit_Password_l.requestFocus();
        }

        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "User logged succesfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Error Log in!!" + task.getException().getMessage() , Toast.LENGTH_LONG).show();

                    }

                }
            });
        }




    }
}