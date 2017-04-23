package com.example.wenzty.drawingboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
    private MySurfaceView mview;
    private Button btnreset;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        mview = (MySurfaceView) findViewById(R.id.MySurfaceView);
        btnreset = (Button) findViewById(R.id.btn_reset);
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mview.reset();
            }
        });
        startActivity(new Intent(context, SecondActivity.class));
    }
}
