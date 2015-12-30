package com.less.haku.hcomic.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.GradientDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by HaKu on 15/12/29.
 */
public class PageWidget extends View {

    private Bitmap foreImage;
    private Bitmap bgImage;
    private PointF touchPt;
    private int screenWidth;
    private int screenHeight;
    private GradientDrawable shadowDrawableRL;
    private GradientDrawable shadowDrawableLR;
    private ColorMatrixColorFilter mColorMatrixFilter;
    private Scroller mScroller;
    private int lastTouchX;

    public PageWidget(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        touchPt = new PointF(-1, -1);

        //ARGB A(0-透明,255-不透明)
        int[] color = { 0xb0333333 ,0x00333333};
        shadowDrawableRL = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, color);
        shadowDrawableRL.setGradientType(GradientDrawable.LINEAR_GRADIENT);

        shadowDrawableLR = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, color);
        shadowDrawableLR.setGradientType(GradientDrawable.LINEAR_GRADIENT);


        float[] array = {
                1, 0, 0, 0, 0, 0,
                1, 0, 0, 0, 0, 0,
                1, 0, 0, 0, 0, 0,
                1, 0};
//        float array[] = {
//                0.55f, 0, 0, 0, 80.0f,
//                0.55f, 0, 0, 80.0f, 0,
//                0.55f, 0, 80.0f, 0, 0,
//                0.2f, 0};
        ColorMatrix cm = new ColorMatrix();
        cm.set(array);
        /*
         * |A*0.55 + 80|
         * |R*0.55 + 80|
         * |G*0.55 + 80|
         * |B*0.2|
         */
//      cm.setSaturation(0);
        mColorMatrixFilter = new ColorMatrixColorFilter(cm);
        //利用滚动条来实现接触点放开后的动画效果
        mScroller = new Scroller(context);
    }
    @Override
    public void computeScroll() {
        // TODO Auto-generated method stub
        if (mScroller.computeScrollOffset()) {
            touchPt.x = mScroller.getCurrX();
            touchPt.y = mScroller.getCurrY();

            postInvalidate();
        } else {
//            touchPt.x = -1;
//            touchPt.y = -1;
        }
        super.computeScroll();
    }
    public void SetScreen(int screenWidth,int screenHeight){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public Bitmap getForeImage() {
        return foreImage;
    }

    public void setForeImage(Bitmap foreImage) {
        this.foreImage = foreImage;
    }

    public Bitmap getBgImage() {
        return bgImage;
    }

    public void setBgImage(Bitmap bgImage) {
        this.bgImage = bgImage;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        drawPageEffect(canvas);
        super.onDraw(canvas);
    }

    /**
     * 画前景图片
     * @param canvas
     */
    private void drawForceImage(Canvas canvas) {
        // TODO Auto-generated method stub
        Paint mPaint = new Paint();

        if (foreImage!=null) {
            canvas.drawBitmap(foreImage, 0, 0, mPaint);
        }
    }

    /**
     * 画背景图片
     * @param canvas
     */
    private void drawBgImage(Canvas canvas,Path path) {
        // TODO Auto-generated method stub
        Paint mPaint = new Paint();

        if (bgImage!=null) {
            canvas.save();

            //只在与路径相交处画图
            canvas.clipPath(path, Region.Op.INTERSECT);
            canvas.drawBitmap(bgImage, 0, 0, mPaint);
            canvas.restore();
        }
    }


    /**
     * 画翻页效果
     * @param canvas
     */
    private void drawPageEffect(Canvas canvas) {
        // TODO Auto-generated method stub
        drawForceImage(canvas);
        Paint mPaint = new Paint();
        if (touchPt.x!=-1 && touchPt.y!=-1) {
            //翻页左侧书边
            canvas.drawLine(touchPt.x, 0, touchPt.x, screenHeight, mPaint);

            //左侧书边画阴影
            shadowDrawableRL.setBounds((int)touchPt.x - 20, 0, (int)touchPt.x, screenHeight);
            shadowDrawableRL.draw(canvas);

            //翻页对折处
            float halfCut = touchPt.x + (screenWidth - touchPt.x)/2;
            canvas.drawLine(halfCut, 0, halfCut, screenHeight, mPaint);

            //对折处左侧画翻页页图片背面
            Rect backArea = new Rect((int)touchPt.x, 0, (int)halfCut, screenHeight);
            Paint backPaint = new Paint();
            backPaint.setColor(0xffdacab0);
            canvas.drawRect(backArea, backPaint);

            //将翻页图片正面进行处理水平翻转并平移到touchPt.x点
            Paint fbPaint = new Paint();
            fbPaint.setColorFilter(mColorMatrixFilter);
            Matrix matrix = new Matrix();

            matrix.preScale(-1, 1);
            matrix.postTranslate(foreImage.getWidth() + touchPt.x, 0);

            canvas.save();
            canvas.clipRect(backArea);
            canvas.drawBitmap(foreImage, matrix, fbPaint);
            canvas.restore();

            //对折处画左侧阴影
            shadowDrawableRL.setBounds((int)halfCut - 50, 0, (int)halfCut, screenHeight);
            shadowDrawableRL.draw(canvas);

            Path bgPath = new Path();

            //可以显示背景图的区域
            bgPath.addRect(new RectF(halfCut, 0, screenWidth, screenHeight), Path.Direction.CW);

            //对折出右侧画背景
            drawBgImage(canvas, bgPath);

            //对折处画右侧阴影
            shadowDrawableLR.setBounds((int)halfCut, 0, (int)halfCut + 50, screenHeight);
            shadowDrawableLR.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touchPt.x = event.getX();
            touchPt.y = event.getY();
        } else if(event.getAction() == MotionEvent.ACTION_MOVE) {
            lastTouchX = (int)touchPt.x;
            touchPt.x = event.getX();
            touchPt.y = event.getY();

            postInvalidate();
        } else if(event.getAction() == MotionEvent.ACTION_UP) {
            int dx, dy;

            dy = 0;

            //向右滑动
            if (lastTouchX<touchPt.x) {
                dx = foreImage.getWidth() - (int)touchPt.x + 30;
            }
            else{
                //向左滑动
                dx = -(int)touchPt.x - foreImage.getWidth();
            }

            mScroller.startScroll((int)touchPt.x, (int)touchPt.y, dx, dy, 1000);
            postInvalidate();
        }

        //必须为true，否则无法获取ACTION_MOVE及ACTION_UP事件
        return true;
    }
}

//public class PageActivity extends Activity {
//    protected void onCreate(android.os.Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//        PageWidget pageWidget = new PageWidget(this);
//        Display display = getWindowManager().getDefaultDisplay();
//        int width  = display.getWidth();
//        int height = display.getHeight();
//        pageWidget.SetScreen(width, height);
//        Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.pre7);
//        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.after7);
//        Bitmap foreImage = Bitmap.createScaledBitmap(bm1, width, height,false);
//        Bitmap bgImage = Bitmap.createScaledBitmap(bm2, width, height,false);
//        pageWidget.setBgImage(bgImage);
//        pageWidget.setForeImage(foreImage);
//        setContentView(pageWidget);
//
//        super.onCreate(savedInstanceState);
//    };
//}
