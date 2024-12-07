package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class AdaugaActivity extends AppCompatActivity {
    AnimeDataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //setContentView(R.layout.activity_adauga);
        setContentView(R.layout.activity_adauga);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = Room.databaseBuilder(getApplicationContext(), AnimeDataBase.class, "Anime.db").build();

        Intent it = getIntent();
        if(it.hasExtra("anime")){
            Anime anime = it.getParcelableExtra("anime");
            EditText denumireET = findViewById(R.id.editTextDenumire);
            EditText anET = findViewById(R.id.editTextAn);
            EditText genreET = findViewById(R.id.editTextGenre);
            CheckBox finishedCB = findViewById(R.id.cbDa);
            EditText epET = findViewById(R.id.editTextNrEp);

            denumireET.setText(anime.getDenumire());
            anET.setText(String.valueOf(anime.getAnAparitie()));
            genreET.setText(anime.getGenre());
            finishedCB.setChecked(anime.isFinished());
            epET.setText(String.valueOf(anime.getNrEpisoade()));
        }

        Button btn = findViewById(R.id.buttonAdauga);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etDenumire = findViewById(R.id.editTextDenumire);
                String denumire = etDenumire.getText().toString();
                EditText etAn = findViewById(R.id.editTextAn);
                String sAN = etAn.getText().toString();
                int an = Integer.parseInt(sAN);
                EditText etGenre = findViewById(R.id.editTextGenre);
                String genre = etGenre.getText().toString();
                CheckBox cbF = findViewById(R.id.cbDa);
                Boolean finished = cbF.isChecked();
                EditText etNrEp = findViewById(R.id.editTextNrEp);
                String snrEp = etNrEp.getText().toString();
                int nrEp = Integer.parseInt(snrEp);
                Anime anime = new Anime(denumire,an,genre,finished,nrEp);

                Toast.makeText(AdaugaActivity.this,anime.toString(), Toast.LENGTH_LONG).show();

                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoAnime().insert(anime);
                    }
                });

                Intent it = new Intent();
                it.putExtra("anime", anime);
                setResult(RESULT_OK,it);
                finish();
            }
        });


    }
}