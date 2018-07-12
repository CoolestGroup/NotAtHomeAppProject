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

public class LightActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_layout);

        Button homebtn=(Button)findViewById(R.id.homebtn3);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoHome=new Intent();
                gotoHome.setClass(LightActivity.this,MainActivity.class);
                startActivity(gotoHome);
                finish();
            }
        });
    }
}
