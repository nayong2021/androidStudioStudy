package kr.co.company.simpledrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class SingleTouchView extends View {
    private Paint paint = new Paint();
    private Path path;
    private int currentColor = Color.BLACK;
    private ArrayList<Integer> colorArray = new ArrayList<>();
    private ArrayList<Path> pathArray = new ArrayList<>();

    public SingleTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setStrokeWidth(10.0f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setAntiAlias(true);
    }

    public void setColor(int c){
        currentColor = c;
    }

    public void clearPath() {
        pathArray.clear();
        colorArray.clear();
        invalidate();
    }
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < pathArray.size(); i++){
            paint.setColor(colorArray.get(i));
            canvas.drawPath(pathArray.get(i), paint);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path = new Path();
                pathArray.add(path);
                colorArray.add(currentColor);
                path.moveTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        path.moveTo(eventX, eventY);
        path.lineTo(eventX, eventY);

        invalidate();;
        return true;
    }
}
