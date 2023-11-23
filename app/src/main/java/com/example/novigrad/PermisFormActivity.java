package com.example.novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PermisFormActivity extends AppCompatActivity {

    private Button btnBack, btnEnvoyer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permis_form);

        btnBack = findViewById(R.id.buttonFormPermisRetour);

        btnBack.setOnClickListener(new View.OnClickListener() { // retour
            @Override
            public void onClick(View v) {
                Intent it = new Intent(PermisFormActivity.this, ComptesActivity.class);
                it.putExtra("title", "Formulaires");
                startActivity(it);
            }
        });
    }
}