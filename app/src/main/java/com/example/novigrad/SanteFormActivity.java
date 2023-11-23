package com.example.novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SanteFormActivity extends AppCompatActivity {

    private Button btnBack, btnEnvoyer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sante_form);

        btnBack = findViewById(R.id.buttonFormPermisRetourSP);

        btnBack.setOnClickListener(new View.OnClickListener() { // retour
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SanteFormActivity.this, ComptesActivity.class);
                it.putExtra("title", "Formulaires");
                startActivity(it);
            }
        });

    }
}