package com.example.novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ComptesActivity extends AppCompatActivity {

    private TextView tv;
    private Button btn;
    private String [][] comptes_details = {};
    ArrayList list;
    HashMap<String, String> item;
    SimpleAdapter sa;
    private ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comptes);

        tv = findViewById(R.id.textViewComptesTitle);
        btn = findViewById(R.id.buttonComptesBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        Database db = new Database(getApplicationContext(), "novigrad", null, 1); // aller dans la base de données
        ArrayList dbData;
        switch (title) {
            case "Comptes Clients":
                dbData = db.getRegisterData("false"); // récuréper que les clients
                break;
            case "Comptes Employés":
                dbData = db.getRegisterData("true"); // récuréper que les clients
                //doctor_details = doctor_details2;
                break;
            case "Comptes Succursales ":
                dbData = db.getRegisterData("false"); // récuréper que les clients
                //doctor_details = doctor_details3;
                break;
            case "Comptes Services":
                dbData = db.getRegisterData("false"); // récuréper que les clients
                //doctor_details = doctor_details4;
                break;
            default:
                dbData = db.getRegisterData("false"); // récuréper que les clients
                //doctor_details = doctor_details5;

        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComptesActivity.this, AdminHomeActivity.class));
            }
        });


        comptes_details = new String[dbData.size()][];
        for(int i = 0; i < comptes_details.length; i++) {
            comptes_details[i] = new String[2];
        }

        for(int i = 0; i < dbData.size(); i++) {
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            comptes_details[i][0] = strData[0];
            comptes_details[i][1] = strData[1];
        }
        list = new ArrayList();
        for(int i = 0; i < comptes_details.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", comptes_details[i][0]);
            item.put("line2", comptes_details[i][1]);
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c}
        );

        ListView lst = findViewById(R.id.listViewComptes);
        lst.setAdapter(sa);

        // on a cliqué sur la liste
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(ComptesActivity.this, ComptesDetailsActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2", comptes_details[position][0]);
                it.putExtra("text3", comptes_details[position][1]);
//                it.putExtra("text4", comptes_details[position][2]);
//                it.putExtra("text5", comptes_details[position][4]);
                startActivity(it);
            }
        });
    }
}