package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Anime> animeList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        animeList = new ArrayList<>();
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), AdaugaActivity.class);
                startActivityForResult(it, 203);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button listaAnimebtn = findViewById(R.id.buttonLista);//identificam buton
        //facem metoda onClick
        listaAnimebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //facem un intent
                Intent it = new Intent(getApplicationContext(), ListaAnime.class);
                it.putParcelableArrayListExtra("anime", (ArrayList<? extends Parcelable>) animeList);
                startActivity(it);
            }
        });

        Button listaImaginibtn =  findViewById(R.id.buttonImagini);
        listaImaginibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), ImaginiActivity.class);
                startActivity(it);
            }
        });

        Button AccuWeatherbtn =  findViewById(R.id.buttonAccuWeather);
        AccuWeatherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), AccuWeatherActivity.class);
                startActivity(it);
            }
        });

        Button sharedPreferencesBtn = findViewById(R.id.buttonAnimePreferate);
        sharedPreferencesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getApplicationContext(), SharedPreferencesList.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==203){
            if(resultCode==RESULT_OK){
                Anime anime = data.getParcelableExtra("anime");

                animeList.add(anime);
            }
        }
    }
}

