package com.example.vegnish.assignment1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmBroadcaster extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Remember in the SetAlarm file we made an intent to this, this is way this work, otherwise you would have to put an action
        //and here listen to the action, like in a normal receiver
        createNotification(context);
    }

    public void createNotification(Context context) {
        NotificationTrigger.starNotification(context);
    }

}
