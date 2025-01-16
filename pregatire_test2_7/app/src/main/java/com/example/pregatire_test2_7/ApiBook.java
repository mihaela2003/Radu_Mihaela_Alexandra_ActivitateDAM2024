package com.example.pregatire_test2_7;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ApiBook extends AppCompatActivity {
    private String api_key = "6e564fd67e9645ac8424cd0dd63e2a95";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_api_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        Button btn = findViewById(R.id.buttonSearch);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etSearch = findViewById(R.id.etSearch);
                String search = etSearch.getText().toString();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection con = null;
                        String url = "https://api.bigbookapi.com/search-books?query="+search+"&api-key="+api_key;

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
                                response.append(linie);
                            }

                            reader.close();
                            con.disconnect();

                            JSONObject responseObject = new JSONObject(response.toString());
                            JSONArray responseArray = responseObject.getJSONArray("books");
                            StringBuilder rez = new StringBuilder();
                            for(int i=0;i<responseArray.length();i++){
                                JSONArray bookItem = responseArray.getJSONArray(i);
                                JSONObject object =  bookItem.getJSONObject(0);

                                String id = object.getString("id");
                                String title = object.getString("title");

                                rez.append(id).append("-").append(title).append("\n");
                            }
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    TextView rezultat = findViewById(R.id.tvRezultat);
                                    rezultat.setText(rez);
                                }
                            });
                        } catch (MalformedURLException e) {
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
    }
}