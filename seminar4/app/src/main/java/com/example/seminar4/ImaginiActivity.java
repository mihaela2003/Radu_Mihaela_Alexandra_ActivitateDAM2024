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


        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://m.media-amazon.com/images/I/619VEe0W8SL._AC_UF1000,1000_QL80_.jpg");
        linkuriImagini.add("https://images-na.ssl-images-amazon.com/images/I/81odMrRpfYL._AC_UL210_SR210,210_.jpg");
        linkuriImagini.add("https://i1.sndcdn.com/artworks-ZEz82oFyioDzbTDr-o7LwpA-t500x500.png");
        linkuriImagini.add("https://m.media-amazon.com/images/M/MV5BMmFjMWQyMWMtNGRiMy00NTdjLWFkYWItNDg2YWM2ZjE0MzkxXkEyXkFqcGc@._V1_QL75_UX164_.jpg");
        linkuriImagini.add("https://m.media-amazon.com/images/M/MV5BOWNmY2IzOGItMmQyNy00ZTM0LThiNjItODM3YzdkYjRlNWU1XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        List<Bitmap> imagini = new ArrayList<>();

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
                        lista.add(new ImaginiDomeniu("Violet Evergarden", imagini.get(0), "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.amazon.ca%2FQIANC-Evergarden-Picture-Aesthetic-Bedroom%2Fdp%2FB09Y5PX7L4&psig=AOvVaw12z0jgYXzRmU-wRcfE6xCY&ust=1732816403726000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCPD9i6mK_YkDFQAAAAAdAAAAABAG"));
                        lista.add(new ImaginiDomeniu("Vanitas", imagini.get(1), "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.amazon.com%2FCase-Study-Vanitas-Vol%2Fdp%2F031655281X&psig=AOvVaw1DzUUoaBkWLSyipylJDbFw&ust=1732816323874000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCLC7uv6J_YkDFQAAAAAdAAAAABAE"));
                        lista.add(new ImaginiDomeniu("Kiyotaka Ayanokoji", imagini.get(2), "https://www.google.com/url?sa=i&url=https%3A%2F%2Fsoundcloud.com%2Fflaqyy%2Fkiyotaka-ayanokoji-x-im-truly-impressed-x-classroom-of-the-elite&psig=AOvVaw29bnB_rBBlb7ObniM7OrfH&ust=1732815902579000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCJji7bWI_YkDFQAAAAAdAAAAABAo"));
                        lista.add(new ImaginiDomeniu("Haikyuu - The dumpster battle", imagini.get(3), "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.imdb.com%2Ftitle%2Ftt30476486%2F&psig=AOvVaw3Y9hKINXRpSWP1XHMU8R7D&ust=1732037726376000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCOimzL215okDFQAAAAAdAAAAABAI"));
                        lista.add(new ImaginiDomeniu("86: Eighty-Six", imagini.get(4), "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.imdb.com%2Ftitle%2Ftt13718450%2F&psig=AOvVaw3Fu8xk7-mAcaBuqWHyYXNb&ust=1732036426036000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCPij1dKw5okDFQAAAAAdAAAAABAE"));

                        ListView lv = findViewById(R.id.imagini);
                        ImagineAdapter adapter = new ImagineAdapter(lista, getApplicationContext(), R.layout.imagine_layout);
                        lv.setAdapter(adapter);

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                                it.putExtra("link", lista.get(i).getLink());
                                startActivity(it);
                            }
                        });

                    }

                });

            }
        });





    }
}