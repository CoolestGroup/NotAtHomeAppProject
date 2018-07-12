package com.example.keshe.notathome;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import static android.content.ContentValues.TAG;

public class LoginActivity extends Activity {

    // 帐号和密码
    private EditText edname;
    private EditText edpassword;

    private Button btregister;
    private Button btlogin;

    private Handler myhandler;
    SharedPreferences sp;
    Thread t;
    String realname, name, password;
    int success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        edname = (EditText) findViewById(R.id.accountEditText);
        edpassword = (EditText) findViewById(R.id.pwdEditText);
        btregister = (Button) findViewById(R.id.gotoRegin);
        btlogin = (Button) findViewById(R.id.loginConfirm);

        sp = getSharedPreferences("login",MODE_PRIVATE);
        String getname = sp.getString("name",null);
        if(getname == null) {
            Toast.makeText(LoginActivity.this, "sp null", 0).show();
        } else {
            Toast.makeText(LoginActivity.this, "sp have", 0).show();
            edname.setText(sp.getString("name", null));
            edpassword.setText(sp.getString("password", null));
        }

        //登录按钮点击事件
        btlogin.setOnClickListener(new LoginListener());
        //注册按钮点击事件
        btregister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, ReginActivity.class);    // 跳转到注册界面
                startActivity(intent);
            }
        });

        myhandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1) {
                    Toast.makeText(LoginActivity.this, "用户名或密码不正确", 0).show();
                } else if(msg.what ==2) {
                    Toast.makeText(LoginActivity.this, "欢迎您："+realname, 0).show();
                    /*登陆成功将数据写到本地保存下来，以便下次自动额登陆*/
                    sp=getSharedPreferences("login", MODE_PRIVATE);
                    Editor editor=sp.edit();
                    editor.putString("name", name);
                    editor.putString("password", password);
                    editor.commit();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "网络连接失败", 0).show();
                }
            }
        };

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //db.close();
    }


    class LoginListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            System.out.println("开始创建线程");
            // TODO Auto-generated method stub
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println("start to 输入");
                        name = edname.getText().toString();
                        password = edpassword.getText().toString();

                        if (name.equals("") || password.equals("")) {
                            // 弹出消息框
                            System.out.println("输入为空");

                            new AlertDialog.Builder(LoginActivity.this).setTitle("错误")
                                    .setMessage("帐号或密码不能空").setPositiveButton("确定", null)
                                    .show();
                        } else {
                            System.out.println("开始连接数据库");

                            //URL url = new URL("http://192.168.0.101:80/test.php?name="+name+"&password="+password);
                            URL url = new URL("http://192.168.0.101/test.php");
                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                            System.out.println(urlConnection.getResponseCode());
                            urlConnection.setRequestMethod("POST");
                            urlConnection.setDoOutput(true);
                            urlConnection.setConnectTimeout(5000);
                            urlConnection.setReadTimeout(5000);
                            System.out.println("101");
                            OutputStream os = urlConnection.getOutputStream();
                            System.out.println("110");
                            String req = "name="+name+"&password="+password;
                            os.write(req.getBytes());

                            System.out.println("111");
                            InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream());
                            BufferedReader br = new BufferedReader(isr);
                            String result = br.readLine();
                            System.out.println("开始解析json");
                            //对获得的json数据进行解析
                            try {
                                JSONObject object = new JSONObject(result);
                                success = object.getInt("success");
                                System.out.println("success"+success);

                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            Message m1 = myhandler.obtainMessage();
                            if (success == 0)//登录失败
                            {
                                System.out.println("登录失败");
                                m1.what = 1;
                                myhandler.sendMessage(m1);
                            } else if (success == 1) {//登录成功
                                System.out.println("登录成功");
                                m1.what = 2;
                                myhandler.sendMessage(m1);
                            } else {//其他情况：联网失败，服务器异常等
                                System.out.println("联网失败");
                                m1.what = 3;
                                myhandler.sendMessage(m1);
                            }
                            isr.close();
                            os.close();
                            urlConnection.disconnect();

                        }
                    } catch (MalformedURLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            t.start();

/*
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
            */

        }


    }

}

