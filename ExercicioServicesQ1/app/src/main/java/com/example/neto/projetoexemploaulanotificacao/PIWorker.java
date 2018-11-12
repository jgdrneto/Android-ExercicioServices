package com.example.neto.projetoexemploaulanotificacao;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

public class PIWorker implements Runnable{

    private double pi=0;

    @Override
    public void run() {

        Log.i("MyApp", "Calculando valor de PI");

        double soma = 0;

        for(int k=1;k<=1000000;k++){
            soma+=(Math.pow(-1,k+1))/(2 * k - 1);
        }

        this.pi = soma*4;

        Log.i("MyApp", "Calculado valor de PI: " + this.pi);

    }

    public double getPi(){
        return this.pi;
    }

}
