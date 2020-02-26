package com.example.viewlecture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * TODO: document your custom view class.
 */
public class CustomView extends View implements GestureDetector.OnGestureListener {
    private int[] colors = {
            Color.RED,
            Color.BLACK,
            Color.BLUE,
            Color.YELLOW,
            Color.GREEN
    };

    private int current_index = 0;
    Paint p_center = new Paint();
    private Paint p_left_top = new Paint();
    private Paint p_right_top = new Paint();
    private Paint p_left_bottom = new Paint();
    private Paint p_right_bottom = new Paint();


    private GestureDetector gestureDetector;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
        gestureDetector = new GestureDetector(getContext(), this);

        p_center.setColor(Color.CYAN);


    }

    public CustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        p_right_top.setColor(Color.BLACK);
        p_left_top.setColor(Color.GREEN);
        p_left_bottom.setColor(Color.YELLOW);
        p_right_bottom.setColor(Color.CYAN);

        canvas.drawRect(0,0, getWidth()/2, getHeight()/2, p_left_top);
        canvas.drawRect(getWidth(),0, getWidth()/2, getHeight()/2, p_right_top);
        canvas.drawRect(0,getHeight(), getWidth()/2, getHeight()/2 , p_left_bottom);
        canvas.drawRect(getWidth(),getHeight(), getWidth()/2, getHeight()/2, p_right_bottom);

        canvas.drawCircle(getWidth()/2, getHeight()/2, 250, p_center);

    }

    @Override
    public boolean onDown(MotionEvent e) {
        int width = getWidth();
        int height = getHeight();
        int half_height = height/2;
        int half_width = width/2;

        if(e.getX() <= half_width && e.getY() <= half_height){
            System.out.println("Left top");
            p_center.setColor(p_left_top.getColor());
        }
        if(e.getX() > half_width && e.getY() <= half_height){
            System.out.println("Right top");
            p_center.setColor(p_right_top.getColor());
        }
        if(e.getX() < half_width && e.getY() >= half_height){
            System.out.println("Left bottom");
            p_center.setColor(p_left_bottom.getColor());
        }
        if(e.getX() >= half_width && e.getY() >= half_height){
            System.out.println("Right bottom");
            p_center.setColor(p_right_bottom.getColor());
        }
        invalidate();
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if(current_index < 5) {
            setBackgroundColor(colors[current_index]);
            current_index++;
        } else{
            current_index = 0;
        }

        return true;
    }
}


