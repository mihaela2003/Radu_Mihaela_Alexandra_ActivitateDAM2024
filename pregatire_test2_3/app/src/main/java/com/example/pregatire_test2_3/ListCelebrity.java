package com.example.pregatire_test2_3;

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

public class ListCelebrity extends AppCompatActivity {
    List<Celebrity> celebrities = new ArrayList<>();
    private int idModificat = 0;
    ArrayAdapter<Celebrity> adapter = null;
    CelebrityDataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_celebrity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Intent it = getIntent();
        //celebrities = it.getParcelableArrayListExtra("celebrity");
        dataBase = Room.databaseBuilder(getApplicationContext(), CelebrityDataBase.class, "Celebrities.db").build();

        ListView lvCelebrity = findViewById(R.id.lvCelebrity);
        //ArrayAdapter<Celebrity> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, celebrities);
        //lvCelebrity.setAdapter(adapter);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                celebrities = dataBase.daoCelebrity().select();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, celebrities);
                        lvCelebrity.setAdapter(adapter);
                    }
                });
            }
        });

        lvCelebrity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent itModificat = new Intent(getApplicationContext(), AddCelebrity.class);
                itModificat.putExtra("celebrity", celebrities.get(i));
                idModificat = 1;
                startActivityForResult(itModificat, 209);
                Toast.makeText(getApplicationContext(), celebrities.get(i).toString(), Toast.LENGTH_LONG).show();
            }
        });

        lvCelebrity.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                executor.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        dataBase.daoCelebrity().delete(celebrities.get(i));
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                celebrities.remove(i);
//                                adapter.notifyDataSetChanged();
//                            }
//                        });
//                    }
//                });
                SharedPreferences sp = getSharedPreferences("obiecteFavorite", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(celebrities.get(i).getkey(), celebrities.get(i).toString());
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
                Celebrity celebrityModificat = data.getParcelableExtra("celebrity");

                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.myLooper());

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        dataBase.daoCelebrity().update(celebrityModificat);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                celebrities.set(idModificat, celebrityModificat);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        }
    }
}