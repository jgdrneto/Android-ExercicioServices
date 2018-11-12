package com.example.neto.projetoexemploaulanotificacao;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class NotificationUtils {

    public static final String CHANNEL_ID = "br.ufrn.id.android.channel";
    public static final String CHANNEL_NAME = "My Channel";

    public static String getChannelId(Context context){

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.O){
            return CHANNEL_ID;
        }

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = nm.getNotificationChannel(CHANNEL_ID);

        if(channel==null){
            channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
            nm.createNotificationChannel(channel);
        }

        return CHANNEL_ID;

    }
}
