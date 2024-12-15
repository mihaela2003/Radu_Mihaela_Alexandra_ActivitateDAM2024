package com.example.pregatire_test2_2;

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
import androidx.room.RoomDatabase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddQuote extends AppCompatActivity {
    private QuoteDataBase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_quote);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spCategory = findViewById(R.id.spinnerCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spCategory.setAdapter(adapter);

        database = Room.databaseBuilder(getApplicationContext(), QuoteDataBase.class, "Quotes.db").build();
        Intent it = getIntent();

        if(it.hasExtra("quote")){
            Quote quote = it.getParcelableExtra("quote");
            EditText ETQuote = findViewById(R.id.etQuote);
            EditText ETAuthor = findViewById(R.id.etAuthor);
            Spinner SPcategory = findViewById(R.id.spinnerCategory);
            EditText ETYear = findViewById(R.id.etYear);

            ETQuote.setText(quote.getQuote());
            ETAuthor.setText(quote.getAuthor());
            SPcategory.setSelection(((ArrayAdapter<CharSequence>) SPcategory.getAdapter()).getPosition(quote.getCategory()));
            ETYear.setText(""+quote.getYear());
        }

        Button btnAdd = findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etQuote = findViewById(R.id.etQuote);
                String quote = etQuote.getText().toString();
                EditText etAuthor = findViewById(R.id.etAuthor);
                String author = etAuthor.getText().toString();
                Spinner spcategory = findViewById(R.id.spinnerCategory);
                String category = spcategory.getSelectedItem().toString();
                EditText etYear = findViewById(R.id.etYear);
                String sYear = etYear.getText().toString();
                int year = Integer.parseInt(sYear);

                Quote quote1 = new Quote(quote, author, category, year);
                Toast.makeText(getApplicationContext(), quote1.toString(), Toast.LENGTH_LONG).show();

                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            FileOutputStream file = openFileOutput("obiecte.txt", MODE_APPEND);
                            OutputStreamWriter output = new OutputStreamWriter(file);
                            BufferedWriter writer = new BufferedWriter(output);
                            writer.write(quote1.toString());
                            writer.close();
                            output.close();
                            file.close();
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        database.daoQuote().insert(quote1);
                    }
                });

                Intent it = new Intent();
                it.putExtra("quote", quote1);
                setResult(RESULT_OK, it);
                finish();

            }
        });
    }
}