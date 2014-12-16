package com.thoughtworks.karthikintern.reminderapp;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by karthikintern on 9/23/14.
 */
public class BootReciever extends BroadcastReceiver {
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    public void onReceive(Context context, Intent intent) {
        RecvAlarm ra = new RecvAlarm();
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            ra.setAlarm(context);
           /*alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            intent= new Intent(context, BootReciever.class);
            alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
// Set the alarm to start at 8:30 a.m.
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY,17);
            calendar.set(Calendar.MINUTE, 50);
// setRepeating() lets you specify a precise custom interval--in this case,
// 20 minutes.
            alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    1000 * 60 * 20, alarmIntent);*/
        }
    }
}