package com.example.samsungitproject.Authorization;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.samsungitproject.Authorization.Login.LoginActivity;
import com.example.samsungitproject.Main.MainActivity;
import com.example.samsungitproject.Main.Setting;
import com.example.samsungitproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_change_name extends AppCompatActivity {

    EditText Name, Surname;
    Button change;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name_and_surname);

        Name = findViewById(R.id.new_name);
        Surname = findViewById(R.id.new_surname);

        change = findViewById(R.id.btnChange_Name_Surname);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toConnect();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(activity_change_name.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
    public void toConnect() {
        String name = Name.toString().trim();
        String surname = Surname.toString().trim();

        //name;
        //    public String lastname;

        db = FirebaseDatabase.getInstance();
        DatabaseReference rootN =
                db.getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("name");
        rootN.setValue(name);

        db = FirebaseDatabase.getInstance();
        DatabaseReference rootS =
                db.getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("lastname");
        rootS.setValue(surname);

    }
}