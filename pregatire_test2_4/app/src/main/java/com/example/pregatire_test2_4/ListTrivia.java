package com.example.pregatire_test2_4;

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

public class ListTrivia extends AppCompatActivity {
    List<Trivia> trivias = new ArrayList<>();
    TriviaDataBase database;
    private int idModificat = 0;
    ArrayAdapter<Trivia> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_trivia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Intent it = getIntent();
        //trivias = it.getParcelableArrayListExtra("trivia");

        database = Room.databaseBuilder(getApplicationContext(), TriviaDataBase.class, "Trivias.db").build();

        ListView lvTrivia = findViewById(R.id.lvTrivia);
        //ArrayAdapter<Trivia> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_activated_1, trivias);
        //lvTrivia.setAdapter(adapter);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                trivias = database.daoTrivia().select();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, trivias);
                        lvTrivia.setAdapter(adapter);
                    }
                });
            }
        });

        lvTrivia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getApplicationContext(), AddTrivia.class);
                it.putExtra("trivia", trivias.get(i));
                idModificat=1;
                startActivityForResult(it, 209);
                Toast.makeText(getApplicationContext(), trivias.get(i).toString(), Toast.LENGTH_LONG).show();
            }
        });

        lvTrivia.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoTrivia().delete(trivias.get(i));
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                trivias.remove(i);
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
                Trivia triviaModificat = data.getParcelableExtra("trivia");
                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.myLooper());
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoTrivia().update(triviaModificat);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                trivias.set(idModificat, triviaModificat);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        }
    }
}