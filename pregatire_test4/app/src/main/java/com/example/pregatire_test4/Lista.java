package com.example.pregatire_test4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Lista extends AppCompatActivity {
    List<Angajat> angajati = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        angajati = it.getParcelableArrayListExtra("angajat");


        ListView lvAngajat = findViewById(R.id.lvAngajat);
        ArrayAdapter<Angajat> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, angajati);
        lvAngajat.setAdapter(adapter);

        lvAngajat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), angajati.get(i).toString(), Toast.LENGTH_LONG).show();
            }
        });

        lvAngajat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                angajati.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}