package com.example.pregatire_test2_6;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    List<Curse> curse = new ArrayList<>();
    CurseDataBase database;
    private int id;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = Room.databaseBuilder(getApplicationContext(), CurseDataBase.class, "Curse.db").build();
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                count = database.daoCurse().count();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), getString(R.string.mesaj)+count, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        Button btnSalveaza = findViewById(R.id.btnSalveaza);
        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etDestinatie = findViewById(R.id.etDestinatie);
                String destinatie = etDestinatie.getText().toString();
                EditText etDistanta = findViewById(R.id.etDistanta);
                String sDistanta = etDistanta.getText().toString();
                int distanta = Integer.parseInt(sDistanta);

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        int lastId = database.daoCurse().getLastId();
                        id = lastId+1;
                    }
                });

                Curse cursa = new Curse(id, destinatie, distanta, true);
                Toast.makeText(getApplicationContext(), cursa.toString(), Toast.LENGTH_LONG).show();

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        database.daoCurse().insert(cursa);
                    }
                });
            }
        });

        Button btnIncarca = findViewById(R.id.btnIncarca);
        btnIncarca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String url = "https://pdm.ase.ro/curse.json";
                        HttpURLConnection con = null;
                        try{

                            URL apiUrl = new URL(url);
                            con = (HttpURLConnection) apiUrl.openConnection();
                            con.setRequestMethod("GET");
                            con.connect();

                            InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
                            BufferedReader reader = new BufferedReader(inputStreamReader);
                            StringBuilder response = new StringBuilder();
                            String linie = null;

                            while((linie=reader.readLine())!=null){
                                response.append(linie.toString());
                            }

                            JSONObject responseObject = new JSONObject(response.toString());
                            JSONArray responseArray = responseObject.getJSONArray("curse");
                            StringBuilder rez = new StringBuilder();
                            for(int i = 0;i<responseArray.length();i++){
                                JSONObject object = responseArray.getJSONObject(i);
                                String destinatie = object.getString("destinatie");
                                int distanta = object.getInt("distanta");

                                int lastId = database.daoCurse().getLastId();
                                id = lastId + 1;

                                Curse cursa = new Curse(id, destinatie, distanta, false);
                                database.daoCurse().insert(cursa);
                                rez.append(cursa);
                                rez.append("\n");
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    EditText etProtejat = findViewById(R.id.etProtejat);
                                    etProtejat.setText(rez.toString());
                                }
                            });

                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        } catch (ProtocolException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });

        Button btn = findViewById(R.id.btnSterge);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<curse.size();i++){
                            if(curse.get(i).isManual()){
                                database.daoCurse().delete(curse.get(i));
                            }
                        }
                    }
                });
            }
        });
    }
}