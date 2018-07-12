package com.example.keshe.notathome;

import android.app.Activity;
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

public class TempActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_layout);

        LineGraphicView tutemp=(LineGraphicView)findViewById(R.id.line_graphic_temp);
        ArrayList<Double> yList= new ArrayList<Double>();
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

        //室温数据
        double tempRandNum=16;
        for(i=0;i<=5;i++){
            tempRandNum=rand.nextInt(41);
            yList.add(tempRandNum);
        }
        tutemp.setData(yList,xRawDatas,40,10);

        TextView texttemp=(TextView)findViewById(R.id.tempnumber);
        texttemp.setText(tempRandNum+"℃");


        Button refreshTemp = (Button)findViewById(R.id.temprefresh);
        Button homeBtn=(Button)findViewById(R.id.homebtn2);

        refreshTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LineGraphicView linetemp=(LineGraphicView)findViewById(R.id.line_graphic_temp);
                linetemp.invalidate();

                ArrayList<Double> yList= new ArrayList<Double>();
                Random rand = new Random();
                int i=0;
                LineGraphicView linetempp=(LineGraphicView)findViewById(R.id.line_graphic_temp);

                //室温数据
                double tempRandNum=16;
                for(i=0;i<=5;i++){
                    tempRandNum=rand.nextInt(41);
                    yList.add(tempRandNum);
                }
                linetempp.setData(yList,xRawDatas,40,10);

                TextView texttemp=(TextView)findViewById(R.id.tempnumber);
                texttemp.setText(tempRandNum+"℃");

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoHome=new Intent();
                gotoHome.setClass(TempActivity.this,MainActivity.class);
                startActivity(gotoHome);
                finish();
            }
        });
    }
}
