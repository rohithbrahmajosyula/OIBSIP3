package com.example.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private Button startButton, stopButton, holdButton;
    private long pauseOffset;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        holdButton = findViewById(R.id.holdButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChronometer();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopChronometer();
            }
        });

        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdChronometer();
            }
        });
    }

    private void startChronometer() {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    private void stopChronometer() {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    private void holdChronometer() {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        } else {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
}
