package com.example.timer_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private int seconds = 0;
    private boolean isRunning = false;
    private boolean wasRunning = false;
    private TextView textViewTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTimer = findViewById(R.id.textViewTimer);
        if(savedInstanceState!=null) {
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putBoolean("isRunning",isRunning);
        outState.putBoolean("wasRunning",wasRunning);
    }


    @Override
    protected void onPause() {
        super.onPause();
        wasRunning=isRunning;
        isRunning=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning=wasRunning;
    }

    public void onClickStart(View view) {
        isRunning=true;
    }

    public void onClickPause(View view) {
        isRunning=false;
    }

    public void onClickReset(View view) {
        isRunning=false;
        seconds=0;
    }

    private void runTimer() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs= seconds%60;
                String res = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                textViewTimer.setText(res);
                if(isRunning)
                    seconds++;
                handler.postDelayed(this,1000);
            }
        },1000);

    }
}