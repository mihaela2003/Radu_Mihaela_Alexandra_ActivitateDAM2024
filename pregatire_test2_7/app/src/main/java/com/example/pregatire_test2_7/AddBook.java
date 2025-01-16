package com.example.pregatire_test2_7;

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

public class AddBook extends AppCompatActivity {
    BookDataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinner = findViewById(R.id.spinnerGenre);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.genre, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        database = Room.databaseBuilder(getApplicationContext(), BookDataBase.class, "Books.db").build();
        Executor executor = Executors.newSingleThreadExecutor();

        Intent it = getIntent();
        if(it.hasExtra("book")){
            Book book = it.getParcelableExtra("book");
            EditText eta = findViewById(R.id.etAuthor);
            EditText ety = findViewById(R.id.etYear);
            EditText ett = findViewById(R.id.etTitle);
            EditText eti = findViewById(R.id.etIsbn);
            Spinner spg = findViewById(R.id.spinnerGenre);

            eta.setText(book.getAuthor());
            ety.setText(""+book.getYear());
            ett.setText(book.getTitle());
            eti.setText(book.getIsbn());
            spg.setSelection(((ArrayAdapter<CharSequence>)spg.getAdapter()).getPosition(book.getGenre()));
        }

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText eta = findViewById(R.id.etAuthor);
                String author = eta.getText().toString();
                EditText ety = findViewById(R.id.etYear);
                String sy = ety.getText().toString();
                int year = Integer.parseInt(sy);
                EditText ett = findViewById(R.id.etTitle);
                String title = ett.getText().toString();
                EditText eti = findViewById(R.id.etIsbn);
                String isbn = eti.getText().toString();
                Spinner spg = findViewById(R.id.spinnerGenre);
                String genre = spg.getSelectedItem().toString();

                Book book = new Book(author, year, title, isbn, genre);
                Toast.makeText(getApplicationContext(), book.toString(), Toast.LENGTH_LONG).show();

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoBook().insert(book);
                    }
                });

                Intent it = new Intent();
                it.putExtra("book", book);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}