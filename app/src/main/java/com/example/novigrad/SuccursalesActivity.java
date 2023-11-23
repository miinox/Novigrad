package com.example.novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class SuccursalesActivity extends AppCompatActivity {

    private EditText edNom, edAdresse;
    private CheckBox lu, ma, me, je, ve, sa;
    private Button btnBack, btnAdd, btnTimeO, btnTimeF;
    private TimePickerDialog timePickerDialogOuverture;
    private TimePickerDialog timePickerDialogFermeture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succursales);

        edNom = findViewById(R.id.editTextSuccursaleNom);
        edAdresse = findViewById(R.id.editTextSuccursaleAdresse);
        btnBack = findViewById(R.id.buttonBackSuccursale);
        btnAdd = findViewById(R.id.buttonAddSuccursale);
        btnTimeO = findViewById(R.id.buttonServiceTimeOuverture);
        btnTimeF = findViewById(R.id.buttonServiceTimeFermeture);
        lu = findViewById(R.id.checkBoxLundi);
        ma = findViewById(R.id.checkBoxMardi);
        me = findViewById(R.id.checkBoxMercredi);
        je = findViewById(R.id.checkBoxJeudi);
        ve = findViewById(R.id.checkBoxVendredi);
        sa = findViewById(R.id.checkBoxSamedi);

        CheckBox joursOuverture[] = {lu, ma, me, je, ve, sa};

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(SuccursalesActivity.this, ComptesActivity.class));
                Intent it = new Intent(SuccursalesActivity.this, ComptesActivity.class);
                it.putExtra("title", "Comptes Succursales");
                startActivity(it);
            }
        });

        //timepicker ouverture
        initTimePicker(btnTimeO);
        btnTimeO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialogOuverture.show();
            }
        });

        //timepicker fermeture
        initTimePicker(btnTimeF);
        btnTimeF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialogFermeture.show();
            }
        });

        // enregistrer la succursales
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = edNom.getText().toString();
                String adresse = edAdresse.getText().toString();
                ArrayList ouverture = new ArrayList<String>();
                String o = btnTimeO.getText().toString();
                String f = btnTimeF.getText().toString();
                String[] heures = {o, f};
                for(int i = 0; i < 6; i++) {
                    if(joursOuverture[i].isChecked()) {
                        String jour = joursOuverture[i].getText().toString();
                        ouverture.add(jour);
                    }
                }
                if(!nom.isEmpty()) {
                    Database db = new Database(getApplicationContext(), "novigrad", null, 1); // aller dans la base de données
                    Succursale succursale = new Succursale(nom, adresse, ouverture, heures);
                    db.addSuccursale(succursale);
                    //startActivity(new Intent(SuccursalesActivity.this, AdminHomeActivity.class));
                    Intent it = new Intent(SuccursalesActivity.this, ComptesActivity.class);
                    it.putExtra("title", "Comptes Succursales");
                    startActivity(it);
                } else {
                    Toast.makeText(getApplicationContext(), "Verifier les informations", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * Initialiser les heures
     * @param btn
     */
    private void initTimePicker(Button btn){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                btn.setText(hourOfDay+":"+minute);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR);
        int minutes = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        if (btn == btnTimeO) {
            timePickerDialogOuverture = new TimePickerDialog(this, style, timeSetListener, hours, minutes, true);
        } else if (btn == btnTimeF) {
            timePickerDialogFermeture = new TimePickerDialog(this, style, timeSetListener, hours, minutes, true);
        }
    }
}