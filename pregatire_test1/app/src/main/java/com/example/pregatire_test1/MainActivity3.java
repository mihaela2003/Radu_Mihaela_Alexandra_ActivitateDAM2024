package com.example.pregatire_test1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn = findViewById(R.id.buttonAdauga);

        Spinner spRestanta = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.restanta,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spRestanta.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etNume = findViewById(R.id.etNume);
                String nume = etNume.getText().toString();
                EditText etFacultate = findViewById(R.id.etFacultate);
                String facultate = etFacultate.getText().toString();
                EditText etAn = findViewById(R.id.etAnNastere);
                String sAn = etAn.getText().toString();
                int an = Integer.parseInt(sAn);
                EditText etMedie = findViewById(R.id.etMedie);
                String sMedie = etMedie.getText().toString();
                float medie = Float.parseFloat(sMedie);
                Spinner sRestant = findViewById(R.id.spinner);
                String restant = sRestant.getSelectedItem().toString();

                Student stud = new Student(an,nume,facultate, restant, medie);
                Toast.makeText(MainActivity3.this, stud.toString(), Toast.LENGTH_LONG).show();

                Intent it = new Intent();
                it.putExtra("student", stud);
                setResult(RESULT_OK, it);
                finish();
            }
        });

       // Intent it = getIntent();
        //int val1 = it.getIntExtra("numar1", 0);
        //int val2 = it.getIntExtra("numar2", 0);
        //Toast.makeText(getApplicationContext(), "suma este:"+(val1+val2), Toast.LENGTH_LONG).show();
    }
}