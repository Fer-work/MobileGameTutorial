package com.example.gametutorial;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static Context gameContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameContext = this;
        EdgeToEdge.enable(this);
        setContentView(new GamePanel(this));
    }

    //  getter method to get the game context anywhere in the app
    public static Context getGameContext() {
        return gameContext;
    }
}