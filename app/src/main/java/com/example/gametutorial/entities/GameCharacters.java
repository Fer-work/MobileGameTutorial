package com.example.gametutorial.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.gametutorial.MainActivity;
import com.example.gametutorial.R;

public enum GameCharacters {
    PLAYER(R.drawable.player_spritesheet),
    FLAMEPLAYER(R.drawable.flame_spritesheet);


    private Bitmap spriteSheet;
    private Bitmap[][] sprites = new Bitmap[7][4];
    private BitmapFactory.Options options = new BitmapFactory.Options();

    // lets us get the images/resources from the main activity's context
    GameCharacters(int resId) {
        options.inScaled = false;
        spriteSheet = BitmapFactory.decodeResource(MainActivity.getGameContext().getResources(), resId, options);
        for (int i = 0; i < sprites.length; i++) {
            for (int j = 0; j < sprites[i].length; j++) {
                sprites[i][j] = getScaledBitmap(Bitmap.createBitmap(spriteSheet, j * 16, i * 16, 16, 16));
            }
        }

    }

    public Bitmap getSpriteSheet() {
        return spriteSheet;
    }

    public Bitmap getSprite(int yPos, int xPos) {
        return sprites[yPos][xPos];
    }

    private Bitmap getScaledBitmap(Bitmap bitmap){
        return Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 10, bitmap.getHeight() * 10, false);
    }
}
