package com.example.termtracker.Helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.termtracker.UI.Login;

import java.text.ParseException;
import java.util.Date;

public class Notify {

    public static void run(Context ctx, String date, String message) {

        Date tmpDate = null;
        try {
            tmpDate = Utility.SIMPLE_DATE_FORMAT.parse(date);

        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        long trigger = tmpDate.getTime();
        Intent intent = new Intent(ctx, Noticeiver.class);
        intent.putExtra(Noticeiver.MESSAGE_KEY, message);

        PendingIntent pIntent = PendingIntent.getBroadcast(ctx,
                Login.alertNum++,
                intent,
                PendingIntent.FLAG_IMMUTABLE);

        AlarmManager manager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, trigger, pIntent);

    }

    public static void show(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }
}
