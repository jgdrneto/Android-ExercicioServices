package com.example.neto.projetoexemploaulaservicebind;

import android.os.SystemClock;
import android.util.Log;

public class RandomNumberWorker implements Runnable{

    private volatile boolean running;
    private double number;

    @Override
    public void run() {

        running = true;

        while(running){
            //seconds++;
            this.createNumber();
            Log.i("MyApp", "Número: " + this.number);
            SystemClock.sleep(1000);
        }

    }

    public void stop(){
        running=false;
    }

    private synchronized void createNumber(){
        number = Math.random();
    }

    public synchronized double getNumber(){
        return this.number;
    }
}
