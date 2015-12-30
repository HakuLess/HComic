package com.less.haku.hcomic.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HaKu on 15/12/29.
 * 练习用自定义View
 */
public class CustomView extends View {

    private Paint paint;
    private Context context;
    private int radiu;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        this.context = context;
        this.radiu = 200;
    }

    public void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);

        // 设置画笔颜色为浅灰色
        paint.setColor(Color.LTGRAY);

    /*
     * 设置描边的粗细，单位：像素px
     * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
     */
        paint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制圆环
        canvas.drawCircle(300, 300, radiu, paint);
        if (radiu < 500) {
            setRadiu(radiu + 20);
        }
    }

    public synchronized void setRadiu(int radiu) {
        this.radiu = radiu;

        // 重绘
        invalidate();
    }
}
