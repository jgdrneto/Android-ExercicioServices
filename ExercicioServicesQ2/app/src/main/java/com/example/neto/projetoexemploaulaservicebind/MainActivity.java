package com.example.neto.projetoexemploaulaservicebind;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private TextView textView;
    private RandomNumberService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txt_seconds);
    }

    @Override
    protected void onStart() {
        super.onStart();

        intent =  new Intent(getApplicationContext(), RandomNumberService.class);
        startService(intent);

        TimeServiceConnection conn =  new TimeServiceConnection();
        bindService(intent,conn,0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(intent!=null){
            stopService(intent);
        }
    }

    public void read(View view) {
        double seconds = service.getNumber();
        textView.setText(String.valueOf(seconds));
    }

    private class TimeServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            RandomNumberService.TimeServiceBinder binder = (RandomNumberService.TimeServiceBinder)service;
            MainActivity.this.service = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
