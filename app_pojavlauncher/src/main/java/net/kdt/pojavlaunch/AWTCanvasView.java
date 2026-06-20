package net.kdt.pojavlaunch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class AWTCanvasView extends View {
    private final Paint mMangoPaintBg = new Paint();
    private final Paint mMangoPaintText = new Paint();
    private final Paint mMangoPaintValue = new Paint();
    
    private float mHudX = 20f;
    private float mHudY = 40f;
    private float mLastTouchX;
    private float mLastTouchY;
    private boolean mIsDragging = false;
    
    private boolean mDrawing = false;

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
        mMangoPaintText.setTextSize(32f);
        mMangoPaintText.setAntiAlias(true);
        mMangoPaintText.setFakeBoldText(true);

        mMangoPaintValue.setColor(Color.parseColor("#00FF00"));
        mMangoPaintValue.setTextSize(32f);
        mMangoPaintValue.setAntiAlias(true);
        mMangoPaintValue.setFakeBoldText(true);
    }

    private float fps() {
        return 60.0f; 
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        String fpsText = "MangoHUD  FPS: ";
        String fpsValue = String.valueOf(Math.round(fps() * 10) / 10);
        
        float padding = 16f;
        float textWidth = mMangoPaintText.measureText(fpsText) + mMangoPaintValue.measureText(fpsValue);
        float textHeight = 36f;

        canvas.drawRect(mHudX - padding, mHudY - textHeight, mHudX + textWidth + padding, mHudY + padding, mMangoPaintBg);
        canvas.drawText(fpsText, mHudX, mHudY, mMangoPaintText);
        canvas.drawText(fpsValue, mHudX + mMangoPaintText.measureText(fpsText), mHudY, mMangoPaintValue);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (x >= mHudX - 30 && x <= mHudX + 300 && y >= mHudY - 50 && y <= mHudY + 30) {
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
