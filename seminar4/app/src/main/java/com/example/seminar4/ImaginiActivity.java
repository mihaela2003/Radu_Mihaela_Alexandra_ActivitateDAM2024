package com.example.seminar4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.BitmapCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImaginiActivity extends AppCompatActivity {
    private List<Bitmap> imagini = null;
    private List<ImaginiDomeniu> lista = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imagini = new ArrayList<>();
        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://static.wikia.nocookie.net/violet-evergarden/images/a/ae/Violet_Evergarden.png/revision/latest?cb=20180209195829");
        linkuriImagini.add("https://static.wikia.nocookie.net/pandorahearts/images/6/6a/Vanitas_anime.jpg/revision/latest?cb=20210506035914");
        linkuriImagini.add("https://static.wikia.nocookie.net/youkoso-jitsuryoku-shijou-shugi-no-kyoushitsu-e/images/f/f2/Kiyotaka_Ayanok%C5%8Dji_LN_visual.png/revision/latest?cb=20170901054135");
        linkuriImagini.add("https://m.media-amazon.com/images/M/MV5BMmFjMWQyMWMtNGRiMy00NTdjLWFkYWItNDg2YWM2ZjE0MzkxXkEyXkFqcGc@._V1_QL75_UX164_.jpg");
        linkuriImagini.add("https://m.media-amazon.com/images/M/MV5BOWNmY2IzOGItMmQyNy00ZTM0LThiNjItODM3YzdkYjRlNWU1XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link:linkuriImagini){
                    HttpURLConnection con = null;
                    try{
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch(MalformedURLException e){
                        throw new RuntimeException(e);
                    } catch(IOException e){
                        throw new RuntimeException();
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lista = new ArrayList<>();
                        lista.add(new ImaginiDomeniu("Violet Evergarden", imagini.get(0), "https://www.google.com/url?sa=i&url=https%3A%2F%2Fviolet-evergarden.fandom.com%2Fwiki%2FViolet_Evergarden&psig=AOvVaw38IlXIYTqarsMRLNldFnMC&ust=1732037462639000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCICuyr-05okDFQAAAAAdAAAAABAE"));
                        lista.add(new ImaginiDomeniu("Vanitas", imagini.get(1), "https://www.google.com/url?sa=i&url=https%3A%2F%2Fmochijun.fandom.com%2Fwiki%2FVanitas&psig=AOvVaw3J4c0oJnHXzoHNGlISaQVY&ust=1732037541017000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCJiC5OS05okDFQAAAAAdAAAAABAE"));
                        lista.add(new ImaginiDomeniu("Kiyotaka Ayanokoji", imagini.get(2), "https://www.google.com/url?sa=i&url=https%3A%2F%2Fyou-zitsu.fandom.com%2Fwiki%2FKiyotaka_Ayanok%25C5%258Dji&psig=AOvVaw0g326YmJO6Z2QC--9MQZaQ&ust=1732037627004000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCICE6I215okDFQAAAAAdAAAAABAE"));
                        lista.add(new ImaginiDomeniu("Haikyuu - The dumpster battle", imagini.get(3), "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.imdb.com%2Ftitle%2Ftt30476486%2F&psig=AOvVaw3Y9hKINXRpSWP1XHMU8R7D&ust=1732037726376000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCOimzL215okDFQAAAAAdAAAAABAI"));
                        lista.add(new ImaginiDomeniu("86: Eighty-Six", imagini.get(4), "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.imdb.com%2Ftitle%2Ftt13718450%2F&psig=AOvVaw3Fu8xk7-mAcaBuqWHyYXNb&ust=1732036426036000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCPij1dKw5okDFQAAAAAdAAAAABAE"));
                    }
                });
                ListView lv = findViewById(R.id.imagini);
                ImagineAdapter adapter = new ImagineAdapter(lista, getApplicationContext(), R.layout.imagine_layout);
                lv.setAdapter(adapter);
            }
        });

        ListView lv = findViewById(R.id.imagini);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                it.putExtra("link", lista.get(i).getLink());
                startActivity(it);
            }
        });


    }
}