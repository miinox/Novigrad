package com.example.novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ServicesActivity extends AppCompatActivity {

    private EditText edNom, edDesription;
    private Button btnBack, btnAdd;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        edNom = findViewById(R.id.editTextServiceNom);
        edDesription = findViewById(R.id.editTextServiceDescription);
        btnAdd = findViewById(R.id.buttonAddServce);
        btnBack = findViewById(R.id.buttonBackService);
        edNom.getText().clear();
        edDesription.getText().clear();
        tv = findViewById(R.id.textViewService);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String nomService = intent.getStringExtra("nom") ;
        String descriptionService = intent.getStringExtra("description");
        if(title.equals("Changer Service")) {
            tv.setText(title);
            btnAdd.setText("Modifier");
            edNom.setText(nomService);
            edDesription.setText(descriptionService);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ServicesActivity.this, AdminHomeActivity.class));
                Intent it = new Intent(ServicesActivity.this, ComptesActivity.class);
                it.putExtra("title", "Comptes Services");
                startActivity(it);
            }
        });

        // ADD OR CHANGE
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext(), "novigrad", null, 1);
                if(edNom.getText().toString().isEmpty()) { // empécher de créer un service sans nom
                    Toast.makeText(getApplicationContext(), "Champs vide", Toast.LENGTH_SHORT).show();
                } else {
                    if(btnAdd.getText().toString().equals("Modifier")) {
                        // le code pour modifier dans la base de données
                        String nouveauNom = edNom.getText().toString();
                        String nouvelleDescription = edDesription.getText().toString();
                        boolean MAJ = db.updateServiceData(nomService, nouveauNom, nouvelleDescription);
                        Toast.makeText(getApplicationContext(), "Non incorrect!", Toast.LENGTH_SHORT).show();

                    } else { // ajouter une nouvelle données dans la base
                        // aller dans la base de données
                        Service nouveau = new Service(edNom.getText().toString(), edDesription.getText().toString());
                        db.addService(nouveau.getTitle(), nouveau.getDescription());
                    }
                }
                //startActivity(new Intent(ServicesActivity.this, AdminHomeActivity.class));
                Intent it = new Intent(ServicesActivity.this, ComptesActivity.class);
                it.putExtra("title", "Comptes Services");
                startActivity(it);
            }
        });

    }
}