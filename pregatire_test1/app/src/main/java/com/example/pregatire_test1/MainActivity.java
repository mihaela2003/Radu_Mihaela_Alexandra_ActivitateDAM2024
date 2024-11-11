package com.example.pregatire_test1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {
    private List<Student> stud = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        stud = it.getParcelableArrayListExtra("listaStudenti");

        ListView lvStudenti = findViewById(R.id.studLV);
        ArrayAdapter<Student> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, stud);
        lvStudenti.setAdapter(adapter);

        lvStudenti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), stud.get(i).toString(),Toast.LENGTH_LONG).show();
            }
        });
        lvStudenti.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                stud.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("activitate", "s-a apelat onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, R.string.mesaj,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("activitate", "s-a apelat onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("activitate", "s-a apelat onStop");
    }

    @Override
    protected void onDestroy() {
        Log.i("activitate", "s-a apelat onDestroy");
        super.onDestroy();
    }
}