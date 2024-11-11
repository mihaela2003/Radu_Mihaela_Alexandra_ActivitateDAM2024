package com.example.pregatire_test2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
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

        Spinner spinnerZile = findViewById(R.id.spinnerZile);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.zile,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerZile.setAdapter(adapter);

        Spinner spMaterie = findViewById(R.id.spinnerMaterie);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.materie,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spMaterie.setAdapter(adapter1);

        Button btn = findViewById(R.id.buttonA);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etSala = findViewById(R.id.etSala);
                String sala = etSala.getText().toString();
                EditText etNume = findViewById(R.id.etNume);
                String nume = etNume.getText().toString();
                Spinner spZile = findViewById(R.id.spinnerZile);
                String zile = spZile.getSelectedItem().toString();
                CheckBox cbOnline = findViewById(R.id.cbOnline);
                Boolean online = cbOnline.isChecked();
                Spinner spMaterie = findViewById(R.id.spinnerMaterie);
                String materie = spMaterie.getSelectedItem().toString();

                Orar orar = new Orar(sala, nume, zile, online, materie);
                Toast.makeText(getApplicationContext(), orar.toString(), Toast.LENGTH_LONG).show();

                Intent it = new Intent();
                it.putExtra("orar", orar);
                setResult(RESULT_OK, it);
                finish();

            }
        });
    }
}