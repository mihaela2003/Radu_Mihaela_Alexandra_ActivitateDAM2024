package com.example.pregatire_test2_3;

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
import androidx.room.Room;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddCelebrity extends AppCompatActivity {
    CelebrityDataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_celebrity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinner = findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getApplicationContext(),
                R.array.gender,
                android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        database = Room.databaseBuilder(getApplicationContext(), CelebrityDataBase.class, "Celebrities.db").build();
        Intent it = getIntent();
        if(it.hasExtra("celebrity")){
            Celebrity celebrity = it.getParcelableExtra("celebrity");
            EditText etName = findViewById(R.id.etName);
            Spinner spGender = findViewById(R.id.spinnerGender);
            EditText etNationality = findViewById(R.id.etNationality);
            EditText etOccupation = findViewById(R.id.etOccupation);
            EditText etHeight = findViewById(R.id.etHeight);
            EditText etBirthday = findViewById(R.id.etBirthday);

            etName.setText(celebrity.getName());
            spGender.setSelection(((ArrayAdapter<CharSequence>)spGender.getAdapter()).getPosition(celebrity.getGender()));
            etNationality.setText(celebrity.getNationality());
            etOccupation.setText(celebrity.getOccupation());
            etHeight.setText(""+celebrity.getHeight());
            etBirthday.setText(celebrity.getBirthday());
        }

        Button btnAdd = findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etName = findViewById(R.id.etName);
                String name = etName.getText().toString();
                Spinner spGender = findViewById(R.id.spinnerGender);
                String gender = spGender.getSelectedItem().toString();
                EditText etNationality = findViewById(R.id.etNationality);
                String nationality = etNationality.getText().toString();
                EditText etOccupation = findViewById(R.id.etOccupation);
                String occupation = etOccupation.getText().toString();
                EditText etHeight = findViewById(R.id.etHeight);
                String sHeight = etHeight.getText().toString();
                float height = Float.parseFloat(sHeight);
                EditText etBirthday = findViewById(R.id.etBirthday);
                String birthday = etBirthday.getText().toString();

                Celebrity celebrity = new Celebrity(name, gender, nationality, occupation, height, birthday);
                Toast.makeText(getApplicationContext(), celebrity.toString(), Toast.LENGTH_LONG).show();

                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoCelebrity().insert(celebrity);
                    }
                });

                Intent it = new Intent();
                it.putExtra("celebrity", celebrity);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}