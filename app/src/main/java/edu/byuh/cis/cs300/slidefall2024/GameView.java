package edu.byuh.cis.cs300.slidefall2024;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * the main "view" class of the program.
 * All drawing and user interaction "begins" here, although
 * this class delegates much of the work to other classes.
 */
public class GameView extends View {

    private Grid grid;
    private boolean firstRun;
    private final List<GuiButton> buttons;

    /**
     * the public constructor. Like all view subclasses,
     * it requires a Context object, which gets forwarded
     * on to the superclass
     * @param context a reference to the Activity that created this view
     */
    public GameView(Context context) {
        super(context);
        firstRun = true;
        buttons = new ArrayList<>();
    }

    /**
     * Whatever you want the user to see, needs to get
     * drawn from this method
     * @param c The Canvas object, provided by the OS when onDraw is called
     */
    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        c.drawColor(Color.WHITE);
        if (firstRun) {
            init();
            firstRun = false;
        }

        grid.draw(c);
        for (GuiButton b : buttons) {
            b.draw(c);
        }
    }

    /**
     * this method sets up some of the variables
     * needed for drawing the UI
     */
    private void init() {
        float w = getWidth();
        float h = getHeight();
        float unit = w/16f;
        float gridX = unit * 2.5f;
        float cellSize = unit * 2.3f;
        float gridY = unit * 9;
        grid = new Grid(gridX, gridY, cellSize);
        float buttonTop = gridY - cellSize;
        float buttonLeft = gridX - cellSize;

        //instantiate the top row of buttons
        buttons.add(new GuiButton('1', this, buttonLeft + cellSize*1, buttonTop, cellSize));
        buttons.add(new GuiButton('2', this, buttonLeft + cellSize*2, buttonTop, cellSize));
        buttons.add(new GuiButton('3', this, buttonLeft + cellSize*3, buttonTop, cellSize));
        buttons.add(new GuiButton('4', this, buttonLeft + cellSize*4, buttonTop, cellSize));
        buttons.add(new GuiButton('5', this, buttonLeft + cellSize*5, buttonTop, cellSize));

        //instantiate the left column of buttons
        buttons.add(new GuiButton('A', this, buttonLeft, buttonTop + cellSize*1, cellSize));
        buttons.add(new GuiButton('B', this, buttonLeft, buttonTop + cellSize*2, cellSize));
        buttons.add(new GuiButton('C', this, buttonLeft, buttonTop + cellSize*3, cellSize));
        buttons.add(new GuiButton('D', this, buttonLeft, buttonTop + cellSize*4, cellSize));
        buttons.add(new GuiButton('E', this, buttonLeft, buttonTop + cellSize*5, cellSize));
    }

    public boolean onTouchEvent(@NonNull MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                boolean touchedButton = false;
                for (GuiButton button : buttons) {
                    if (button.contains(x, y)) {
                        button.press();
                        touchedButton = true;
                        invalidate();
                        break;
                    }
                }
                if (!touchedButton) {
                    Toast.makeText(getContext(), "Touch a button", Toast.LENGTH_SHORT).show();
                }
                break;

            case MotionEvent.ACTION_UP:
                for (GuiButton button : buttons) {
                    button.release();
                }
                invalidate();
                performClick();
                break;
        }
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
