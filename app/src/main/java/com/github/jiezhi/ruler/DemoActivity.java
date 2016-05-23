package com.github.jiezhi.ruler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.jiezhi.ruler.RulerView;

/**
 * Created by jiezhi on 4/6/16.
 * Function:
 */
public class DemoActivity extends Activity {
    private static final String TAG = "DemoActivity";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.demo_ruler);

        tv = (TextView) findViewById(R.id.textview);
//
        com.jiezhi.ruler.RulerView rulerView = (com.jiezhi.ruler.RulerView) findViewById(R.id.ruler_view);
        rulerView.setCallback(new RulerView.RulerResultCallback() {
            @Override
            public void setResult(int result) {
                tv.setText("Result:" + result);
            }
        });
    }
}
