package com.example.clockwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class ClockView extends View {

    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int x0 = getWidth() / 2;
        int y0 = getHeight() / 2;
        int r = Math.min(x0, y0);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(5);
        RectF ovalRect = new RectF(x0 - (r - 5), y0 - (r - 5), x0 + (r - 5), y0 + (r - 5));
        canvas.drawOval(ovalRect, paint);

        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR) % 12;
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);

        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        double h = Math.PI * 2 * (hour * 60 + minute) / (12 * 60);
        canvas.drawLine(x0, y0, (float) (x0 + Math.sin(h) * (r * 0.5)), (float) (y0 - Math.cos(h) * (r * 0.5)), paint);

        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(10);
        double m = Math.PI * 2 * (minute * 60 + second) / (60 * 60);
        canvas.drawLine(x0, y0, (float) (x0 + Math.sin(m) * (r * 0.7)), (float) (y0 - Math.cos(m) * (r * 0.7)), paint);

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        double s = Math.PI * 2 * second / 60;
        canvas.drawLine(x0, y0, (float) (x0 + Math.sin(s) * (r * 0.9)), (float) (y0 - Math.cos(s) * (r * 0.9)), paint);
    }
}
