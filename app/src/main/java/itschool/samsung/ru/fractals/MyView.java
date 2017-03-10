package itschool.samsung.ru.fractals;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Admin on 06.03.2017.
 */
public class MyView extends View {
    int length = 120;
    double number = 0.6;
    float angle = 45;
    int lev = 5;
    Paint paint;
    int width;

    public MyView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = canvas.getWidth();

        canvas.translate(canvas.getWidth() / 2, canvas.getHeight());
        canvas.drawLine(0,0,0,-length,paint);
        canvas.translate(0,-length);

        Tree(canvas,lev);
    }

    public void Tree(Canvas canvas, int level)
    {
        if(level > 0) {
            canvas.save();
            canvas.rotate(-angle,0,0);
            canvas.drawLine(0, 0, 0, (float) (-length * Math.pow(number, lev - level + 1)), paint);
            canvas.translate(0, (float) (-length * Math.pow(number, lev - level + 1)));
            Tree(canvas, level - 1);
            canvas.restore();

            canvas.save();
            canvas.rotate(angle, 0,0);
            canvas.drawLine(0, 0, 0, (float) (-length * Math.pow(number, lev - level + 1)), paint);
            canvas.translate(0, (float) (-length * Math.pow(number, lev - level + 1)));
            Tree(canvas, level - 1);
            canvas.restore();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(width != 0)
            angle = 90 - event.getX() / width * 90;
        invalidate();
        return true;
    }
}
