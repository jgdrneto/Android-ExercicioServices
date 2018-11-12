package com.example.neto.projetoexemploaulaservicebind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class RandomNumberService extends Service {

    private RandomNumberWorker worker;

    private TimeServiceBinder binder = new TimeServiceBinder();

    @Override
    public void onCreate() {
        super.onCreate();

        worker = new RandomNumberWorker();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyApp", "Serviço iniciado");
        new Thread(worker).start();
        return START_NOT_STICKY;

    }

    @Override
    public void onDestroy() {
        worker.stop();
        Log.i("MyApp", "Serviço finalizado");
    }

    @Override
    public IBinder onBind(Intent intent) {
       return binder;
    }

    public double getNumber(){
        return worker.getNumber();
    }

    public class TimeServiceBinder extends Binder{
        public RandomNumberService getService(){
            return RandomNumberService.this;
        }
    }

}
