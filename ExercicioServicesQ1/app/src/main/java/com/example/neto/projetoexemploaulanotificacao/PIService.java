package com.example.neto.projetoexemploaulanotificacao;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class PIService extends Service {

    private PIWorker worker;

    private PIServiceBinder binder = new PIServiceBinder();

    @Override
    public void onCreate() {
        super.onCreate();

        worker = new PIWorker();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Thread t = new Thread(worker);

        Log.i("MyApp", "Serviço PI iniciado");
        t.start();

        try {
            t.join();
        } catch (InterruptedException ex) {
            Log.e("MyApp", "Não foi possivel colocar a thread junto");
        }

        Log.i("MyApp", "Serviço PI finalizado");
        stopSelf();

        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        Notification.Builder builder = new Notification.Builder(this);

        builder.setContentTitle("Calcular PI");
        builder.setContentText("O valor de PI foi calculado");
        builder.setSmallIcon(android.R.drawable.sym_action_chat);

        Intent intent2 = new Intent(getApplicationContext(),MensageActivity.class);
        intent2.putExtra("PI", worker.getPi());
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent2,0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        Notification notification = builder.build();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(50,notification);
    }

    public boolean isDone(){
        return worker.getPi()!=0;
    }

    public double getPI(){
        return worker.getPi();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }



    public class PIServiceBinder extends Binder {
        public PIService getService(){
            return PIService.this;
        }
    }
}
