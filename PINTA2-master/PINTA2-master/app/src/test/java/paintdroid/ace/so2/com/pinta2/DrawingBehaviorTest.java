package paintdroid.ace.so2.com.pinta2;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.MotionEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.GraphicsMode;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(application = Application.class, sdk = 34)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
public class DrawingBehaviorTest {
    private DrawingView view;
    @Before public void createCanvas() {
        view = new DrawingView(RuntimeEnvironment.getApplication(), null);
        view.layout(0, 0, 200, 200);
        view.setColor("#FF0000");
        view.setBrushSize(20);
    }
    private void stroke(int y) {
        touch(MotionEvent.ACTION_DOWN, 30, y);
        touch(MotionEvent.ACTION_MOVE, 160, y);
        touch(MotionEvent.ACTION_UP, 160, y);
    }
    private void touch(int action, int x, int y) {
        MotionEvent event = MotionEvent.obtain(0, 1, action, x, y, 0);
        try { view.onTouchEvent(event); } finally { event.recycle(); }
    }
    private int pixel(int x, int y) {
        Bitmap bitmap = view.exportBitmap();
        try { return bitmap.getPixel(x, y); } finally { bitmap.recycle(); }
    }
    @Test public void drawingUsesTheSelectedColor() {
        stroke(60);
        assertEquals(Color.RED, pixel(90, 60));
    }
    @Test public void eraserRemovesInkAndDrawingCanResume() {
        stroke(60);
        view.setErase(true);
        stroke(60);
        assertEquals(Color.WHITE, pixel(90, 60));
        view.setErase(false);
        view.setColor("#0000FF");
        stroke(100);
        assertEquals(Color.BLUE, pixel(90, 100));
    }
    @Test public void aNewDrawingClearsInk() {
        stroke(60);
        view.startNew();
        assertEquals(Color.WHITE, pixel(90, 60));
    }
    @Test public void resizingRetainsTheDrawing() {
        stroke(60);
        view.layout(0, 0, 400, 400);
        assertEquals(Color.RED, pixel(180, 120));
    }
    @Test public void aTapLeavesADot() {
        touch(MotionEvent.ACTION_DOWN, 60, 60);
        touch(MotionEvent.ACTION_UP, 60, 60);
        assertEquals(Color.RED, pixel(60, 60));
    }
    @Test public void selectingAColorLeavesEraserMode() {
        view.setErase(true);
        view.setColor("#0000FF");
        stroke(60);
        assertEquals(Color.BLUE, pixel(90, 60));
    }
}
