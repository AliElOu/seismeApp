package com.example.deprem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private static final long DELAY_TIME_MILLISECONDS = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entering_page);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent pour passer à MainActivity
                Intent intent = new Intent(MainActivity.this, SeismeAlertActivity.class);
                startActivity(intent);
                finish();
            }
        }, DELAY_TIME_MILLISECONDS);
    }
}
