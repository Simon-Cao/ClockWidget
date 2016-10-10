package com.example.clockwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * ClockWidget をアプリとして実行するためのクラス
 */
public class MainActivity extends AppCompatActivity {

    // 時計のビュー
    private ClockView clockView;

    // アニメーションの実行内容、null ならアニメーション終了
    private Runnable runner;

    /**
     * アプリを起動したときに呼ばれる
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // スーパークラスのメソッドを実行する
        super.onCreate(savedInstanceState);

        // アプリの画面を生成する
        setContentView(R.layout.activity_main);

        // 時計のビューを取得する
        clockView = (ClockView) findViewById(R.id.clockView);
    }

    /**
     * アプリが表示されるときに呼ばれる
     */
    @Override
    protected void onResume() {
        // スーパークラスのメソッドを実行する
        super.onResume();

        // アニメーションの実行内容を設定する
        runner = new Runnable() {
            @Override
            public void run() {
                if (runner != null) {
                    // 時計のビューの表示内容を更新する
                    clockView.invalidate();

                    // 1000 ミリ秒 (1 秒) 後に、アニメーションを実行する
                    clockView.postDelayed(runner, 1000);
                }
            }
        };

        // 1000 ミリ秒 (= 1 秒) 後に、アニメーションを実行する
        clockView.postDelayed(runner, 1000);
    }

    /**
     * アプリが表示されなくなるときに呼ばれる
     */
    @Override
    protected void onPause() {
        // スーパークラスのメソッドを実行する
        super.onPause();

        // アニメーションを終了する
        runner = null;
    }
}
