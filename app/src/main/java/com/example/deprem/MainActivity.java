package com.example.deprem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rc = findViewById(R.id.rv);
        getData();
    }

    private void getData() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    return fetchJsonData("https://ecommerceapp00.000webhostapp.com/EcommerceApp/getSeisme.php");
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                if (s != null) {
                    try {
                        JSONArray jo = new JSONArray(s);
                        SendToRc(jo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("MainActivity", "Failed to fetch JSON data");
                }
            }
        }.execute();
    }

    private void SendToRc(JSONArray data) {
        RcAdapter adapter = new RcAdapter(this, data);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adapter);
    }

    private String fetchJsonData(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        StringBuilder jsonData = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            jsonData.append(line);
        }
        reader.close();

        connection.disconnect();
        System.out.println(jsonData.toString());
        return jsonData.toString();
    }
}
