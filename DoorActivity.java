package com.example.keshe.notathome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by lucia on 2018/7/7.
 */

public class DoorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.door_layout);

        Button homebtn=(Button)findViewById(R.id.homebtn4);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoHome=new Intent();
                gotoHome.setClass(DoorActivity.this,MainActivity.class);
                startActivity(gotoHome);
                finish();
            }
        });
    }
}
