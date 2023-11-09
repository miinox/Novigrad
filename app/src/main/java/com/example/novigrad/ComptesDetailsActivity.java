package com.example.novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.BaseKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ComptesDetailsActivity extends AppCompatActivity {

    private Button btnSave, btnChange, btnDelete;
    private EditText edDetails;
    private TextView tvCDName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comptes_details);


        btnSave = findViewById(R.id.buttonCDSave);
        btnDelete = findViewById(R.id.buttonCDDelete);
        btnChange = findViewById(R.id.buttonCDChange);
        edDetails = findViewById(R.id.editTextCDTextMultiline);
        tvCDName = findViewById(R.id.textViewCDPackageName);


        Intent intent = getIntent();
        String title = intent.getStringExtra("text1");
        String name = intent.getStringExtra("text2") ;
        String email = intent.getStringExtra("text3") ;

        if(title.equals("Comptes Clients")) {
            btnChange.setVisibility(View.INVISIBLE);
        }

        edDetails.setText(intent.getStringExtra("text2") + "\n" + intent.getStringExtra("text3"));
        //tvCDName.setText(intent.getStringExtra("text1"));
        tvCDName.setText(title);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComptesDetailsActivity.this, ComptesActivity.class));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext(), "novigrad", null, 1);
                db.removeProfil(name); // effacer dans la table
                startActivity(new Intent(ComptesDetailsActivity.this, AdminHomeActivity.class));
            }
        });

    }
}