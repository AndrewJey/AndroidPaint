package paintdroid.ace.so2.com.pinta2;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

final class DrawingStorage {
    private DrawingStorage() { }

    static Uri save(Context context, Bitmap bitmap) throws IOException {
        String name = "PINTA2-" + UUID.randomUUID() + ".png";
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, name);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/png");
        if (Build.VERSION.SDK_INT >= 29) {
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/PINTA2");
            values.put(MediaStore.Images.Media.IS_PENDING, 1);
        } else {
            File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "PINTA2");
            if (!directory.isDirectory() && !directory.mkdirs()) throw new IOException("Cannot create drawing directory");
            values.put(MediaStore.Images.Media.DATA, new File(directory, name).getPath());
        }
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        if (uri == null) throw new IOException("Cannot create drawing");
        try {
            try (OutputStream stream = context.getContentResolver().openOutputStream(uri)) {
                if (stream == null || !bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)) {
                    throw new IOException("Cannot write drawing");
                }
            }
            if (Build.VERSION.SDK_INT >= 29) {
                ContentValues ready = new ContentValues();
                ready.put(MediaStore.Images.Media.IS_PENDING, 0);
                context.getContentResolver().update(uri, ready, null, null);
            }
            return uri;
        } catch (IOException | RuntimeException error) {
            context.getContentResolver().delete(uri, null, null);
            throw error;
        }
    }
}
