package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Authentication extends AppCompatActivity {

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        Button button = (Button) findViewById(R.id.btn_auth);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        URL url = null;
                        try {
                            url = new URL("https://httpbin.org/basic-auth/bob/sympa");
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                            TextView username = (TextView) findViewById(R.id.username);
                            TextView password = (TextView) findViewById(R.id.password);
                            // get the username and password
                            String currentUsername = username.getText().toString();
                            String currentPassword = password.getText().toString();
                            String basicAuth = "Basic " + Base64.encodeToString((currentUsername +":"+ currentPassword).getBytes(),
                                    Base64.NO_WRAP);
                            urlConnection.setRequestProperty ("Authorization", basicAuth);
                            try {
                                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                String s = readStream(in);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView result = (TextView) findViewById(R.id.result);
                                        result.setText("My result here");
                                    }
                                });

                                Log.i("JFL", s);
                            } finally {
                                urlConnection.disconnect();
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }


            });
        };
    }

