package com.example.pregatire_test2_7;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
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

public class BookList extends AppCompatActivity {
    List<Book> books=new ArrayList<>();
    BookDataBase database;
    private int idModificat = 0;
    ArrayAdapter<Book> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Intent it = getIntent();
        //books = it.getParcelableArrayListExtra("book");
        database = Room.databaseBuilder(getApplicationContext(), BookDataBase.class, "Books.db").build();
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        ListView lvBook = findViewById(R.id.lvBook);
        //ArrayAdapter<Book> adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_activated_1, books);
        //lvBook.setAdapter(adapter);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                books = database.daoBook().select();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_activated_1, books);
                        lvBook.setAdapter(adapter);
                    }
                });
            }
        });

        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getApplicationContext(), AddBook.class);
                it.putExtra("book", books.get(i));
                idModificat=1;
                startActivityForResult(it, 209);
                Toast.makeText(getApplicationContext(), books.get(i).toString(), Toast.LENGTH_LONG).show();
            }
        });

        lvBook.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoBook().delete(books.get(i));
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                books.remove(i);
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
                Executor executor = Executors.newSingleThreadExecutor();
                Handler handler = new Handler(Looper.myLooper());

                Book bookChanged = data.getParcelableExtra("book");
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoBook().update(bookChanged);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                books.set(idModificat, bookChanged);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
            }
        }
    }
}