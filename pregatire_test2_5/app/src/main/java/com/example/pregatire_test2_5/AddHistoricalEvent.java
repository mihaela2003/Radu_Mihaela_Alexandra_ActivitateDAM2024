package com.example.pregatire_test2_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddHistoricalEvent extends AppCompatActivity {
    HistoricalEventDataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_historical_event);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        database = Room.databaseBuilder(getApplicationContext(), HistoricalEventDataBase.class, "HistoricalEvent.db").build();

        Intent it = getIntent();
        if(it.hasExtra("he")){
            HistoricalEvent he = it.getParcelableExtra("he");
            EditText etYear = findViewById(R.id.etYear);
            EditText etMonth = findViewById(R.id.etMonth);
            EditText etDay = findViewById(R.id.etDay);
            EditText etEvent = findViewById(R.id.etEvent);

            etYear.setText(""+he.getYear());
            etMonth.setText(""+he.getMonth());
            etDay.setText(""+he.getDay());
            etEvent.setText(he.getEvent());
        }

        Executor executor = Executors.newSingleThreadExecutor();

        Button btn = findViewById(R.id.buttonAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etYear = findViewById(R.id.etYear);
                String sYear = etYear.getText().toString();
                int year = Integer.parseInt(sYear);
                EditText etMonth = findViewById(R.id.etMonth);
                String sMonth = etMonth.getText().toString();
                int month = Integer.parseInt(sMonth);
                EditText etDay = findViewById(R.id.etDay);
                String sDay = etDay.getText().toString();
                int day = Integer.parseInt(sDay);
                EditText etEvent = findViewById(R.id.etEvent);
                String event = etEvent.getText().toString();

                HistoricalEvent he = new HistoricalEvent(year, month, day, event);
                Toast.makeText(getApplicationContext(), he.toString(), Toast.LENGTH_LONG).show();

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoHE().insert(he);
                    }
                });

                Intent it = new Intent();
                it.putExtra("he", he);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}