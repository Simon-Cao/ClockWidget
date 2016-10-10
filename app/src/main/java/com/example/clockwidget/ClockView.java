package com.example.clockwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * 時計を表示するためのクラス (ビュー)
 */
public class ClockView extends View {

    /**
     * コンストラクタ
     */
    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 時計を描画する
     * @param canvas 描画先
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // 座標を取得する
        int x0 = getWidth() / 2;        // ビューの中心の x 座標
        int y0 = getHeight() / 2;       // ビューの中心の y 座標
        int r = Math.min(x0, y0);       // ビューの内接円の半径

        // 円を描画する
        Paint paint = new Paint();              // 描画するための塗料を生成する
        paint.setStyle(Paint.Style.STROKE);     // 塗りつぶしではなく、輪郭線を描く
        paint.setColor(Color.YELLOW);           // 色は黄色
        paint.setStrokeWidth(5);                // 線の太さは 5 ピクセル
        RectF ovalRect = new RectF(x0 - (r - 5), y0 - (r - 5), x0 + (r - 5), y0 + (r - 5));
        canvas.drawOval(ovalRect, paint);       // 円を描画する

        // 現在時刻を取得する
        Calendar now = Calendar.getInstance();      // 現在の日付と時刻
        int hour = now.get(Calendar.HOUR) % 12;     // 時 (12 時間制)
        int minute = now.get(Calendar.MINUTE);      // 分
        int second = now.get(Calendar.SECOND);      // 秒

        // 時針を描画する
        paint.setColor(Color.RED);                      // 色は赤
        paint.setStrokeWidth(20);                       // 線の太さは 20 ピクセル
        //double rad1 = Math.PI * 2 * (hour * 60 + minute) / (12 * 60);
        double rad1 = Math.PI * 2 * hour / 12;          // 時針の角度を求める
        double x1 = x0 + Math.sin(rad1) * (r * 0.5);    // 時針の先端の x 座標
        double y1 = y0 - Math.cos(rad1) * (r * 0.5);    // 時針の先端の y 座標
        canvas.drawLine(x0, y0, (float) x1, (float) y1, paint);

        // 分針を描画する
        paint.setColor(Color.GREEN);                    // 色は緑
        paint.setStrokeWidth(10);                       // 線の太さは 10 ピクセル
        //double rad2 = Math.PI * 2 * (minute * 60 + second) / (60 * 60);
        double rad2 = Math.PI * 2 * minute / 60;        // 分針の角度を求める
        double x2 = x0 + Math.sin(rad2) * (r * 0.7);    // 分針の先端の x 座標
        double y2 = y0 - Math.cos(rad2) * (r * 0.7);    // 分針の先端の y 座標
        canvas.drawLine(x0, y0, (float) x2, (float) y2, paint);

        // 秒針を描画する
        paint.setColor(Color.BLUE);                     // 色は青
        paint.setStrokeWidth(5);                        // 線の太さは 5 ピクセル
        double rad3 = Math.PI * 2 * second / 60;        // 秒針の角度を求める
        double x3 = x0 + Math.sin(rad3) * (r * 0.9);    // 秒針の先端の x 座標
        double y3 = y0 - Math.cos(rad3) * (r * 0.9);    // 秒針の先端の y 座標
        canvas.drawLine(x0, y0, (float) x3, (float) y3, paint);
    }
}
