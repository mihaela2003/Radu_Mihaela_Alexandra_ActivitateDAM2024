package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ListaAnime extends AppCompatActivity {

    private List<Anime> anime = null;
    private int idModificat=0;
    private AnimeAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_anime);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        List<Anime> anime = it.getParcelableArrayListExtra("anime");

        ListView lv = findViewById(R.id.animeLv);

        //ArrayAdapter<Anime> adapter =  new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, anime);
        //lv.setAdapter(adapter);

        adapter = new AnimeAdapter(anime, getApplicationContext(), R.layout.row_item);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentModifica = new Intent(getApplicationContext(), AdaugaActivity.class);
                intentModifica.putExtra("anime", anime.get(i));
                idModificat = 1;
                startActivityForResult(intentModifica, 209);
                Toast.makeText(getApplicationContext(),anime.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //aici cand tinem mai mult apasat, se sterge -> noi aici lucram pe o copie, nu pe original
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //int i -> item pe care se face stergerea, in cazul asta
                anime.remove(i);
                adapter.notifyDataSetChanged();//daca nu puneam asta nu se dadea un fel de notificare ca sa dea refresh si sa stearga -> un fel de refresh
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==209){
                anime.set(idModificat, data.getParcelableExtra("anime"));
                adapter.notifyDataSetChanged();
            }
        }
    }
}