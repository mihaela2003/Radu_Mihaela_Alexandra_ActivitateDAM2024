package com.example.pregatire_test2_5;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class ListHistoricalEvent extends AppCompatActivity {
    List<HistoricalEvent> historicalEvents = new ArrayList<>();
    private int idModificat = 0;
    HistoricalEventDataBase database;
    ArrayAdapter<HistoricalEvent> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_historical_event);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Intent it = getIntent();
        //historicalEvents = it.getParcelableArrayListExtra("he");
        database = Room.databaseBuilder(getApplicationContext(), HistoricalEventDataBase.class, "HistoricalEvent.db").build();
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        ListView lvHE = findViewById(R.id.lvHE);
        //ArrayAdapter<HistoricalEvent> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, historicalEvents);
        //lvHE.setAdapter(adapter);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                historicalEvents = database.daoHE().select();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, historicalEvents);
                        lvHE.setAdapter(adapter);
                    }
                });
            }
        });

        lvHE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getApplicationContext(), AddHistoricalEvent.class);
                idModificat = 1;
                it.putExtra("he", historicalEvents.get(i));
                startActivityForResult(it, 209);
                Toast.makeText(getApplicationContext(), historicalEvents.get(i).toString(), Toast.LENGTH_LONG);
            }
        });
        lvHE.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                executor.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        database.daoHE().delete(historicalEvents.get(i));
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                historicalEvents.remove(i);
//                                adapter.notifyDataSetChanged();
//                            }
//                        });
//                    }
//                });
                SharedPreferences sp = getSharedPreferences("obiecteFavorite", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(historicalEvents.get(i).getkey(), historicalEvents.get(i).toString());
                editor.commit();
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==209){
                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.myLooper());

                HistoricalEvent heModificat = data.getParcelableExtra("he");
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoHE().update(heModificat);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                historicalEvents.set(idModificat, heModificat);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        }
    }
}