package com.example.keshe.notathome;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity{

    //刷新天气图片，随机
    protected void WeatherRand(){

        ImageView weatherImage=(ImageView)findViewById(R.id.weather);
        Random rand = new Random();
        int WeatherRandNum=rand.nextInt(9)+1;

        switch(WeatherRandNum) {
            case 1:
                weatherImage.setImageResource(R.drawable.windy);
                break;
            case 2:
                weatherImage.setImageResource(R.drawable.sunny);
                break;
            case 3:
                weatherImage.setImageResource(R.drawable.cloudy);
                break;
            case 4:
                weatherImage.setImageResource(R.drawable.cloudywindy);
                break;
            case 5:
                weatherImage.setImageResource(R.drawable.snowy);
                break;
            case 6:
                weatherImage.setImageResource(R.drawable.foggy);
                break;
            case 7:
                weatherImage.setImageResource(R.drawable.suncloudy);
                break;
            case 8:
                weatherImage.setImageResource(R.drawable.rainy);
                break;
            case 9:
                weatherImage.setImageResource(R.drawable.thunder);
                break;
        }

        weatherImage.invalidate();
    }

    //刷新温度，随机
    protected void TempRand(){
        TextView tempNum=(TextView)findViewById(R.id.texttempnum);
        Random rand = new Random();
        int TempRandNum =rand.nextInt(61)-20;
        tempNum.setText(TempRandNum+"℃");
    }

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tempBtn=(Button)findViewById(R.id.tempbtn);
        Button lightBtn = (Button)findViewById(R.id.lightbtn);
        Button doorBtn=(Button)findViewById(R.id.doorbtn);
        Button airBtn =(Button)findViewById(R.id.airbtn);
        Button homeBtn=(Button)findViewById(R.id.homebtn);

        Button refreshBtn=(Button)findViewById(R.id.refresh);

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WeatherRand();
                TempRand();
            }
        });

        tempBtn.setOnClickListener(new View.OnClickListener() {
            // TODO Auto-generated method stub
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent gotoTemp= new Intent();
                gotoTemp.setClass(MainActivity.this,TempActivity.class);
                startActivity(gotoTemp);
               }
        });

        lightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent gotoLight= new Intent();
                gotoLight.setClass(MainActivity.this,LightActivity.class);
                startActivity(gotoLight);

            }
        });

        doorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent gotoDoor = new Intent();
                gotoDoor.setClass(MainActivity.this,DoorActivity.class);
                startActivity(gotoDoor);

            }
        });

        airBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent gotoAir = new Intent();
                gotoAir.setClass(MainActivity.this,AirActivity.class);
                startActivity(gotoAir);

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent gotoHome=new Intent();
                gotoHome.setClass(MainActivity.this,HomeActivity.class);
                startActivity(gotoHome);

            }
        });

    }
}
