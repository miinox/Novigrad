package com.example.novigrad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SuccursalesActivity extends AppCompatActivity {

    private String[][] succursales =
            {
                    {"Succursale 1", "Full Body Checkup", "", "", "999"},
                    {"Succursale 2", "Blood Glucose", "", "", "299"},
                    {"Succursale 3", "COVID-19 Antibody", "", "", "899"},
                    {"Succursale 4", "Thyroid Check", "", "", "499"},
                    {"Succursale 4", "Immunity check", "", "", "699"}
            };

    private String[] succursales_detail = {
            "SuccOttawa\n" +
                    " Complete Hemogram\n" +
                    "HbA1c\n" +
                    " Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "LDH Lactate Dehydrogenase, Sereum\n" +
                    "Liquid Profile\n" +
                    "Liver Function Test",
            "Su Heron, Permis de conduire Fasting",
            "Succ#157 Photo seulement - IgG",
            "CLOSE till...",
            "Services en ligne seulement Hemogram\n" +
                    "Permis de conduire\n" +
                    "Carte santé\n" +
                    "Carte photo\n" +
                    "...\n" +
                    "...\n" +
                    "..."
    };

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart, btnBack;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succursales);

        btnGoToCart = findViewById(R.id.buttonLTGoToCart);
        btnBack = findViewById(R.id.buttonSCBack);
        listView = findViewById(R.id.listViewSC);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SuccursalesActivity.this, AdminHomeActivity.class));
            }
        });
        list = new ArrayList();
        for(int i = 0; i < succursales.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", succursales[i][0]);
            item.put("line2", succursales[i][1]);
            item.put("line3", succursales[i][2]);
            item.put("line4", succursales[i][3]);
            item.put("line5", "Total Cost:" + succursales[i][4] + "$");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(SuccursalesActivity.this, SuccursalesDetailsActivity.class);
                it.putExtra("text1", succursales[position][0]);
                it.putExtra("text2", succursales_detail[position]);
                it.putExtra("text3", succursales[position][4]);
                startActivity(it);
            }
        });
    }
}