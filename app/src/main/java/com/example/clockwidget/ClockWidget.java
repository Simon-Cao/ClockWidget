package com.example.clockwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class ClockWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        context.startService(new Intent(context, ClockService.class));
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);

        context.stopService(new Intent(context, ClockService.class));
    }
}
