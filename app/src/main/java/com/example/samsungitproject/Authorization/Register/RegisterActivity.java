package com.example.samsungitproject.Authorization.Register;

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
import com.example.samsungitproject.Authorization.User;
import com.example.samsungitproject.Main.MainActivity;
import com.example.samsungitproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    TextView tvtoLogin;
    Button btn_Register_r;
    EditText Email_r;
    EditText Password_r;
    EditText Name_r, LastName_r;

    FirebaseAuth mAuth;

    String emailPatern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvtoLogin = findViewById(R.id.tvToLogin);
        btn_Register_r = findViewById(R.id.btnRegister_r);
        Email_r = findViewById(R.id.Email_r);
        Password_r = findViewById(R.id.Password_r);
        Name_r = findViewById(R.id.Name_r);
        LastName_r = findViewById(R.id.Surname_r);
//
        mAuth = FirebaseAuth.getInstance();
        btn_Register_r.setOnClickListener(view -> {
            createUser();
        });

        tvtoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLoginActivity();
            }
        });
    }

    private void toLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void createUser() {
        String currency = "bitcoin";
        String interval = "1";
        String course_1 = "bitcoin";
        String course_2 = "bitcoin";
        String course_3 = "bitcoin";
        String name = Name_r.getText().toString();
        String email = Email_r.getText().toString();
        String password = Password_r.getText().toString();
        String lastname = LastName_r.getText().toString();


        if (email.isEmpty()) {
            Email_r.setError("Email cannot be empty");
            Email_r.requestFocus();
        }

        else if(!email.matches(emailPatern)){
            Email_r.setError("Email not correct");
            Email_r.requestFocus();

        }
        else if (password.isEmpty()) {
            Password_r.setError("Password cannot be empty");
            Password_r.requestFocus();
        }
        else if (name.isEmpty()) {
            Name_r.setError("Name cannot be empty");
            Name_r.requestFocus();
        }
        else if (lastname.isEmpty()){
            LastName_r.setError("Last name cannot be empty");
            LastName_r.requestFocus();
        }

//        else if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            Email_r.setError("Please provide valid email!");
//            Email_r.requestFocus();
//            return;
//        }


//        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            edit_Email_r.setError("Provide valid email");
//            edit_Email_r.requestFocus();
//            return;
//        }

        else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        User user = new User(name, lastname, email, password, currency, interval, course_1, course_2, course_3);

                        FirebaseDatabase database = FirebaseDatabase.getInstance();

                        FirebaseDatabase.getInstance().getReference("users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {


                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            }
                        });
                        Toast.makeText(RegisterActivity.this, "User registered succesfully", Toast.LENGTH_LONG).show();
                        // startActivity(new Intent(Activity_Register.this, Activity_Login.class));

                    } else {
                        Toast.makeText(RegisterActivity.this, "Error registration!!" + task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    }

                }
            });

        }
    }
}