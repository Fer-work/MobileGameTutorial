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

import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    // new paint object
    private final Paint redPaint = new Paint();
    private SurfaceHolder holder;
    // declare variable for input
    float xInput, yInput;
    // array list for holding the red squares we will draw
    private ArrayList<RandomSquare> squares = new ArrayList<>();
    // get a random value
    private Random rand = new Random();
    private GameLoop gameLoop;


    public GamePanel(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
        redPaint.setColor(Color.RED);
        gameLoop = new GameLoop(this);
    }

    public void render() {

        // Get the canvas
        Canvas c = holder.lockCanvas();
        c.drawColor(Color.BLACK);

        // Draw to the canvas
        for(RandomSquare square : squares){
            square.draw(c);
        }
        // send canvas to be rendered
        holder.unlockCanvasAndPost(c);
    }

    public void update(){
        // Draw to the canvas
        for(RandomSquare square : squares){
            square.move();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // get the input from the screen and store it to the x and y variables
        xInput = event.getX();
        yInput = event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            System.out.println("Pressing down event");

            PointF pos = new PointF(xInput, yInput);
            int color = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            int size = rand.nextInt(100) + 25;

            squares.add(new RandomSquare(pos, color, size));
            // call render method
//            render();
//            update();
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

    private class RandomSquare{
        private PointF pos;
        private int size;
        private Paint paint;
        private int xDir = 1;
        private int yDir = 1;

        public RandomSquare(PointF pos, int color, int size){
            paint = new Paint();
            paint.setColor(color);
            this.pos = pos;
            this.size = size;
        }

        public void move(){
            pos.x += xDir;
            if(pos.x >= 1080 || pos.x <= 0){
                xDir *= -5;
            }
            pos.y += yDir;
            if(pos.y >= 1920 || pos.y <= 0){
                yDir *= -5;
            }
        }

        public void draw(Canvas c){
            c.drawRect(pos.x, pos.y, pos.x + size, pos.y + size, paint);
        }


    }
}
