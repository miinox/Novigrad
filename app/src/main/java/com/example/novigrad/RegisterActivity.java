package com.example.novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText edUsername, edEmail, edPassword, edConfirm;
    private Button btn;
    private TextView tv;
    private CheckBox chkEmploye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init(); // initialisation

        // le bouton s'enregistrer est pressé
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                boolean role = chkEmploye.isChecked(); // 1 = employé, 0 = client
                Database db = new Database(getApplicationContext(), "novigrad", null, 1); // création de la database "novigrad"

                // s'assurer que le champ n'est pas vide
                if(username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill All details", Toast.LENGTH_LONG).show();

                } else { // même mot de passe
                    if(password.equals(confirm)) {
                        if(isValid(password)) { // vérifier que le password est valide
                            //**** ENREGRISTREMENT DANS LA BASE DE DONNEES ****//
                            db.register(username, email, password, String.valueOf(role));
                            Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class)); // aller au login
                        } else { // afficher message d'erreur
                            Toast.makeText(RegisterActivity.this, "Password must contain at least 8 characters, having letter, digit and speacial caractere", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password and confirm didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // L'utilisateur a déjà un compte existant
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // aller à la page de connexion
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
}

    /**
     * Initialistion des E/S
     */
private void init() {
    edUsername = findViewById(R.id.editTextRegUsername);
    edPassword = findViewById(R.id.editTextRegPassword);
    edEmail = findViewById(R.id.editTextAppAdress);
    edConfirm = findViewById(R.id.editTextRegConfirmPassword);
    btn= findViewById(R.id.buttonRegister);
    tv = findViewById(R.id.textViewExistingUser);
    chkEmploye = findViewById(R.id.checkBoxEmploye);

    edUsername.getText().clear();
    edPassword.getText().clear();
    edEmail.getText().clear();
    edConfirm.getText().clear();
}

    /**
     * Validité du password, il doit respecter les conditions
     * @param password
     * @return
     */
    private static boolean isValid(String password) {
        boolean condition1 = false, condition2 = false, condition3 = false;
        if (password.length() < 8) { // pas assez de caractères
            return false;
        } else {
            // au moins une lettre
            for (int i = 0; i < password.length(); i++) {
                if (Character.isLetter(password.charAt(i))) {
                    condition1 = true;
                }
            }
            // au moins un chiffre
            for (int j = 0; j < password.length(); j++) {
                if (Character.isDigit(password.charAt(j))) {
                    condition2 = true;
                }
            }
            // au moins un caractère spécial
            for (int k = 0; k < password.length(); k++) {
                char c = password.charAt(k);
                if (c >= 33 && c <= 46 || c == 64) {
                    condition3 = true;
                }
            }
            if (condition1 && condition2 && condition3) { // toutes les conditions sont réunies
                return true;
            }
        }
        return false;
    }
}