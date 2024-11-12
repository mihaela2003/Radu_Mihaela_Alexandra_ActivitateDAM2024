package com.example.pregatire_test4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Adauga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spFirma = findViewById(R.id.spinnerFirma);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.spFirma,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spFirma.setAdapter(adapter);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etNume = findViewById(R.id.etNume);
                String nume = etNume.getText().toString();
                Spinner spFirma = findViewById(R.id.spinnerFirma);
                String firma = spFirma.getSelectedItem().toString();
                EditText etAn = findViewById(R.id.etAn);
                String sAn = etAn.getText().toString();
                int an = Integer.parseInt(sAn);
                EditText etSalariu = findViewById(R.id.etSalariu);
                String sSalariu = etSalariu.getText().toString();
                float salariu = Float.parseFloat(sSalariu);

                Angajat ang = new Angajat(nume, firma, an, salariu);
                Toast.makeText(getApplicationContext(), ang.toString(), Toast.LENGTH_LONG).show();

                Intent it = new Intent();
                it.putExtra("angajat", ang);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }

}