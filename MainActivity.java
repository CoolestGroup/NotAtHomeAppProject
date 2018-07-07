package com.example.keshe.notathome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button tempBtn=(Button)findViewById(R.id.tempbtn);
    Button lightBtn=(Button)findViewById(R.id.lightbtn);
    Button doorBtn=(Button)findViewById(R.id.doorbtn);
    Button airBtn =(Button)findViewById(R.id.airbtn);
    Button homeBtn=(Button)findViewById(R.id.homebtn);

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoTemp= new Intent();
                gotoTemp.setClass(MainActivity.this,TempActivity.class);
                startActivity(gotoTemp);
                finish();
            }
        });

        lightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoLight= new Intent();
                gotoLight.setClass(MainActivity.this,LightActivity.class);
                startActivity(gotoLight);
                finish();
            }
        });

        doorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoDoor = new Intent();
                gotoDoor.setClass(MainActivity.this,DoorActivity.class);
                startActivity(gotoDoor);
                finish();
            }
        });

        airBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoAir = new Intent();
                gotoAir.setClass(MainActivity.this,AirActivity.class);
                startActivity(gotoAir);
                finish();
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoHome=new Intent();
                gotoHome.setClass(MainActivity.this,HomeActivity.class);
                startActivity(gotoHome);
                finish();
            }
        });

    }
}
