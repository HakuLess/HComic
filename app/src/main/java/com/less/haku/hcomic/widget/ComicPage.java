package com.less.haku.hcomic.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HaKu on 15/12/24.
 */
public class ComicPage extends View {

    private Paint mPaint;
    private int radius;

    public ComicPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        radius = 200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, mPaint);
//        refresh();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int measureSpec) {
        String Tag = "measure";
        int result = 0; //结果
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.AT_MOST:  // 子容器可以是声明大小内的任意大小
//                Log.e(Tag, "子容器可以是声明大小内的任意大小");
//                Log.e(Tag, "大小为:" + specSize);
                result = specSize;
                break;
            case MeasureSpec.EXACTLY: //父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间.  比如EditTextView中的DrawLeft
//                Log.e(Tag, "父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间");
//                Log.e(Tag, "大小为:" + specSize);
                result = specSize;
                break;
            case MeasureSpec.UNSPECIFIED:  //父容器对于子容器没有任何限制,子容器想要多大就多大. 所以完全取决于子view的大小
//                Log.e(Tag, "父容器对于子容器没有任何限制,子容器想要多大就多大");
//                Log.e(Tag, "大小为:" + specSize);
                result = 1500;
                break;
            default:
                break;
        }
        return result;
    }

    public void refresh() {
        radius = 200 + (int)(Math.random() * 100);
        invalidate();
    }

}
