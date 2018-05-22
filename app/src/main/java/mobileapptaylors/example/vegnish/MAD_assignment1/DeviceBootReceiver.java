package mobileapptaylors.example.vegnish.MAD_assignment1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DeviceBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
//                new SetAlarm().alarmSetter(context);
            }
        }
    }
}
