package com.example.edvorapaint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PaintView extends View {

    public static int BRUSH_SIZE = 15;
    public static final int DEFAULT_COLOR = Color.BLACK;
    public static final int DEFAULT_BG_COLOR = Color.WHITE;

    private int mX, mY, dx, dy, sX, sY;
    private Path mPath;
    private Paint mPaint;
    private ArrayList<FingerTouch> paths = new ArrayList<>();
    public static int currentColor;
    private int backgroundColor = DEFAULT_BG_COLOR;
    private int strokeWidth = BRUSH_SIZE;

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mBitmapPaint = new Paint(Paint.DITHER_FLAG);

    static boolean rect;
    static boolean ellipse;
    static boolean arrow;
    static boolean line;

    public PaintView(Context context) {
        this(context, null);
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(DEFAULT_COLOR);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public void init(DisplayMetrics metrics) {
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(backgroundColor);

        currentColor = DEFAULT_COLOR;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        if(line)
            drawLine(canvas);
        else if(rect)
            drawRect(canvas);
        else if(ellipse)
            drawEllipse(canvas);
        else if(arrow)
            drawArrow(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
    }
    public void drawLine(Canvas canvas){
        for (FingerTouch ft : paths) {
            mPaint.setColor(ft.color);
            mPaint.setStrokeWidth(ft.strokeWidth);
            mCanvas.drawPath(ft.path, mPaint);
        }
    }
    public void drawArrow(Canvas canvas) {
        mPaint.setColor(currentColor);
        mPaint.setStrokeWidth(strokeWidth);
        mCanvas.drawLine(sX, sY, sX+dx, sY+dy, mPaint);
        mCanvas.drawLine(sX+dx, sY+dy, sX+dx-10, sY+dy+10, mPaint);
    }
    public void drawRect(Canvas canvas) {
        mPaint.setColor(currentColor);
        mPaint.setStrokeWidth(strokeWidth);
        mCanvas.drawRect(sX, sY, sX+dx, sY+dy, mPaint);
    }
    public void drawEllipse(Canvas canvas) {
        mPaint.setColor(currentColor);
        mPaint.setStrokeWidth(strokeWidth);
        mCanvas.drawOval(sX, sY, sX+dx, sY+dy, mPaint);
    }

    private void touchStart(float x, float y) {
        mPath = new Path();
        FingerTouch ft = new FingerTouch(currentColor, strokeWidth, mPath);

        if(line)
            paths.add(ft);

        mPath.reset();
        mPath.moveTo(x, y);

        mX = (int)x;
        mY = (int)y;

        sX = (int)x;
        sY = (int)y;
    }
    private void touchMove(float x, float y) {
        dx = (int)Math.abs(x - mX);
        dy = (int)Math.abs(y - mY);
        mPath.quadTo(mX, mY, (x+mX)/2, (y+mY)/2);
        mX = (int)x;
        mY = (int)y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
        }
        return true;
    }
}
