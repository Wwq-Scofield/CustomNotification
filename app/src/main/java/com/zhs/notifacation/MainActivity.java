package com.zhs.notifacation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zhs.notification.library.CustomNotificationManager;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int notifyId;
    public void ShowNotif(View view){
        notifyId++;
        Intent intent=new Intent("com.action.view");
        CustomNotificationManager.showNotification(this,intent,notifyId);
    }
}
