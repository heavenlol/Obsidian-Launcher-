package net.kdt.pojavlaunch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class AWTCanvasView extends View {
    public static int AWT_CANVAS_WIDTH = 0;
    public static int AWT_CANVAS_HEIGHT = 0;
    public boolean mDrawing = false;

    private final Paint mMangoPaintBg = new Paint();
    private final Paint mMangoPaintText = new Paint();
    private final Paint mMangoPaintValue = new Paint();
    
    private float mHudX = 30f;
    private float mHudY = 60f;
    private float mLastTouchX;
    private float mLastTouchY;
    private boolean mIsDragging = false;

    public AWTCanvasView(Context context) {
        super(context);
        initHud();
    }

    public AWTCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHud();
    }

    private void initHud() {
        mMangoPaintBg.setColor(Color.parseColor("#E6111115"));
        mMangoPaintBg.setStyle(Paint.Style.FILL);

        mMangoPaintText.setColor(Color.parseColor("#FF9800"));
        mMangoPaintText.setTextSize(34f);
        mMangoPaintText.setAntiAlias(true);
        mMangoPaintText.setFakeBoldText(true);

        mMangoPaintValue.setColor(Color.parseColor("#00FF00"));
        mMangoPaintValue.setTextSize(34f);
        mMangoPaintValue.setAntiAlias(true);
        mMangoPaintValue.setFakeBoldText(true);
    }

    public float fps() {
        return 60.0f; 
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        AWT_CANVAS_WIDTH = right - left;
        AWT_CANVAS_HEIGHT = bottom - top;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        String fpsText = "MangoHUD  FPS: ";
        String fpsValue = "60"; 
        
        float padding = 20f;
        float textWidth = mMangoPaintText.measureText(fpsText) + mMangoPaintValue.measureText(fpsValue);
        float textHeight = 38f;

        canvas.drawRect(mHudX - padding, mHudY - textHeight - 5, mHudX + textWidth + padding, mHudY + padding, mMangoPaintBg);
        canvas.drawText(fpsText, mHudX, mHudY, mMangoPaintText);
        canvas.drawText(fpsValue, mHudX + mMangoPaintText.measureText(fpsText), mHudY, mMangoPaintValue);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (x >= mHudX - 40 && x <= mHudX + 350 && y >= mHudY - 60 && y <= mHudY + 40) {
                    mLastTouchX = x;
                    mLastTouchY = y;
                    mIsDragging = true;
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mIsDragging) {
                    float dx = x - mLastTouchX;
                    float dy = y - mLastTouchY;
                    mHudX += dx;
                    mHudY += dy;
                    mLastTouchX = x;
                    mLastTouchY = y;
                    invalidate(); 
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsDragging = false;
                break;
        }
        return super.onTouchEvent(event);
    }
}
