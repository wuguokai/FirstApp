package com.example.wuguokai.firstapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.wuguokai.firstapp.R;
import com.example.wuguokai.firstapp.service.TestService;

public class MainActivity extends AppCompatActivity {
    private EditText editText ;
    public static final String PREFERENCE_NAME = "SaveSetting";
    public static int MODE = Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE;

//    private Intent intent = null;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText =(EditText) findViewById(R.id.edit_text);
        final Intent intent = new Intent(this, TestService.class);

//        intent.setAction("com.example.wuguokai.firstapp.service.TestService");

        Button activityButton = (Button) findViewById(R.id.btn_activity);
        Button serviceButton = (Button) findViewById(R.id.btn_service);
        Button intentButton = (Button) findViewById(R.id.btn_intent);
        Button broadcastButton = (Button) findViewById(R.id.btn_broadcast);
        activityButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                跳转到第二个activity
                final Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });

        intentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //打开浏览器并访问网页
                final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        broadcastButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //发送广播消息
                final Intent intent = new Intent("com.example.wuguokai.firstapp.activity.MainActivity");
                intent.putExtra("message", "test Broadcast");
                sendBroadcast(intent);
            }
        });

        serviceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //使用service
                final Intent intent = new Intent(MainActivity.this, ThreadRandomServiceDemoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadSharedPreferences();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveSharedPreferences();
    }

    private void loadSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE);
        String name = sharedPreferences.getString("Name", "WUGUOKAI");
        editText.setText(name);
    }

    private void saveSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Name", editText.getText().toString());
        editor.commit();
    }
}