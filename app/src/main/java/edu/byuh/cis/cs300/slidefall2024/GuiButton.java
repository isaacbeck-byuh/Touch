package edu.byuh.cis.cs300.slidefall2024;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class GuiButton {

    private RectF bounds;
    private Bitmap unpressedButton;
    private Bitmap pressedButton;
    private boolean pressed;
    private char label;

    public GuiButton(char name, View parent, float x, float y, float width) {
        label = name;
        bounds = new RectF(x, y, x+width, y+width);
        unpressedButton = BitmapFactory.decodeResource(parent.getResources(), R.drawable.unpressed_button);
        unpressedButton = Bitmap.createScaledBitmap(unpressedButton, (int)width, (int)width, true);
        pressedButton = BitmapFactory.decodeResource(parent.getResources(), R.drawable.pressed_button);
        pressedButton = Bitmap.createScaledBitmap(pressedButton, (int) width, (int) width, true);
        pressed = false;
    }

    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
    }
    public void press() {
        pressed = true;
    }
    public void release() {
        pressed = false;
    }

    public void draw(Canvas c) {
        if (pressed) {
            c.drawBitmap(pressedButton, bounds.left, bounds.top, null);
        } else {
            c.drawBitmap(unpressedButton, bounds.left, bounds.top, null);
        }
    }

}

