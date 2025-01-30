package com.example.gametutorial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.gametutorial.entities.GameCharacters;

import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    // new paint object
    private final Paint redPaint = new Paint();
    private SurfaceHolder holder;
    // declare variable for input
    private float xInput, yInput;
    // get a random value
    private Random rand = new Random();
    private GameLoop gameLoop;
    private ArrayList<PointF> flames = new ArrayList<>();


    public GamePanel(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        redPaint.setColor(Color.RED);
        gameLoop = new GameLoop(this);

        for (int i = 0; i < 10; i++) {
            flames.add(new PointF(rand.nextInt(500), rand.nextInt(1000)));
        }

    }

    public void render() {
        // Get the canvas
        Canvas c = holder.lockCanvas();
        c.drawColor(Color.BLACK);

        c.drawBitmap(GameCharacters.PLAYER.getSpriteSheet(), 500, 500, null);

        c.drawBitmap(GameCharacters.PLAYER.getSprite(6, 3), xInput, yInput, null);
        c.drawBitmap(GameCharacters.FLAMEPLAYER.getSprite(4, 3), 500, 500, null);

        for (PointF pos : flames){
            c.drawBitmap(GameCharacters.FLAMEPLAYER.getSprite(4, 3), pos.x, pos.y, null);
        }

        // send canvas to be rendered
        holder.unlockCanvasAndPost(c);
    }

    public void update(double delta) {
        // Draw to the canvas
        for (PointF pos : flames) {
            pos.y += 100 * delta;
            pos.x += 100 * delta;

            if (pos.y >= 400) {
                pos.y *= -1;
            }

            if (pos.x >= 900) {
                pos.x *= -1;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            xInput = event.getX();
            yInput = event.getY();
        }
        return true;
    }

    // for later
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startGameLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}
