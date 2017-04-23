package com.example.wenzty.drawingboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DebugUtils;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import static android.R.attr.startX;
import static android.R.attr.startY;

/**
 * Created by wenzty on 2017/4/23.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    private SurfaceHolder mSurfaceHolder;
    private Canvas mCanvas;
    private Boolean startDraw;
    private Paint mPaint = new Paint();
    private Path mPath = new Path();

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        //焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        //屏幕常亮
        this.setKeepScreenOn(true);
    }

    @Override
    public void run() {
        while (startDraw){
            draw();
        }
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startDraw = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
            startDraw = false;
    }
    private void draw(){
        try {
        mCanvas = mSurfaceHolder.lockCanvas();
        mCanvas.drawColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.RED);
        mCanvas.drawPath(mPath,mPaint);
    } catch (Exception e) {

    } finally {
        // 对画布内容进行提交
        if (mCanvas != null) {
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }
}

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction()){
           case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.moveTo(x,y);
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;

        }

        return true;
    }
    public void reset(){
        mPath.reset();
    }
}
