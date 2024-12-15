package com.example.pregatire_test2_4;

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

public class AddTrivia extends AppCompatActivity {
    TriviaDataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_trivia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Spinner spinner = findViewById(R.id.spCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.category, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        database = Room.databaseBuilder(getApplicationContext(), TriviaDataBase.class, "Trivias.db").build();
        Intent it = getIntent();

        if(it.hasExtra("trivia")){
            Trivia trivia = it.getParcelableExtra("trivia");
            Spinner spCategory = findViewById(R.id.spCategory);
            EditText etQuestion = findViewById(R.id.etQuestion);
            EditText etAnswer = findViewById(R.id.etAnswer);

            spCategory.setSelection(((ArrayAdapter<CharSequence>)spCategory.getAdapter()).getPosition(trivia.getCategory()));
            etQuestion.setText(trivia.getQuestion());
            etAnswer.setText(trivia.getAnswer());
        }


        Executor executor = Executors.newSingleThreadExecutor();

        Button btnAdd = findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spinner spCategory = findViewById(R.id.spCategory);
                String category = spCategory.getSelectedItem().toString();
                EditText etQuestion = findViewById(R.id.etQuestion);
                String question = etQuestion.getText().toString();
                EditText etAnswer = findViewById(R.id.etAnswer);
                String answer = etAnswer.getText().toString();

                Trivia trivia = new Trivia(category,question, answer);
                Toast.makeText(getApplicationContext(), trivia.toString(), Toast.LENGTH_LONG).show();

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoTrivia().insert(trivia);
                    }
                });

                Intent it = new Intent();
                it.putExtra("trivia", trivia);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}