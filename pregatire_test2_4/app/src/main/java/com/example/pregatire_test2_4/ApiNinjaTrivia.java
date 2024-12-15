package com.example.pregatire_test2_4;

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
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ApiNinjaTrivia extends AppCompatActivity {
    private String api_key = "8+oXSMw+3uhsglciCYB9Jg==gkaq5v7uLE8Z9dRC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_api_ninja_trivia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etCategory = findViewById(R.id.ETCategory);
                String category = etCategory.getText().toString();

                String url = "https://api.api-ninjas.com/v1/trivia?category=" + category;
                executor.execute(new Runnable() {
                    @Override
                    public void run() {

                        HttpURLConnection con = null;
                        try {
                            URL apiURL = new URL(url);
                            con = (HttpURLConnection) apiURL.openConnection();
                            con.setRequestMethod("GET");
                            con.setRequestProperty("X-Api-Key", api_key);
                            con.connect();

                            InputStreamReader inputStreamReader = new InputStreamReader(con.getInputStream());
                            BufferedReader reader = new BufferedReader(inputStreamReader);
                            StringBuilder response = new StringBuilder();
                            String linie = null;

                            while((linie= reader.readLine())!=null){
                                response.append(linie);
                            }

                            reader.close();
                            con.disconnect();

                            StringBuilder rez = new StringBuilder();
                            JSONArray responseArray = new JSONArray(response.toString());
                            for(int i=0;i<responseArray.length();i++){
                                JSONObject object = responseArray.getJSONObject(i);

                                String question = object.getString("question");
                                String answer = object.getString("answer");

                                rez.append(question+"-"+answer+"\n");
                            }

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    TextView tvRezultat = findViewById(R.id.tvRezultat);
                                    tvRezultat.setText(rez);
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