package com.example.novigrad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username ", "");
        //Toast.makeText(getApplicationContext(), "Welcome" + username, Toast.LENGTH_SHORT).show();


        // LOGOUT
        CardView exit = findViewById(R.id.cardExit);
        exit.setOnClickListener(new View.OnClickListener() { // retourner à la page de connexion
            @Override
            public void onClick(View v) { // on efface la mémoire temporaire et on retorune à la page de connexion
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(AdminHomeActivity.this, LoginActivity.class));
            }
        });


        // EMPLOYES
        CardView employes = findViewById(R.id.cardEmployes);
        employes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(AdminHomeActivity.this, ComptesActivity.class);
                it.putExtra("title", "Comptes Employés");
                startActivity(it);
            }
        });

        // SUCCURSALES
        CardView succursales = findViewById(R.id.cardSuccursales);
        succursales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AdminHomeActivity.this, ComptesActivity.class);
                it.putExtra("title", "Comptes Succursales");
                startActivity(it);
            }
        });

        // CLENTS
        CardView clients = findViewById(R.id.cardClients);
        clients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(AdminHomeActivity.this, ComptesActivity.class);
                it.putExtra("title", "Comptes Clients");
                startActivity(it);
            }
        });

        // SERVICES
        CardView services = findViewById(R.id.cardServices);
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AdminHomeActivity.this, ComptesActivity.class);
                it.putExtra("title", "Comptes Services");
                startActivity(it);
            }
        });

        // FORMULAIRES
        CardView formulaires = findViewById(R.id.cardFormulaire);
        formulaires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(AdminHomeActivity.this, ComptesActivity.class);
                it.putExtra("title", "Formulaires");
                startActivity(it);
            }
        });
    }
}