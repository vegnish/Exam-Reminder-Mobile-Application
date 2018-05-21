package com.example.vegnish.assignment1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

public class SetAlarm {

    public void alarmSetter(Context context, long alarmTime) {

        //This will set the alarm time to be at the 14:30
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(alarmTime);
//        calendar.set(Calendar.HOUR_OF_DAY, 14);
//        calendar.set(Calendar.MINUTE, 55);

        //This intent, will execute the AlarmBroadcaster when the alarm is triggered
        Intent alertIntent = new Intent(context, AlarmBroadcaster.class);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Log.d ("alarm time", "time: "+ calendar.getTimeInMillis());
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }

}
