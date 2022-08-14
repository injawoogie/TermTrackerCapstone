package com.example.termtracker.Helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.termtracker.R;

public class Noticeiver extends BroadcastReceiver {

    String CHANNEL_ID = "noticeiver";
    static int notificationId;
    public static String CONTENT_KEY = "notify";

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, intent.getStringExtra(CONTENT_KEY), Toast.LENGTH_LONG).show();
        createNotificationChannel(context, CHANNEL_ID);

        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra(CONTENT_KEY))
                .setContentTitle(context.getString(R.string.notification_content_title)).build();

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(notificationId++, notification);


        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void createNotificationChannel(Context context, String CHANNEL_ID) {

        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                context.getResources().getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_DEFAULT);

        channel.setDescription(context.getString(R.string.channel_description));

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

    }

}