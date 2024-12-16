package com.example.pregatire_test2_2;

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

public class ListQuotes extends AppCompatActivity {
    List<Quote> quotes = new ArrayList<>();
    private int idModificat = 0;
    private ArrayAdapter<Quote> adapter = null;
    QuoteDataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_quotes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = Room.databaseBuilder(getApplicationContext(), QuoteDataBase.class, "Quotes.db").build();
        //Intent it = getIntent();
        //quotes = it.getParcelableArrayListExtra("quote");

        ListView lvQuotes = findViewById(R.id.lvQuotes);
        //ArrayAdapter<Quote> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_activated_1, quotes);
        //lvQuotes.setAdapter(adapter);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
         executor.execute(new Runnable() {
             @Override
             public void run() {
                 quotes = database.daoQuote().select();
                 handler.post(new Runnable() {
                     @Override
                     public void run() {
                         adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, quotes);
                         lvQuotes.setAdapter(adapter);
                     }
                 });
             }
         });

        lvQuotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent itModificat = new Intent(getApplicationContext(), AddQuote.class);
                itModificat.putExtra("quote", quotes.get(i));
                idModificat = i;
                startActivityForResult(itModificat, 209);
                Toast.makeText(getApplicationContext(), quotes.get(i).toString(), Toast.LENGTH_LONG).show();
            }
        });

        lvQuotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                executor.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        database.daoQuote().delete(quotes.get(i));
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                quotes.remove(i);
//                                adapter.notifyDataSetChanged();
//                            }
//                        });
//                    }
//                });
                SharedPreferences sp = getSharedPreferences("obiecteFavorite", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(quotes.get(i).getkey(), quotes.get(i).toString());
                editor.commit();
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode==209){
                Quote quoteModificat = data.getParcelableExtra("quote");

                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.myLooper());

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoQuote().update(quoteModificat);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                quotes.set(idModificat, quoteModificat);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        }

    }
}