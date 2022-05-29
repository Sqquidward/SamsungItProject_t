package com.example.samsungitproject.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samsungitproject.Authorization.Login.LoginActivity;
import com.example.samsungitproject.Authorization.User;
import com.example.samsungitproject.Authorization.activity_changePassword;
import com.example.samsungitproject.Authorization.activity_change_name;
import com.example.samsungitproject.Graphic.ApiClient_graph;
import com.example.samsungitproject.Graphic.Models.graphic;
import com.example.samsungitproject.R;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Setting extends AppCompatActivity {

    Button change_Password, exit, change_name, change_surname;
    FirebaseAuth mAuth;
    TextView name , email, surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        exit = findViewById(R.id.exit);
        change_Password = findViewById(R.id.change_password);

        change_name = findViewById(R.id.change_name);
        change_surname = findViewById(R.id.change_surname);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Setting.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        change_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Setting.this, activity_changePassword.class);
                startActivity(intent);
            }
        });

        change_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting.this, activity_change_name.class);
                startActivity(intent);
            }
        });

        change_surname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting.this, activity_change_name.class);
                startActivity(intent);
            }
        });


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        name = findViewById(R.id.setting_name);
        surname = findViewById(R.id.setting_surname);
        email = findViewById(R.id.setting_email);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user!=null){
                    name.setText(user.name);
                    surname.setText(user.lastname);
                    email.setText(user.email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}