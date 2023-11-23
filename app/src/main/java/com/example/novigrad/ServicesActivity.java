package com.example.novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ServicesActivity extends AppCompatActivity {

    private EditText edNom, edDesription;
    private Button btnBack, btnAdd;
    private TextView tv;
    private Spinner spinner;
    private String descriptionService;
    private boolean etat = false;
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
        spinner = findViewById(R.id.spinnerDropdownService);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String nomService = intent.getStringExtra("nom") ;
        descriptionService = intent.getStringExtra("description");
        String formulaire = intent.getStringExtra("formulaire");

        if(title.equals("Changer service")) {
            tv.setText(title);
            btnAdd.setText("Modifier");
            edNom.setText(nomService);
            edDesription.setText(descriptionService);
        }
        // Menu deroulant pour les formulaires
        String[] items = new String[]{"-","Permis de conduire", "Carte santé", "Carte d'identité"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_text_color, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if (adapter != null) {
            for (int i = 0; i < adapter.getCount(); i++) {
                if (adapter.getItem(i).equals(formulaire)) {
                    spinner.setSelection(i); // Sélectionner l'élément correspondant
                    break;
                }
            }
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                String text = spinner.getSelectedItem().toString();
                String message;
                if(text.equals("-")) {
                    if(etat == false) {
                        edDesription.setEnabled(true);
                        edDesription.setText(descriptionService);
                        etat = true;
                    }
                } else {
                    if(etat == true){
                        etat = false;
                        descriptionService = edDesription.getText().toString();
                    }
                    edDesription.setEnabled(false);
                    edDesription.setText(type(text));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Action à effectuer si aucun élément n'est sélectionné
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
//                         le code pour modifier dans la base de données
                        String nouveauForm = spinner.getSelectedItem().toString();
                        String nouveauNom = edNom.getText().toString();
                        String nouvelleDescription = edDesription.getText().toString();
                        boolean MAJ = db.updateServiceData(nomService, nouveauNom, nouvelleDescription, nouveauForm);
//                        Toast.makeText(getApplicationContext(), "Non incorrect!", Toast.LENGTH_SHORT).show();

                    } else { // ajouter une nouvelle données dans la base
                        // aller dans la base de données
                        String form = spinner.getSelectedItem().toString();
                        Service nouveau = new Service(edNom.getText().toString(), edDesription.getText().toString(), form);
                        //Toast.makeText(getApplicationContext(), form, Toast.LENGTH_SHORT).show();
                        db.addService(nouveau.getTitle(), nouveau.getDescription(), nouveau.getFormulaire());
                    }
                    Intent it = new Intent(ServicesActivity.this, ComptesActivity.class);
                    it.putExtra("title", "Comptes Services");
                    startActivity(it);
                }
            }
        });


    }

    public String type(String nom) {

        if (!nom.equals("-")) {
            String doc = "Requis" +
                    "\n- Nom"  +
                    "\n- Prénom" +
                    "\n- Date de naissance" +
                    "\n- Adresse" +
                    "\n- Preuve de domicile";

            if(nom.equals("Permis de conduire")) {
                doc += "\n- Type de permis (G1. G2, G)";
            }
            if(nom.equals("Carte santé")) {
                doc += "\n- Preuve de statut";
            }
            if(nom.equals("Carte d'identité")) {
                doc += "\n- Photo du client";
            }
            return doc;
        }
        return "-";
    }
}