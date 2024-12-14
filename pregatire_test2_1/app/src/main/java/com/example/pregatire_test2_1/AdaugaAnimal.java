package com.example.pregatire_test2_1;

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

public class AdaugaAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_animal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spGen = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.gen,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spGen.setAdapter(adapter);

        Button btnAdaugaAnimal = findViewById(R.id.btnAdaugaAnimal);
        btnAdaugaAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etStapan = findViewById(R.id.etStapan);
                String stapan = etStapan.getText().toString();
                EditText etNume = findViewById(R.id.etNumeAnimal);
                String nume = etNume.getText().toString();
                EditText etRasa = findViewById(R.id.etrasa);
                String rasa = etRasa.getText().toString();
                Spinner spggen = findViewById(R.id.spinner);
                String gen = spGen.getSelectedItem().toString();

                Animal animal = new Animal(stapan,nume, rasa, gen);
                Toast.makeText(getApplicationContext(),animal.toString(), Toast.LENGTH_LONG).show();

                Intent it = new Intent();
                it.putExtra("animal", animal);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}