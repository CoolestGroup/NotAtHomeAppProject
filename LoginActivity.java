package com.example.keshe.notathome;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import static android.content.ContentValues.TAG;

public class LoginActivity extends Activity {

    // 帐号和密码
    private EditText edname;
    private EditText edpassword;

    private Button btregister;
    private Button btlogin;

    // 创建SQLite数据库
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        edname = (EditText) findViewById(R.id.accountEditText);
        edpassword = (EditText) findViewById(R.id.pwdEditText);
        btregister = (Button) findViewById(R.id.gotoRegin);
        btlogin = (Button) findViewById(R.id.loginConfirm);

        db = SQLiteDatabase.openOrCreateDatabase(LoginActivity.this.getFilesDir().toString()
                + "/test.dbs", null);

        btregister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, ReginActivity.class);    // 跳转到注册界面
                startActivity(intent);
            }
        });
        btlogin.setOnClickListener(new LoginListener());
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        db.close();
    }


    class LoginListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            System.out.println("start to 输入");
            String name = edname.getText().toString();
            String password = edpassword.getText().toString();
            if (name.equals("") || password.equals("")) {
                // 弹出消息框
                new AlertDialog.Builder(LoginActivity.this).setTitle("错误")
                        .setMessage("帐号或密码不能空").setPositiveButton("确定", null)
                        .show();
            } else {
                //isUserinfo(name, password);
                System.out.println("start to check");
                if(DBHelper.check(name, password)) {
                    //
                    new AlertDialog.Builder(LoginActivity.this).setTitle("正确").setMessage("成功登录").show();
                    Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent1);
                    finish();
                } else {
                    new AlertDialog.Builder(LoginActivity.this).setTitle("错误")
                            .setMessage("帐号或密码错误！").setPositiveButton("确定", null)
                            .show();
                }

            }
        }

        // 判断输入的用户是否正确
        public Boolean isUserinfo(String name, String pwd) {
            try{
                String str="select * from tb_user where name=? and password=?";
                Cursor cursor = db.rawQuery(str, new String []{name,pwd});
                if(cursor.getCount()<=0){
                    new AlertDialog.Builder(LoginActivity.this).setTitle("错误")
                            .setMessage("帐号或密码错误！").setPositiveButton("确定", null)
                            .show();
                    return false;
                }else{

                    new AlertDialog.Builder(LoginActivity.this).setTitle("正确").setMessage("成功登录").show();
                    Log.d(TAG, "isUserinfo: aaa");
                    Intent intent1 = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent1);
                    finish();
                    return true;
                }

            }catch(SQLiteException e){
                createDb();
            }
            return false;
        }

    }
    // 创建数据库和用户表
    public void createDb() {
        db.execSQL("create table tb_user( name varchar(30) primary key,password varchar(30))");
    }/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

}

