package com.example.heshitha.story.common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by Heshitha on 3/16/2015.
 */
public class ImageHelper {
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int newWidth = 0;

        int Bottom = 0;
        int Right = 0;

        if(width == height){
            newWidth = width;
        }else if(width > height){
            newWidth = height;
        }else{
            newWidth = width;
        }

        float startX = 0;
        float startY = 0;

        startX = (width - newWidth) / 2f;
        startY = (height - newWidth) / 2f;

        if(width == height){
            Bottom = newWidth;
            Right = newWidth;
        }else if(width > height){
            Bottom = newWidth;
            Right = newWidth + (int)startX;
        }else{
            Bottom = newWidth + (int)startY;
            Right = newWidth;
        }

        Bitmap croppedImage = Bitmap.createBitmap(bitmap, (int)startX, (int)startY, newWidth, newWidth);

        Bitmap output = Bitmap.createBitmap(newWidth, newWidth, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        //final Rect rect = new Rect((int)startX, (int)startY, Right, Bottom);
        final Rect rect = new Rect(0, 0, newWidth, newWidth);
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(croppedImage, rect, rect, paint);

        return output;
    }
}
