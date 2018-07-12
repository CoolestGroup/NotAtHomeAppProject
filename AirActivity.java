package com.example.keshe.notathome;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lucia on 2018/7/7.
 */

public class AirActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.air_layout);
        //System.out.print("This is an activity");

        LineGraphicView tu25=(LineGraphicView)findViewById(R.id.line_graphic_pm25);
        LineGraphicView tu10=(LineGraphicView)findViewById(R.id.line_graphic_pm10);

        ArrayList<Double> yList25= new ArrayList<Double>();
        ArrayList<Double> yList10= new ArrayList<Double>();

       final ArrayList<String> xRawDatas = new ArrayList<String>();
        xRawDatas.add("07-2");
        xRawDatas.add("07-3");
        xRawDatas.add("07-4");
        xRawDatas.add("07-5");
        xRawDatas.add("07-6");
        xRawDatas.add("07-7");
        xRawDatas.add("07-8");
        xRawDatas.add("07-9");

        Random rand = new Random();
        int i=0;

        //Pm2.5数据
        double Pm25RandNum=120;
        for(i=0;i<=5;i++){
            Pm25RandNum=rand.nextInt(501);
            yList25.add(Pm25RandNum);
        }
        tu25.setData(yList25,xRawDatas,500,100);


        //Pm10数据
        double Pm10RandNum=40;//(MAX-MIN+1)+MIN
        for(i=0;i<=5;i++){
            Pm10RandNum=rand.nextInt(111)+40;
            yList10.add(Pm10RandNum);
        }
        tu10.setData(yList10, xRawDatas, 150, 20);

        TextView text10=(TextView)findViewById(R.id.text10);
        TextView text25=(TextView)findViewById(R.id.text25);

        text25.setText("PM2.5含量:(当前AQI)"+Pm25RandNum);
        text10.setText("PM10含量:(当前AQI)"+Pm10RandNum);

        Button refreshPM25=(Button)findViewById(R.id.pm25refresh);
        Button refreshPM10=(Button)findViewById(R.id.pm10refresh);
        Button homeBtn=(Button)findViewById(R.id.homebtn);

        refreshPM10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LineGraphicView line10=(LineGraphicView)findViewById(R.id.line_graphic_pm10);
                line10.invalidate();

                LineGraphicView tu10=(LineGraphicView)findViewById(R.id.line_graphic_pm10);
                ArrayList<Double> yList10= new ArrayList<Double>();
                double Pm10RandNum=100;
                int i=0;
                Random rand = new Random();

                for(i=0;i<=5;i++){
                    Pm10RandNum=rand.nextInt(111)+40;
                    yList10.add(Pm10RandNum);
                }
                tu10.setData(yList10,xRawDatas,150,20);
                TextView text10=(TextView)findViewById(R.id.text10);
                text10.setText("PM10含量:(当前AQI)"+Pm10RandNum);

                if(Pm10RandNum>=100)
                    new AlertDialog.Builder(AirActivity.this)
                            .setTitle("警告").setMessage("当前室内PM10浓度过高！")
                            .setPositiveButton("确定", null);

            }
        });

        refreshPM25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LineGraphicView line25=(LineGraphicView)findViewById(R.id.line_graphic_pm25);
                line25.invalidate();

                LineGraphicView tu25=(LineGraphicView)findViewById(R.id.line_graphic_pm25);
                ArrayList<Double> yList25= new ArrayList<Double>();
                double Pm25RandNum=120;
                int i=0;
                Random rand = new Random();

                for(i=0;i<=5;i++){
                    Pm25RandNum=rand.nextInt(501);
                    yList25.add(Pm25RandNum);
                }
                tu25.setData(yList25,xRawDatas,500,100);
                TextView text25=(TextView)findViewById(R.id.text25);
                text25.setText("PM2.5含量:(当前AQI)"+Pm25RandNum);

                if(Pm25RandNum>300) {
                } new AlertDialog.Builder(AirActivity.this)
                        .setTitle("警告").setMessage("当前室内PM2.5浓度过高！")
                        .setPositiveButton("确定", null);
                }

            });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoHome=new Intent();
                gotoHome.setClass(AirActivity.this,MainActivity.class);
                startActivity(gotoHome);
                finish();
            }
        });

    }


}
