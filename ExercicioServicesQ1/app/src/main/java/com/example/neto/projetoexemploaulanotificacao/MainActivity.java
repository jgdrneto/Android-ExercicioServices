package com.example.neto.projetoexemploaulanotificacao;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private PIService service;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void notify(View view){
        intent =  new Intent(getApplicationContext(), PIService.class);
        startService(intent);

        PIServiceConnection conn =  new PIServiceConnection();
        bindService(intent,conn,0);

    }

    private class PIServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            PIService.PIServiceBinder binder = (PIService.PIServiceBinder)service;
            MainActivity.this.service = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
