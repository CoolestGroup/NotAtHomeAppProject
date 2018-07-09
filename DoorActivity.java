package com.example.keshe.notathome;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lucia on 2018/7/7.
 */

public class DoorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.door_layout);
        System.out.print("This is an activity");
    }
}
