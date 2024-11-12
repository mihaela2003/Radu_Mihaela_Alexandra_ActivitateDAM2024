package com.example.pregatire_test3;

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

        Spinner spEnt = findViewById(R.id.spinnerEnt);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.spEnt,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spEnt.setAdapter(adapter);

        Spinner spMembri = findViewById(R.id.spinnerMembri);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.spMembri,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spMembri.setAdapter(adapter1);

        Button btn = findViewById(R.id.buttonA);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etDenumire = findViewById(R.id.etDenumire);
                String denumire = etDenumire.getText().toString();
                Spinner spEnt = findViewById(R.id.spinnerEnt);
                String ent = spEnt.getSelectedItem().toString();
                Spinner spMembri = findViewById(R.id.spinnerMembri);
                String sMembri = spMembri.getSelectedItem().toString();
                int nrMembri = Integer.parseInt(sMembri);
                CheckBox cbSolo = findViewById(R.id.cbSolo);
                boolean solo = cbSolo.isChecked();
                EditText etSalariu = findViewById(R.id.etSalariu);
                String sSalariu = etSalariu.getText().toString();
                float salariu = Float.parseFloat(sSalariu);

                kpop k = new kpop(denumire,salariu,solo, nrMembri, ent);
                Toast.makeText(getApplicationContext(), k.toString(), Toast.LENGTH_LONG).show();

                Intent it = new Intent();
                it.putExtra("kpop", k);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}