package paintdroid.ace.so2.com.pinta2;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.TypedValue;

/**
 * Created by ace on 20/10/16.
 */


public class DrawingView extends View {

    //drawing path
    private Path drawPath;
    //drawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //initial color
    private int paintColor = 0xFF660000;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;
    //brushes sizes
    private float brushSize, lastBrushSize;

    private boolean erase=false;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    private void setupDrawing() {

        brushSize = getResources().getInteger(R.integer.medium_size);
        lastBrushSize = brushSize;

        //get drawing area setup for interaction
        drawPath = new Path();
        drawPaint = new Paint();
        //set the initial color
        drawPaint.setColor(paintColor);
        //set the initial path properties:

        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(brushSize);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        //instantiating the canvas Paint object
        canvasPaint = new Paint(Paint.DITHER_FLAG);


    }

    //override the onSizeChanged method, which will be called when the custom View is assigned a size
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //view given size
        super.onSizeChanged(w, h, oldw, oldh);

        //instantiate the drawing canvas and bitmap using the width and height values

        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    //override the onDraw method, so add it to the class now:

    @Override
    protected void onDraw(Canvas canvas) {
        //draw view
        //Inside the method, draw the canvas and the drawing path

        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    //listen for touch events. In your drawingView class, add the following method:

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //detect user touch
        //Inside the method, retrieve the X and Y positions of the user touch:
        float touchX = event.getX();
        float touchY = event.getY();

        /*The MotionEvent parameter to the onTouchEvent method will let us respond to particular
        touch events. The actions we are interested in to implement drawing are down, move and up.
        Add a switch statement in the method to respond to each of these:*/

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;

        }
        invalidate();
        return true;
    }

    public void setColor(String newColor) {
        //set color
        invalidate();
        //parsing and set the color for drawing
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }

    public void setBrushSize(float newSize) {
        //update size

        //update the brush size with the passed value:


        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                newSize, getResources().getDisplayMetrics());
        brushSize = pixelAmount;
        drawPaint.setStrokeWidth(brushSize);
    }

    public void setLastBrushSize(float lastSize){
        lastBrushSize=lastSize;
    }
    public float getLastBrushSize(){
        return lastBrushSize;
    }

    public void setErase(boolean isErase){
        //set erase true or false
        erase=isErase;
        //alter the Paint object to erase or switch back to drawing
        if(erase) drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        else drawPaint.setXfermode(null);

    }

    public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }

}
