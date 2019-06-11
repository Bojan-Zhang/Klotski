package com.example.newklotski;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private TextView steps_text;
    private int steps = 0;
    private Boolean isOver = false;

    public GameActivity(){
        gameActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        steps_text = (TextView) findViewById(R.id.count_steps);
        TextView textView = findViewById(R.id.game_title);
        AssetManager mgr=getResources().getAssets();
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/SIMLI.ttf");
        textView.setTypeface(tf);

        Button button_refresh = findViewById(R.id.refresh);
        button_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameView gameview = findViewById(R.id.game_view);
                gameview.refreshGame();
            }
        });

        Button button_returnMain = findViewById(R.id.returnMain);
        button_returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        DataHelper dbHelper = new DataHelper(this, DataHelper.DATABASE_NAME,null,1);
        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();

        //最少步数
        TextView best_text = findViewById(R.id.best_steps);
        Cursor cursor = sqliteDatabase.rawQuery("select min(steps) from user where game_name == 0", null);
        if (cursor.getColumnIndex("steps") < 0 ){
            best_text.setText("暂无记录");
        } else {
            best_text.setText(cursor.getColumnIndex("steps")+"");
        }

    }

    public void clearSteps(){
        steps = 0;
        showSteps();
    }

    public void showSteps(){
        steps_text.setText(steps+"");
    }

    public void addSteps(int s){
        steps = s;
        showSteps();
    }

    public void checkOver(Boolean flag){
        isOver = flag;
        if (isOver){
            //添加震动效果
            Vibrator vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
            long[] patter = {1000, 1000, 2000, 50};
            vibrator.vibrate(1000);
            vibrator.cancel();

            final CustomDialog.Builder alterDialog = new CustomDialog.Builder(GameActivity.this);
            TextView give_money = findViewById(R.id.give_money);
            give_money.setClickable(true);
            give_money.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GameActivity.this,GiveMoneyActivity.class);
                    startActivity(intent);
                }
            });

            alterDialog.setTitle("义释华容");
            alterDialog.setPositiveButton("重新开始",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            GameView gameview = findViewById(R.id.game_view);
                            gameview.refreshGame();
                        }
                    });
            alterDialog.setNegativeButton("记录成绩",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            DataStorage();
                        }
                    });
            alterDialog.create().show();
        }
    }

    private static  GameActivity gameActivity =  null;

    public static GameActivity getGameActivity() {
        return gameActivity;
    }

    private void DataStorage(){
        DataHelper dbHelper1 = new DataHelper(this,DataHelper.DATABASE_NAME,null,2);
        // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
        SQLiteDatabase  sqliteDatabase1 = dbHelper1.getWritableDatabase();

        // 创建ContentValues对象
        ContentValues values1 = new ContentValues();


        // 向该对象中插入键值对
        values1.put("id", 1);
        values1.put("game_name", GameView.level);
        values1.put("steps",steps);

        // 调用insert()方法将数据插入到数据库当中
        sqliteDatabase1.insert("user", null, values1);

        // sqliteDatabase.execSQL("insert into user (id,name) values (1,'carson')");

        //关闭数据库
        sqliteDatabase1.close();
    }

}
