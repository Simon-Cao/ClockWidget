package com.example.clockwidget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

public class ClockService extends Service {

    private AppWidgetManager appWidgetManager;
    private ComponentName componentName;
    private RemoteViews remoteViews;

    private Thread thread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appWidgetManager = AppWidgetManager.getInstance(this);
        componentName = new ComponentName(this, ClockWidget.class);
        remoteViews = new RemoteViews(getPackageName(), R.layout.clock_widget);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (thread != null) {
                    updateClock();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });
        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        thread.interrupt();
        thread = null;
    }

    private void updateClock() {
        ClockView clockView = new ClockView(this);
        clockView.measure(320, 320);
        clockView.layout(0, 0, 320, 320);
        clockView.setDrawingCacheEnabled(true);

        Bitmap bitmap = clockView.getDrawingCache();
        remoteViews.setImageViewBitmap(R.id.imageView, bitmap);
        appWidgetManager.updateAppWidget(componentName, remoteViews);
    }
}
