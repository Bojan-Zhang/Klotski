package com.example.newklotski;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ChooseGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
        TextView text_0 = findViewById(R.id.game_0);
        TextView text_1 = findViewById(R.id.game_1);
        TextView text_2 = findViewById(R.id.game_2);
        TextView text_3 = findViewById(R.id.game_3);
        TextView text_4 = findViewById(R.id.game_4);
        TextView text_5 = findViewById(R.id.game_5);
        AssetManager mgr=getResources().getAssets();
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/SIMLI.ttf");
        text_0.setTypeface(tf);
        text_1.setTypeface(tf);
        text_2.setTypeface(tf);
        text_3.setTypeface(tf);
        text_4.setTypeface(tf);
        text_5.setTypeface(tf);
    }

    public void setGame_0(View view) {
        com.example.newklotski.GameView.level = 0;
        Intent intent = new Intent(ChooseGame.this,MainActivity.class);
        startActivity(intent);
    }

    public void setGame_1(View view) {
        com.example.newklotski.GameView.level = 1;
        Intent intent = new Intent(ChooseGame.this,MainActivity.class);
        startActivity(intent);
    }

    public void setGame_2(View view) {
        com.example.newklotski.GameView.level = 2;
        Intent intent = new Intent(ChooseGame.this,MainActivity.class);
        startActivity(intent);
    }

    public void setGame_3(View view) {
        com.example.newklotski.GameView.level = 3;
        Intent intent = new Intent(ChooseGame.this,MainActivity.class);
        startActivity(intent);
    }

    public void setGame_4(View view) {
        com.example.newklotski.GameView.level = 4;
        Intent intent = new Intent(ChooseGame.this,MainActivity.class);
        startActivity(intent);
    }

    public void setGame_5(View view) {
        com.example.newklotski.GameView.level = 5;
        Intent intent = new Intent(ChooseGame.this,MainActivity.class);
        startActivity(intent);
    }
}
