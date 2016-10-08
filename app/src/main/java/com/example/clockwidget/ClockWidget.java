package com.example.clockwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

public class ClockWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        ClockView clockView = new ClockView(context);
        clockView.measure(320, 320);
        clockView.layout(0, 0, 320, 320);
        clockView.setDrawingCacheEnabled(true);
        Bitmap bitmap = clockView.getDrawingCache();

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.clock_widget);
        remoteViews.setImageViewBitmap(R.id.imageView, bitmap);
        ComponentName widget = new ComponentName(context, ClockWidget.class);
        appWidgetManager.updateAppWidget(widget, remoteViews);
    }
}
