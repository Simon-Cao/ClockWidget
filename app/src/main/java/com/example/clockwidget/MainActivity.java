package com.example.clockwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ClockView clockView;

    private Runnable runner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clockView = (ClockView) findViewById(R.id.clockView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        runner = new Runnable() {
            @Override
            public void run() {
                if (runner != null) {
                    clockView.invalidate();
                    clockView.postDelayed(runner, 1000);
                }
            }
        };
        clockView.postDelayed(runner, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();

        runner = null;
    }
}
