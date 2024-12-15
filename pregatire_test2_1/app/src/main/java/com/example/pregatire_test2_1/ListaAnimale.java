package com.example.pregatire_test2_1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaAnimale extends AppCompatActivity {
    List<Animal> animale = new ArrayList<>();
    private ArrayAdapter<Animal> adapter = null;
    private int idModificat = 0;
    AnimalDataBase database ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_animale);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = Room.databaseBuilder(this, AnimalDataBase.class, "Animale.db").build();
        //Intent it = getIntent();
        //animale = it.getParcelableArrayListExtra("animal");

        ListView lvAnimal = findViewById(R.id.listaAnimale);
        //ArrayAdapter<Animal> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, animale);
        //lvAnimal.setAdapter(adapter);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                animale = database.daoAnimal().select();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, animale);
                        lvAnimal.setAdapter(adapter);
                    }
                });
            }
        });

        lvAnimal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentModificat = new Intent(getApplicationContext(), AdaugaAnimal.class);
                intentModificat.putExtra("animal", animale.get(i));
                idModificat=i;
                startActivityForResult(intentModificat, 209);
                Toast.makeText(getApplicationContext(), animale.get(i).toString(), Toast.LENGTH_LONG).show();
            }
        });

        lvAnimal.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoAnimal().delete(animale.get(i));
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                animale.remove(i);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });

                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==209){
                Animal animalModificat = data.getParcelableExtra("animal");

                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.myLooper());

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoAnimal().update(animalModificat);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                animale.set(idModificat, animalModificat);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        }
    }
}