package com.example.keshe.notathome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lucia on 2018/7/4.
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);

        final Intent intent = new Intent(this,LoginActivity.class); //从本活动转至登录活动
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                startActivity(intent); //执行
                finish();
            }
        };
        System.out.println("");
        System.out.println("hi");
        timer.schedule(task, 1000 * 3); //等待3秒
    }

}
